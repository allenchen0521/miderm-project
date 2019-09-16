package com.iiiedu.eeit109.shoppingcart;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> cartItemList = new LinkedHashMap<Integer, CartItem>();
    
    public int getTotal() {
        int total = 0;
        for(CartItem c:cartItemList.values()) {
            total+=c.getSubtotal();
        }
        return total;
    }
    
    public void add(CartItem cartItem) {
        if(cartItemList.containsKey(cartItem.getProduct().getProd_id())) {
            CartItem oldCartItem = cartItemList.get(cartItem.getProduct().getProd_id());
            oldCartItem.setCount(oldCartItem.getCount()+cartItem.getCount());
            cartItemList.put(oldCartItem.getProduct().getProd_id(),oldCartItem);
        } else {
            cartItemList.put(cartItem.getProduct().getProd_id(),cartItem);
        }
    }
    
    public void delete(int prod_id) {
        cartItemList.remove(prod_id);
    }
    
    public void clear() {
        cartItemList.clear();
    }
    
    public Collection<CartItem> getCartItemList() {
        return cartItemList.values();
    }
}
