package com.spring.security.jwt.controller;

import com.spring.security.jwt.model.Customer;
import com.spring.security.jwt.model.JWTResponse;
import com.spring.security.jwt.service.UserService;
import com.spring.security.jwt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer cus = new Customer();
        cus.setCustomerId(customer.getCustomerId());
        cus.setCustomerName(customer.getCustomerName());
        System.out.println("Customer details added successfully : " +cus.toString());
        return new ResponseEntity<>(cus, HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestHeader HttpHeaders headers) throws Exception {
        //authenticate(authRequest.getUserName(), authRequest.getPassword());
        final UserDetails userDetails;
        if(null != headers.get("clientId")) {
            userDetails = userService.loadUserByUsername(headers.get("clientId").get(0));
        } else {
            String authorization = headers.get("authorization").get(0);
            byte[] actualByte = Base64.getDecoder().decode(authorization.substring(6));
            String[] split = new String(actualByte).split(":");
            userDetails = userService.loadUserByUsername(split[0]);
        }
        String expires = String.valueOf(60 * 60 * 1);           // setting token expiry time for 1 hr (3600 seconds)
        final String access_token = jwtUtil.generateToken(userDetails.getUsername(), expires);
        System.out.println("Token Generated : " +access_token);
        return ResponseEntity.ok(new JWTResponse(access_token, "Bearer", expires));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("Invalid Credentials " +e);
        }
    }
}
