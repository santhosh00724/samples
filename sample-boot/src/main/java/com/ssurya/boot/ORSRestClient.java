/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssurya.boot;

import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ssuryade
 */
public class ORSRestClient {
    


public static final String REST_SERVICE_URI = "http://slc05zup.us.oracle.com:7001/ec-ors-svc/rest/v1.0/studies/";
     
    /* POST */
    public String createManualRequest(AWSManualRequest awsRequest) {
        String result = "";
        System.out.println("Testing create manual request API----------");
        RestTemplate restTemplate = new BasicAuthRestTemplate("testuser1", "password1");
        String plainCreds = "testuser1:password1";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/json");
        
        
        /**
         * {"kits":[{"kitTypeId":"BFD5E4C2C1904398BC8E558C5509E9DC","quantity":1},{"kitTypeId":"C41A50D532534A269A9CD30814473725"
,"quantity":1}],"siteId":"5C90508F4CC94130BF846D1501D25565"}
         */
        String studyId = "1671E211A3054C1287E9FB7BE055B5DF";
        String mode = "test";
        
        String url = REST_SERVICE_URI+studyId+"/"+mode+"/resupply/manual";
        
//        String user = "testuser1";
//        String password = "password1";
        
        ManualSupplyRequest request = new ManualSupplyRequest();
        request.setSiteId(HexUtils.toUUID("5C90508F4CC94130BF846D1501D25565"));
        
        List<KitRequest> kits = new ArrayList<>();
        
        for (AWSKit aKit : awsRequest.getKits()) {
            KitRequest kit = new KitRequest();
            
            if ("active".equalsIgnoreCase(aKit.getKit())) {
                kit.setKitTypeId(HexUtils.toUUID("BFD5E4C2C1904398BC8E558C5509E9DC"));
            }else {
                kit.setKitTypeId(HexUtils.toUUID("C41A50D532534A269A9CD30814473725"));
            }
            
            
            kit.setQuantity(aKit.getQuantity());
            kits.add(kit);
        }
        
        request.setKits(kits);
        
        System.out.println(request);
        
        ResponseEntity<Object> response = null;
        Object obj = null;
        response = restTemplate.postForEntity(url, request, Object.class, obj);
        
        if (response.getStatusCodeValue() == 200) {
          System.out.println(response.getStatusCodeValue());
            LinkedHashMap map = (LinkedHashMap) response.getBody();
            List list = (List) map.get("Resources");
            LinkedHashMap order = (LinkedHashMap) list.get(0);
            String shipmentNumber = (String) order.get("shipmentNumber");
            result = shipmentNumber;
            System.out.println("Shipment Number: " + order.get("shipmentNumber"));  
        }else {
            result = "It seems there is an issue with the order system. Try again";
            System.out.println("Error");
        }
        
        return result;
        
    }
 
    public static void main(String args[]){
        AWSManualRequest request = new AWSManualRequest();
        List<AWSKit> kits = new ArrayList<>();
        
        AWSKit kit = new AWSKit();
        kit.setKit("active");
        kit.setQuantity(30);
        
        kits.add(kit);
        
        request.setKits(kits);
        
        //createManualRequest(request);
        
    }
    
    /* GET */
    //@SuppressWarnings("unchecked")
//    private static void listAllUsers(){
//        System.out.println("Testing listAllUsers API-----------");
//         
//        RestTemplate restTemplate = new RestTemplate();
//        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
//         
//        if(usersMap!=null){
//            for(LinkedHashMap<String, Object> map : usersMap){
//                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
//            }
//        }else{
//            System.out.println("No user exist----------");
//        }
//    }
//     
//    /* GET */
//    private static void getUser(){
//        System.out.println("Testing getUser API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
//        System.out.println(user);
//    }
}
