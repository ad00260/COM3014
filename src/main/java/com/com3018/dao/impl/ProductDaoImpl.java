package com.com3018.dao.impl;

import com.com3018.dao.ProductDao;
import com.com3018.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Anisha on 08/05/2016.
 */

// dealing with the database

@Repository

// spring hibernate will know how to deal with transactions

@Transactional
public class ProductDaoImpl implements ProductDao {


    // autowire interface, spring will find beans and inject them into container as beans

    @Autowired
    private SessionFactory sessionFactory;

    public void addProduct(Product product) {

        // define session variable using session factory

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);

        // session operation excluded

        session.flush();
    }

    public void editProduct(Product product) {

        // if its new, can be created, already there- system will update automatically

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);

        session.flush();
    }

    public Product getProductById(String id) {
        Session session = sessionFactory.getCurrentSession();

        // use session to get product class with primary key

        Product product = (Product) session.get(Product.class, id);
        session.flush();

        return product;
    }

    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        List<Product> products = query.list();
        session.flush();

        return products;
    }

    public void deleteProduct(String id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getProductById(id));
        session.flush();
    }
}
