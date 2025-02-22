package com.bs.servicecustomer.controller;


import com.bs.servicecustomer.entity.TokenEntity;
import com.bs.servicecustomer.service.CustomerService;
import com.bs.servicecustomer.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customerDashboard", produces = {"application/json"})
    @ResponseBody
    public Map<String, Object> getCustomer(@RequestBody TokenEntity token){
        try {

            EncryptUtil des = new EncryptUtil("9ba45bfd500642328ec03ad8ef1b6e75", "utf-8");
            String tokenString = des.decode(token.getToken());
            String customerId = tokenString.split("-")[0];
//            System.out.println("==================="+customerId);
            Map<String, Object> resultMap =  customerService.findCustomerById(customerId);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
