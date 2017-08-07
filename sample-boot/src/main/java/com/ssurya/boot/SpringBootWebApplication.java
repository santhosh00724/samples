/**
 * 
 */
package com.ssurya.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ssuryade
 *
 */
@SpringBootApplication
@RestController
public class SpringBootWebApplication extends SpringBootServletInitializer {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Sample application for docker using netbeans";
    }
    
    @RequestMapping(path = "/post", method = RequestMethod.POST)
    @ResponseBody
    public String postMethod(@RequestBody AWSManualRequest request) {
        System.out.println("Received request --> " + request);
        ORSRestClient client = new ORSRestClient();
        
        return client.createManualRequest(request);
    }
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

}
