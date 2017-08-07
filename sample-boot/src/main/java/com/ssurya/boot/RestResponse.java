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
public class RestResponse {
 
    private List<ManualResupplyResponse> Resources;
    
    private List<String> schemas;
    
    private Integer totalResults;

    public List<ManualResupplyResponse> getResources() {
        return Resources;
    }

    public void setResources(List<ManualResupplyResponse> Resources) {
        this.Resources = Resources;
    }

    public List<String> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<String> schemas) {
        this.schemas = schemas;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
    
    
}
