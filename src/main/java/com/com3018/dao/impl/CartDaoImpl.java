package com.com3018.dao.impl;

import com.com3018.dao.CartDao;
import com.com3018.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anisha on 08/05/2016.
 */

// register this class as a bean, accessing data

@Repository
public class CartDaoImpl implements CartDao {

    private Map<String, Cart> listOfCarts;

    // constructor to initalise list of cars

    public CartDaoImpl() {
        listOfCarts = new HashMap<String, Cart>();
    }

    // check if cart id already exists

    public Cart create(Cart cart) {
        if (listOfCarts.keySet().contains(cart.getCartId())) {
            throw new IllegalArgumentException(String.format("cannot create a cart. a cart with the given id already exists.", cart.getCartId()));
        }

        // if cart passes the test, adds to list of carts

        listOfCarts.put(cart.getCartId(), cart);

        return cart;
    }

    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }

    // if cart doesn't contain id, throw new exception

    public void update(String cartId, Cart cart) {
        if (!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(String.format("cannot update cart. a cart with the given id doesn't exist.", cart.getCartId()));
        }

        // if check is passed, add cart into cart list

        listOfCarts.put(cartId, cart);
    }

    public void delete(String cartId) {
        if (!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(String.format("cannot delete cart. a cart with the given id doesn't exist.", cartId));

        }

        // passed check, remove cart from lisr

        listOfCarts.remove(cartId);
    }
}
