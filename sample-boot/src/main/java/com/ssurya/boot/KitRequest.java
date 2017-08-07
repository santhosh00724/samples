/*******************************************************************************
 * Copyright © 2016 Oracle and/or its affiliates. All rights reserved.
 *******************************************************************************/
package com.ssurya.boot;

import java.util.UUID;

public class KitRequest {
	
	private UUID kitTypeId;
	private Integer quantity;
	
	/**
	 * @return the kitTypeId
	 */
	public UUID getKitTypeId() {
		return kitTypeId;
	}
	/**
	 * @param kitType the kitTypeId to set
	 */
	public void setKitTypeId(UUID kitTypeId) {
		this.kitTypeId = kitTypeId;
	}
	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity.toString();
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    @Override
    public String toString() {
        return "KitRequest{" + "kitTypeId=" + HexUtils.toHexString(kitTypeId) + ", quantity=" + quantity + '}';
    }
	
        

}
