package com.umiuni.shop.auth.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import com.umiuni.shop.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void whenLoginSuccess_thenRedirectToDashboard() throws Exception {
        OAuth2User principal = mock(OAuth2User.class);
        when(principal.getAttribute("email")).thenReturn("test@example.com");

        mockMvc.perform(get("/auth/login/success").principal((Principal) principal))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Redirecting to")));
    }
}
