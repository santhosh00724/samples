/*******************************************************************************
 * Copyright © 2016 Oracle and/or its affiliates. All rights reserved.
 *******************************************************************************/
/**
 * 
 */
package com.ssurya.boot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ssuryade
 *
 */
public class ManualSupplyRequest {

	private List<KitRequest> kits;

	private UUID siteId;

	
	/**
	 * @return
	 */
	public UUID getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId
	 */
	public void setSiteId(UUID siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the kits
	 */
	public List<KitRequest> getKits() {
		if (kits == null) {
			kits = new ArrayList<>();
		}
		return kits;
	}

	/**
	 * @param kits the kits to set
	 */
	public void setKits(List<KitRequest> kits) {
		this.kits = kits;
	}

    @Override
    public String toString() {
        return "ManualSupplyRequest{" + "kits=" + kits + ", siteId=" + HexUtils.toHexString(siteId) + '}';
    }
        
        
}
