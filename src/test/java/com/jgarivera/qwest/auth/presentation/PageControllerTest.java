package com.jgarivera.qwest.auth.presentation;

import com.jgarivera.qwest.TestSecurityConfiguration;
import com.jgarivera.qwest.auth.domain.User;
import com.jgarivera.qwest.auth.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PageController.class)
@Import(TestSecurityConfiguration.class)
class PageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @Test
    void it_gets_login_page() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/login"))
                .andExpectAll(
                        content().string(containsString("Log in")),
                        content().string(containsString("Username")),
                        content().string(containsString("Password"))
                );
    }

    @Test
    void it_succeeds_to_logs_in() throws Exception {
        mockMvc.perform(formLogin().user("testuser").password("password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated());
    }

    @Test
    void it_fails_to_log_in() throws Exception {
        mockMvc.perform(formLogin().user("nonexistent").password("password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(unauthenticated());
    }

    @Test
    void it_gets_register_page() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/register"))
                .andExpect(content().string(containsString("Register")));
    }

    @ParameterizedTest
    @CsvSource({
            "Juan,,Dela Cruz,juan@example.com,juandelacruz,password",
            "Juan,Pedro,Dela Cruz,juan@example.com,juandelacruz,password",
    })
    void it_succeeds_to_register(@AggregateWith(RegistrationFormAggregator.class) RegistrationForm form) throws Exception {
        mockMvc.perform(post("/register")
                        .with(csrf())
                        .param("firstName", form.getFirstName())
                        .param("middleName", form.getMiddleName())
                        .param("lastName", form.getLastName())
                        .param("email", form.getEmail())
                        .param("username", form.getUsername())
                        .param("password", form.getPassword())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @ParameterizedTest
    @CsvSource({
            ",,Dela Cruz,juan@example.com,juandelacruz,password",
            "Juan,Pedro,,juan@example.com,juandelacruz,password",
            "Juan,,Dela Cruz,notvalidemail,juandelacruz,password",
            "Juan,,Dela Cruz,juan@example.com,juan,password",
            "Juan,,Dela Cruz,juan@example.com,juandelacruz,",
    })
    void it_fails_to_register(@AggregateWith(RegistrationFormAggregator.class) RegistrationForm form) throws Exception {
        mockMvc.perform(post("/register")
                        .with(csrf())
                        .param("firstName", form.getFirstName())
                        .param("middleName", form.getMiddleName())
                        .param("lastName", form.getLastName())
                        .param("email", form.getEmail())
                        .param("username", form.getUsername())
                        .param("password", form.getPassword())
                )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());

        verify(userRepository, never()).save(any(User.class));
    }
}
