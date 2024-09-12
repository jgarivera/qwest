package com.jgarivera.qwest;

import com.jgarivera.qwest.shared.application.TestSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Controller")
})
@Import(TestSecurityConfiguration.class)
class ViewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void it_gets_home_page() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/index"))
                .andExpect(content().string(containsString("Welcome")));
    }
}
