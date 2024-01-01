package com.umiuni.shop.auth.entity;

import com.umiuni.shop.auth.service.UserService;
import com.umiuni.shop.auth.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class UserPrincipal implements UserDetails, OAuth2User {
    private Long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    // Constructor
    public UserPrincipal(Long id, String email, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    public static UserPrincipal create(OAuth2User oAuth2User, UserServiceImpl userService) {
        String userType = determineUserType(oAuth2User.getAttributes());

        Long id;
        String email = oAuth2User.getAttribute("email");
        Collection<? extends GrantedAuthority> authorities;

        if ("supplier".equals(userType)) {
            Supplier supplier = userService.registerOrUpdateSupplier(oAuth2User);
            id = supplier.getId();
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
        } else {
            Customer customer = userService.registerOrUpdateCustomer(oAuth2User);
            id = customer.getId();
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }

        return new UserPrincipal(id, email, authorities, oAuth2User.getAttributes());
    }

    private static String determineUserType(Map<String, Object> attributes) {
        // Implement logic to determine if the user is a Supplier or Customer
        // This is a placeholder example
        return "supplier"; // or "customer"
    }

    // UserDetails and OAuth2User methods
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getPassword() {
        // Return null or appropriate password if applicable
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return this.getUsername();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.util.Collection;
//
//public class UserPrincipal implements UserDetails {
//    private Long id;
//    private String email;
//    private String password; // Include this only if you manage passwords in your system
//    private Collection<? extends GrantedAuthority> authorities;
//
//    // Constructor
//    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    // Getters and setters...
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password; // Return null or the actual password if applicable
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//
//    // Other overridden methods...
//
//    // Static method for creating UserPrincipal
//    public static UserPrincipal create(User user, Collection<? extends GrantedAuthority> authorities) {
//        return new UserPrincipal(
//                user.getId(),
//                user.getEmail(),
//                user.getPassword(), // Or null if password management isn't needed
//                authorities
//        );
//    }
//}
//
