package app.controllers;

import app.dataAccessObjects.ProductsDAO;
import app.entities.Product;
import app.springconfig.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static app.constants.ProductsDbFields.PRODUCT_ID;

@Controller
public class ProductsController {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    @GetMapping("/products")
    public String showProductsPage() {
        return "products";
    }

    @GetMapping("/products/selectAll")
    public String showProductsSelectAllPage(Model model) {
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        List<Product> products = dao.selectAll();
        model.addAttribute("productss", products);
        model.addAttribute("nativeQueryTime", 0);
        model.addAttribute("JPQLqueryTime", 0);
        return "selectAllProducts";
    }

    @GetMapping("/products/selectProductForm")
    public String selectProduct(Model model) {

        return "selectProductForm";
    }

    @PostMapping("/products/selectProductResult")
    public String selectProductResult(Model model) {
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        short productId = (short) model.asMap().get(PRODUCT_ID);
        model.addAttribute("customer", dao.selectById(productId));

        return "selectProductResult";
    }


    @GetMapping("/products/addProductForm")
    public String addProduct(Model model) {

        return "addProductForm";
    }

    @PostMapping("/products/addProductResult")
    public String addProductResult(Model model) {
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        dao.add(model);

        return "addProductResult";
    }

    @GetMapping("products/updateProductsForm")
    public String updateProduct(Model model){
        return "updateProductForm";
    }

    @PostMapping("products/updateProductResult")
    public String updateProductResult(Model model){
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        short productId = (short) model.asMap().get(PRODUCT_ID);
        dao.update(productId, model);

        return "updateProductResult";
    }

    @GetMapping("products/deleteProductForm")
    public String deleteProductForm(Model model){
        return "deleteProductForm";
    }

    @PostMapping("products/deleteProductResult")
    public String deleteProductResult(Model model){
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        short productId = (short) model.asMap().get(PRODUCT_ID);
        dao.remove(productId);

        return "deleteProductResult";
    }
}
