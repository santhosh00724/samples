/*******************************************************************************
 * Copyright © 2016 Oracle and/or its affiliates. All rights reserved.
 *******************************************************************************/
package com.ssurya.boot;

import java.util.UUID;

public class ManualResupplyResponse {

	private UUID orderId;
	private String shipmentNumber;
	private String status;
	/**
	 * @return the orderId
	 */
	public UUID getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

    @Override
    public String toString() {
        return "ManualResupplyResponse{" + "orderId=" + orderId + ", shipmentNumber=" + shipmentNumber + ", status=" + status + '}';
    }
        
        
}
