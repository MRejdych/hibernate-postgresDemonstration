package app.controllers;


import app.dao.*;
import app.entities.*;
import app.orders.OrderReportEntry;
import app.orders.OrderedProductDetails;
import app.orders.OrdersReportCreator;
import app.springconfig.ApplicationConfiguration;
import app.utils.Dates;
import app.utils.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static app.orders.OrdersReportCreator.createReportFromOrders;
import static java.lang.Short.parseShort;

@Controller
public class OrdersController {
    private ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


    @RequestMapping("/orders/show")
    public String showOrders(@ModelAttribute(value = "pair") Pair<String, String> pair, Model model){
        OrdersDAO dao = context.getBean(OrdersDAO.class);
        LocalDate from = Dates.parseDate(pair.getLeft());
        LocalDate to = Dates.parseDate(pair.getRight());


        List<Order> orders = dao.selectFromDatePeriod(from, to);
        model.addAttribute("pair", new Pair<>("", ""));
        model.addAttribute("orders", orders);
        model.addAttribute("from", pair.getLeft());
        model.addAttribute("to", pair.getRight());
        return "selectOrders";
    }

    @PostMapping("/orders/orderDetails")
    public String showOrders(@RequestParam(value = "orderId") Short orderId, Model model){
        OrdersDAO dao = context.getBean(OrdersDAO.class);
        List<Order> orders = dao.selectById(orderId);

        model.addAttribute("orders", orders);

        return "showOrderDetails";
    }


    @GetMapping("/orders/addForm")
    public String addForm(Model model){
        CustomersDAO customersDAO = context.getBean(CustomersDAO.class);
        ProductsDAO productsDAO = context.getBean(ProductsDAO.class);
        List<Product> products = productsDAO.readAll();
        List<Customer> customers = customersDAO.readAll();

        model.addAttribute("orderedProducts", new HashMap<Short, Short>());
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        return "addOrderForm";
    }

    @PostMapping("/orders/addForm")
    public String updateAddForm(@RequestParam Map<String, String> params, Model model){

        CustomersDAO customersDAO = context.getBean(CustomersDAO.class);
        ProductsDAO productsDAO = context.getBean(ProductsDAO.class);
        List<Product> products = productsDAO.readAll();
        List<Customer> customers = customersDAO.readAll();

        String productId = params.get("productId");
        String amount = params.get("amount");

        params.remove("productId");
        params.remove("amount");

        params.put(productId, amount);

        List<OrderedProductDetails> orderedProductsDetails = OrderedProductDetails.getOrderedProductsDetails(products, params);
        products = products.stream()
                .filter(it -> !params.keySet().contains(String.valueOf(it.getProductId())))
                .collect(Collectors.toList());


        model.addAttribute("orderedProducts", params);
        model.addAttribute("orderedProductsDetails", orderedProductsDetails);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);

        return "addOrderForm";
    }



    @PostMapping("/orders/addOrder")
    public String addOrder(@RequestParam Map<String, String> params, Model model){
        CustomersDAO customersDAO = context.getBean(CustomersDAO.class);
        ProductsDAO productsDAO = context.getBean(ProductsDAO.class);
        OrdersDAO ordersDAO = context.getBean(OrdersDAO.class);

        Map<String, Product> products = new HashMap<>();

        productsDAO.readAll()
                .stream()
                .filter(it -> params.keySet().contains(String.valueOf(it.getProductId())))
                .forEach(it -> products.put(String.valueOf(it.getProductId()), it));

        Short customerId = Short.valueOf(params.get("customerId"));
        params.remove("customerId");

        Customer customer = customersDAO.readById(customerId);

        Order submittedOrder = new Order();
        submittedOrder.setCustomer(customer);
        submittedOrder.setOrderDate(LocalDate.now());
        submittedOrder.setAddress(customer.getAddress());

        List<OrderDetails> orderDetails = params.entrySet()
                .stream()
                .map(e -> new OrderDetails(submittedOrder, products.get(e.getKey()), products.get(e.getKey()).getUnitPrice(), parseShort(e.getValue()), 0.0f))
                .collect(Collectors.toList());

        submittedOrder.setOrderDetails(orderDetails);

        ordersDAO.create(submittedOrder);

        return "redirect:show";
    }


    @PostMapping("/orders/showReport")
    public String addForm(@RequestParam Map<String, String> params,
                          Model model){
        OrdersDAO ordersDAO = context.getBean(OrdersDAO.class);
        List<Order> orders = ordersDAO.selectFromDatePeriod(null, null);

        String from = params.get("from");
        String to = params.get("to");
        if(from.equals("")) from = null;
        if(to.equals("")) to = null;
        params.remove("from");
        params.remove("to");

        Optional<String> fromOpt = Optional.ofNullable(from);
        Optional<String> toOpt = Optional.ofNullable(to);

        Set<Short> orderIds = params.values().stream().map(Short::parseShort).collect(Collectors.toSet());
        orders = orders.stream().filter(it -> orderIds.contains(it.getOrderId())).collect(Collectors.toList());

        List<OrderReportEntry> reportEntries = OrdersReportCreator.createReportFromOrders(orders);
        Double allMoneyEarned = reportEntries.stream().mapToDouble(it -> it.moneyEarned).sum();

        model.addAttribute("reportEntries", reportEntries);
        model.addAttribute("from", fromOpt.orElse("początku"));
        model.addAttribute("to", toOpt.orElse("dnia obecnego"));
        model.addAttribute("moneyEarned", allMoneyEarned);


        return "ordersReport";
    }
}
