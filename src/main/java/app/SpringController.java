package app;

import app.entities.Customer;
import app.queries.CustomersManipulator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SpringController {

    @RequestMapping("/customers")
    public String showCustomersPage() {
        return "customers";
    }

    @RequestMapping("/customers/selectAll")
    public String showCustomersSelectAllPage(Model model) {
        CustomersManipulator cm = new CustomersManipulator();
        List<Customer> customers = cm.selectAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("nativeQueryTime", cm.getNativeSqlQueryTime());
        model.addAttribute("JPQLqueryTime", cm.getJPQLqueryTime());
        return "selectAllCustomers";
    }
}
