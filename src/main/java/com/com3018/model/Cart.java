package com.com3018.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anisha on 08/05/2016.
 */
public class Cart {

    private String cartId;
    private Map<String, CartItem> cartItems;
    private double grandTotal;

    // default constructor

    private Cart() {
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }

    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    // retrieve item product id
    public void addCartItem(CartItem item) {
        String productId = item.getProduct().getProductId();

        // identify if the hash map already contains this product id (already in the cart)

        if (cartItems.containsKey(productId)) {

            // define new existing cart item, refer to cart item with product id

            CartItem existingCartItem = cartItems.get(productId);

            // change quantity into previous one in cart, plus new quantity

            existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());


            // update product id with new existing cart item

            cartItems.put(productId, existingCartItem);
        } else {
            cartItems.put(productId, item);
        }

        // change total price

        updateGrandTotal();
    }

    // retrieve product id from cart item, pass id to cart item hash map, update grand total

    public void removeCartItem(CartItem item) {
        String productId = item.getProduct().getProductId();
        cartItems.remove(productId);
        updateGrandTotal();
    }

    // go through all items in cart, add all item prices

    public void updateGrandTotal() {
        grandTotal = 0;
        for (CartItem item : cartItems.values()) {
            grandTotal = grandTotal + item.getTotalPrice();
        }
    }
}
