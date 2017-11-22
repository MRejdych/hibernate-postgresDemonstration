package app.controllers;

import app.dataAccessObjects.CustomersDAO;
import app.entities.Customer;
import app.springconfig.ApplicationConfiguration;
import app.utils.CustomerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomersController {
    private ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


    @GetMapping("/customers/showAll")
    public String showCustomersSelectAllPage(Model model) {
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        List<Customer> customers = dao.readAll();
        model.addAttribute("customers", customers);
        return "selectAllCustomers";
    }

    @PostMapping("/customers/selectCustomer")
    public String selectCustomerResult(@RequestParam("customerId") Short customerId, Model model) {
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        Customer customer = dao.readById(customerId);
        model.addAttribute("customer", customer);

        return "selectCustomer";
    }


    @GetMapping("/customers/addForm")
    public String addCustomer(Model model) {
        Customer customer = new CustomerBuilder().build();
        model.addAttribute("customer", customer);
        return "addCustomerForm";
    }

    @PostMapping("/customers/addCustomer")
    public String addCustomerResult(@ModelAttribute("customer") Customer customer, Model model) {
        System.out.println(customer);

        CustomersDAO dao = context.getBean(CustomersDAO.class);
        dao.create(customer);

        return "redirect:showAll";
    }

    @PostMapping("customers/updateForm")
    public String updateCustomer(@RequestParam("customerId") Short customerId, Model model){
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        Customer customer = dao.readById(customerId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("customer", customer);

        return "updateCustomerForm";
    }

    @PostMapping("customers/updateCustomer")
    public String updateCustomerResult(@ModelAttribute("customer") Customer customer, @RequestParam("customerId") Short customerId){
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        dao.update(customer, customerId);

        return "redirect:showAll";
    }

    @PostMapping("customers/deleteCustomer")
    public String deleteCustomerResult(@RequestParam("customerId") Short customerId, Model model){
        CustomersDAO dao = context.getBean(CustomersDAO.class);
        dao.delete(customerId);

        return "redirect:showAll";
    }
}
