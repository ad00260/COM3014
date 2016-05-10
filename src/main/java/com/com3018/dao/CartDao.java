package com.com3018.dao;

import com.com3018.model.Cart;

/**
 * Created by Anisha on 08/05/2016.
 */
public interface CartDao {

    Cart create(Cart cart);

    Cart read(String cartId);

    void update(String cartId, Cart cart);

    void delete(String cartId);
}
