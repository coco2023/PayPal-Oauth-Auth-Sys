package com.umiuni.shop.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void whenAccessProtectedRouteWithoutAuth_thenRedirectToLogin() throws Exception {
//		mockMvc.perform(get("/protected-route"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrlPattern("**/login"));
		mockMvc.perform(get("/protected-route")) // Replace "/protected-route" with an actual protected URL
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("**/oauth2/authorization/paypal"));
	}

}
