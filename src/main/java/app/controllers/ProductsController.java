package app.controllers;

import app.dataAccessObjects.*;
import app.entities.Category;
import app.entities.Customer;
import app.entities.Product;
import app.entities.Supplier;
import app.springconfig.ApplicationConfiguration;
import app.utils.ProductBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static app.constants.ProductsDbFields.PRODUCT_ID;

@Controller
public class ProductsController {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);


    @GetMapping("/products/showAll")
    public String showProductsSelectAllPage(Model model) {
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        List<Product> products = dao.readAll();
        model.addAttribute("products", products);
        return "selectAllProducts";
    }

    @PostMapping("/products/selectProduct")
    public String selectProduct(@RequestParam("productId") Short productId, Model model) {
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        Product product = dao.readById(productId);
        model.addAttribute("product", product);

        return "selectProduct";
    }


    @GetMapping("/products/addForm")
    public String addProduct(Model model) {
        SuppliersHelper supplierDAO = context.getBean(SuppliersHelper.class);
        CategoriesHelper categoriesDAO = context.getBean(CategoriesHelper.class);
        List<Category> categories = categoriesDAO.selectAll();
        List<Supplier> suppliers = supplierDAO.selectAll();
        Product product = new ProductBuilder().build();

        model.addAttribute("product", product);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("categories", categories);

        return "addProductForm";
    }

    @PostMapping("/products/addProduct")
    public String addProductResult(@ModelAttribute("product") Product product, @RequestParam("supplierId") Short supplierId,
                                   @RequestParam("categoryId") Short categoryId, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
        }

        SuppliersHelper supplierDAO = context.getBean(SuppliersHelper.class);
        CategoriesHelper categoriesDAO = context.getBean(CategoriesHelper.class);
        ProductsDAO dao = context.getBean(ProductsDAO.class);

        Supplier supplier = supplierDAO.selectById(supplierId);
        Category category = categoriesDAO.selectById(categoryId);
        product.setSupplier(supplier);
        product.setCategory(category);

        System.out.println(product);
        dao.create(product);

        return "redirect:showAll";
    }

    @PostMapping("products/updateForm")
    public String updateProduct(@RequestParam("productId") Short productId, Model model){

        ProductsDAO dao = context.getBean(ProductsDAO.class);
        SuppliersHelper supplierDAO = context.getBean(SuppliersHelper.class);
        CategoriesHelper categoriesDAO = context.getBean(CategoriesHelper.class);
        List<Category> categories = categoriesDAO.selectAll();
        List<Supplier> suppliers = supplierDAO.selectAll();
        Product product = dao.readById(productId);

        model.addAttribute("productId", productId);
        model.addAttribute("product", product);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("categories", categories);

        return "updateProductForm";

    }

    @PostMapping("products/updateProduct")
    public String updateProductResult(@ModelAttribute("product") Product product, @RequestParam("productId") Short productId,
                                      @RequestParam("supplierId") Short supplierId, @RequestParam("categoryId") Short categoryId, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.toString());
        }

        SuppliersHelper supplierDAO = context.getBean(SuppliersHelper.class);
        CategoriesHelper categoriesDAO = context.getBean(CategoriesHelper.class);
        ProductsDAO dao = context.getBean(ProductsDAO.class);

        Supplier supplier = supplierDAO.selectById(supplierId);
        Category category = categoriesDAO.selectById(categoryId);
        product.setSupplier(supplier);
        product.setCategory(category);

        System.out.println(product);

        dao.update(product ,productId);

        return "redirect:showAll";
    }

    @PostMapping("products/deleteProduct")
    public String deleteProductResult(@RequestParam("productId") Short productId, Model model){
        ProductsDAO dao = context.getBean(ProductsDAO.class);
        dao.delete(productId);

        return "redirect:showAll";
    }
}
