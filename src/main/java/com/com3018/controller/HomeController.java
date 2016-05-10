package com.com3018.controller;

import com.com3018.dao.ProductDao;
import com.com3018.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Anisha on 07/05/2016.
 */
@Controller
public class HomeController {


    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    // define the mapping path

    @RequestMapping("/productList")

    // import the model parameter attached to the view

    public String getProducts(Model model) {
        List<Product> products = productDao.getAllProducts();

        // add attributes to the model

        model.addAttribute("products", products);

        // return the view name as string

        return "productList";

    }

    @RequestMapping("/productList/viewProduct/{productId}")
    public String viewProduct(@PathVariable String productId, Model model) throws IOException {

        // create a new object instance of product from product dao

        Product product = productDao.getProductById(productId);

        // add product to model

        model.addAttribute(product);

        return "viewProduct";
    }
}