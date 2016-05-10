package com.com3018.dao;

import com.com3018.model.Product;

import java.util.List;

/**
 * Created by Anisha on 08/05/2016.
 */
public interface ProductDao {

    void addProduct(Product product);

    void editProduct(Product product);

    Product getProductById(String id);

    List<Product> getAllProducts();

    void deleteProduct(String id);
}
