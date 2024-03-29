package com.umiuni.shop.auth.controller;

import com.umiuni.shop.auth.JwtTokenProvider;
import com.umiuni.shop.auth.entity.Supplier;
import com.umiuni.shop.auth.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!");
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("This is the Home Page.");
    }

    @GetMapping("/login/success")
    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, OAuth2AuthenticationToken authentication, HttpServletResponse response) throws IOException {

        log.info("* oAuth2User : {}, authentication: {} ", oAuth2User, authentication);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name"); // Adjust according to the data provided by PayPal

        Supplier supplier = userService.registerOrUpdateSupplier(oAuth2User); // Implement this method as per your requirement

        // Implement logic to handle successful login
        // For example, redirect users based on their roles or attributes
        // This might involve checking attributes in the oAuth2User
        String role = oAuth2User.getAttribute("role"); // Example attribute
        // Create JWT token
        String token = jwtTokenProvider.createToken(authentication, supplier.getId());
        log.info("token, {}", token);

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
