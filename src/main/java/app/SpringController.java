package app;

import app.entities.Customer;
import app.queries.CustomersManipulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SpringController {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


    @RequestMapping("/customers")
    public String showCustomersPage() {
        return "customers";
    }

    @RequestMapping("/customers/selectAll")
    public String showCustomersSelectAllPage(Model model) {
        CustomersManipulator cm = context.getBean(CustomersManipulator.class);
        List<Customer> customers = cm.selectAll();
        model.addAttribute("customers", customers);
        model.addAttribute("nativeQueryTime", cm.getNativeSqlQueryTime());
        model.addAttribute("JPQLqueryTime", cm.getJPQLqueryTime());
        return "selectAllCustomers";
    }

    @RequestMapping("/customers/selectCustomer/form")
    public String selectCustomer(Model model) {

        return "selectCustomerForm";
    }

    @RequestMapping("/customers/selectCustomer/result")
    public String selectCustomerResult(Model model) {
        CustomersManipulator cm = context.getBean(CustomersManipulator.class);
        short customerId = (short) model.asMap().get("customerId");
        cm.selectById(customerId);

        return "selectCustomerResult";
    }


    @RequestMapping("/customers/addCustomer/form")
    public String addCustomer(Model model) {

        return "addCustomerForm";
    }

    @RequestMapping("/customers/addCustomer/result")
    public String addCustomerResult(Model model) {
        CustomersManipulator cm = context.getBean(CustomersManipulator.class);
        cm.add(model);

        return "addCustomerResult";
    }

    @RequestMapping("customers/updateCustomer/form")
    public String updateCustomer(Model model){
        return "updateCustomerForm";
    }

    @RequestMapping("customers/updateCustomer/result")
    public String updateCustomerResult(Model model){
        CustomersManipulator cm = context.getBean(CustomersManipulator.class);
        short customerId = (short) model.asMap().get("customerId");
        cm.update(customerId, model);

        return "updateCustomerResult";
    }

    @RequestMapping("customers/deleteCustomer/form")
    public String deleteCustomerForm(Model model){
        return "deleteCustomerForm";
    }

    @RequestMapping("customers/deleteCustomer/result")
    public String deleteCustomerResult(Model model){
        CustomersManipulator cm = context.getBean(CustomersManipulator.class);
        short customerId = (short) model.asMap().get("customerId");
        cm.remove(customerId);

        return "deleteCustomerResult";
    }
}
