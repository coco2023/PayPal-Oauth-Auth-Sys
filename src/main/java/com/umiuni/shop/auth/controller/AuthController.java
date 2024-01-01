package com.umiuni.shop.auth.controller;

import com.umiuni.shop.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!");
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("This is the Home Page.");
    }

    @GetMapping("/login/success")
    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // Implement logic to handle successful login
        // For example, redirect users based on their roles or attributes
        // This might involve checking attributes in the oAuth2User
        String role = oAuth2User.getAttribute("role"); // Example attribute
        if ("supplier".equals(role)) {
            // Redirect to supplier dashboard
            return ResponseEntity.ok("Redirecting to supplier dashboard...");
        } else {
            // Redirect to customer dashboard
            return ResponseEntity.ok("Redirecting to customer dashboard...");
        }
    }

    @GetMapping("/login/failure")
    public String loginFailure() {
        return "Login failed. Please try again.";
    }

//    @GetMapping("/login/success/v1")
//    public ResponseEntity<?> loginSuccess1(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        // Determine if the user is a supplier or customer
//        String userType = determineUserType(oAuth2User.getAttributes());
//
//        if ("supplier".equals(userType)) {
//            userService.registerOrUpdateSupplier(oAuth2User);
//            return ResponseEntity.ok("Login successful - Redirecting to supplier dashboard..." + oAuth2User.getAttribute("name"));
//        } else {
//            userService.registerOrUpdateCustomer(oAuth2User);
//            return ResponseEntity.ok("Login successful - Redirecting to customer dashboard..." + oAuth2User.getAttribute("name"));
//        }
//    }
//
//    private String determineUserType(Map<String, Object> attributes) {
//        // Implement your logic to determine user type
//        return "supplier"; // or "supplier"
//    }
}
