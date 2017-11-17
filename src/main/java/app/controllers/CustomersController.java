package app.controllers;

import app.dataAccessObjects.CustomersDAO;
import app.entities.Customer;
import app.springconfig.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static app.constants.CustomersDbFields.CUSTOMER_ID;

@Controller
public class CustomersController {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


    @GetMapping("/customers")
    public String showCustomersPage() {
        return "customers";
    }

    @GetMapping("/customers/selectAll")
    public String showCustomersSelectAllPage(Model model) {
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        List<Customer> customers = dao.selectAll();
        model.addAttribute("customers", customers);
        model.addAttribute("nativeQueryTime", 0);
        model.addAttribute("JPQLqueryTime", 0);
        return "selectAllCustomers";
    }

    @GetMapping("/customers/selectCustomerForm")
    public String selectCustomer(Model model) {

        return "selectCustomerForm";
    }

    @PostMapping("/customers/selectCustomerResult")
    public String selectCustomerResult(Model model) {
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        short customerId = (short) model.asMap().get(CUSTOMER_ID);
        model.addAttribute("customer", dao.selectById(customerId));

        return "selectCustomerResult";
    }


    @GetMapping("/customers/addCustomerForm")
    public String addCustomer(Model model) {

        return "addCustomerForm";
    }

    @PostMapping("/customers/addCustomerResult")
    public String addCustomerResult(Model model) {
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        dao.add(model);

        return "addCustomerResult";
    }

    @GetMapping("customers/updateCustomerForm")
    public String updateCustomer(Model model){
        return "updateCustomerForm";
    }

    @PostMapping("customers/updateCustomerResult")
    public String updateCustomerResult(Model model){
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        short customerId = (short) model.asMap().get(CUSTOMER_ID);
        dao.update(customerId, model);

        return "updateCustomerResult";
    }

    @GetMapping("customers/deleteCustomerForm")
    public String deleteCustomerForm(Model model){
        return "deleteCustomerForm";
    }

    @PostMapping("customers/deleteCustomerResult")
    public String deleteCustomerResult(Model model){
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        short customerId = (short) model.asMap().get(CUSTOMER_ID);
        dao.remove(customerId);

        return "deleteCustomerResult";
    }
}
