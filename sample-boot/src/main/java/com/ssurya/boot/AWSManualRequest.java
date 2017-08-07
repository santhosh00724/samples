/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssurya.boot;

import java.util.List;

/**
 *
 * @author ssuryade
 */
public class AWSManualRequest {
    
    private List<AWSKit> kits;

    public List<AWSKit> getKits() {
        return kits;
    }

    public void setKits(List<AWSKit> kits) {
        this.kits = kits;
    }

    @Override
    public String toString() {
        return "AWSManualRequest{" + "kits=" + kits + '}';
    }
    
    
    
}
