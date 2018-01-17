package app.controllers;


import app.dao.*;
import app.entities.*;
import app.springconfig.ApplicationConfiguration;
import app.utils.Dates;
import app.utils.Pair;
import app.utils.Triad;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("orderedProducts", new HashMap<Product, Short>());
        return "addOrderForm";
    }

    @PostMapping("/orders/addForm")
    public String updateAddForm(@RequestParam(name = "productId") Short productId,
                                @RequestParam(name = "amount") Short amount,
                                @ModelAttribute(value = "orderedProducts") HashMap<Product, Short> orderedProducts,
                                Model model){

        CustomersDAO customersDAO = context.getBean(CustomersDAO.class);
        ProductsDAO productsDAO = context.getBean(ProductsDAO.class);
        List<Product> products = productsDAO.readAll();
        List<Customer> customers = customersDAO.readAll();

        Product orderedProduct = products.stream().filter(it -> it.getProductId().equals(productId)).findFirst().orElse(null);
        products.removeAll(orderedProducts.keySet());



        orderedProducts.put(orderedProduct, amount);

        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("orderedProducts", orderedProducts);
        return "addOrderForm";
    }

    @PostMapping("/orders/addOrder")
    public String addOrder(@ModelAttribute(value = "order") Order order,
                                @ModelAttribute(value = "customers") List<Customer> customers,
                                @ModelAttribute(value = "products") List<Product> products,
                                @ModelAttribute(value = "orderedProducts")Map<Product, Short> orderedProducts,
                                Model model){

        products.removeAll(orderedProducts.keySet());

        model.addAttribute("order", order);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("orderedProducts", orderedProducts);
        return "addOrderForm";
    }


    @PostMapping("/orders/showReport")
    public String addForm(@RequestParam("orders") List<Order> orders, Model model){


        // to do
        return "addOrderForm";
    }
}
