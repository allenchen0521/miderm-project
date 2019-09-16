package com.iiiedu.eeit109.shoppingcart;

import com.iiiedu.eeit109.shoppingcart.bean.Product;

public class CartItem {
    private Product product;
    private int count;
    
    public int getSubtotal() {
        return product.getProd_price()*count;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}
