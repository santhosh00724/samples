/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssurya.boot;

/**
 *
 * @author ssuryade
 */
public class AWSKit {
    
    private String kit;
    private Integer quantity;

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AWSKit{" + "kit=" + kit + ", quantity=" + quantity + '}';
    }
    
    
}
