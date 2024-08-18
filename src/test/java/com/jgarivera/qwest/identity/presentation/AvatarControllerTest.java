package com.jgarivera.qwest.identity.presentation;

import com.jgarivera.qwest.TestSecurityConfiguration;
import com.jgarivera.qwest.identity.application.AvatarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AvatarController.class)
@Import(TestSecurityConfiguration.class)
class AvatarControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AvatarService avatarService;

    @Test
    void it_gets_avatar() throws Exception {
        when(avatarService.retrieveImageData("joserizal"))
                .thenReturn(Optional.of(new byte[]{}));

        mockMvc.perform(get("/avatar?username={username}", "joserizal"))
                .andExpect(status().isOk());
    }

    @Test
    void it_gets_no_avatar() throws Exception {
        when(avatarService.retrieveImageData("joserizal"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/avatar?username={username}", "joserizal"))
                .andExpect(status().isNotFound());
    }
}
