package com.com3018.controller;

import com.com3018.dao.CartDao;
import com.com3018.dao.ProductDao;
import com.com3018.model.Cart;
import com.com3018.model.CartItem;
import com.com3018.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anisha on 08/05/2016.
 */

// mainly providing restful services

@Controller
@RequestMapping("/rest/cart")
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)

    // returning model object in format of jackson, put cart object in response body

    public
    @ResponseBody
    Cart read(@PathVariable(value = "cartId") String cartId) {
        return cartDao.read(cartId);
    }

    // put method to update information

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)

    // take body of request and transfer into cart object

    public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart) {

        cartDao.update(cartId, cart);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "cartId") String cartId) {
        cartDao.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId") String productId, HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();
        Cart cart = cartDao.read(sessionId);

        // cart doesnt exist, create new cart with session id

        if (cart == null) {
            cart = cartDao.create(new Cart(sessionId));
        }

        // add product to cart, if product doesn't exist, throw exception

        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException(new Exception());
        }

        // create new cart item, to retrieve the product

        cart.addCartItem(new CartItem(product));

        // update cart with sessionid

        cartDao.update(sessionId, cart);
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable String productId, HttpServletRequest request) {

        String sessionId = request.getSession(true).getId();
        Cart cart = cartDao.read(sessionId);

        if (cart == null) {
            cart = cartDao.create(new Cart(sessionId));
        }

        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException(new Exception());
        }

        cart.removeCartItem(new CartItem(product));

        cartDao.update(sessionId, cart);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "illegal request, please verify your payload")
    public void handleClientErrors(Exception e) {
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public void handleServerErrors(Exception e) {
    }
}

