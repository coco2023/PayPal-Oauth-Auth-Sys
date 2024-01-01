package com.umiuni.shop.auth.service;

import com.umiuni.shop.auth.entity.Customer;
import com.umiuni.shop.auth.entity.Supplier;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
    Supplier registerOrUpdateSupplier(OAuth2User oAuth2User);
    Customer registerOrUpdateCustomer(OAuth2User oAuth2User);
}
