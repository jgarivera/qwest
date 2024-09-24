package com.jgarivera.qwest.identity.presentation;

import com.jgarivera.qwest.identity.application.UserService;
import com.jgarivera.qwest.shared.application.TestSecurityConfiguration;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(RegistrationPageController.class)
@Import(TestSecurityConfiguration.class)
class RegistrationPageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @BeforeEach
    void setUp() {
        doNothing().when(userService)
                .register(anyString(), nullable(String.class), anyString(), anyString(), anyString(), anyString());
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
                .andExpect(redirectedUrl("/login"))
                .andExpect(flash().attribute("registered", true));

        verify(userService, times(1))
                .register(anyString(), nullable(String.class), anyString(), anyString(), anyString(), anyString());
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

        verify(userService, never())
                .register(anyString(), nullable(String.class), anyString(), anyString(), anyString(), anyString());
    }
}
