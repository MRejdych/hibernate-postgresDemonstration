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
        List<Customer> customers = cm.selectAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("nativeQueryTime", cm.getNativeSqlQueryTime());
        model.addAttribute("JPQLqueryTime", cm.getJPQLqueryTime());
        return "selectAllCustomers";
    }
}
