package com.jgarivera.qwest.identity.infrastructure.http;

import com.jgarivera.qwest.identity.application.AvatarSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(RestAvatarService.class)
class RestAvatarServiceTest {

    @MockBean
    AvatarSettings avatarSettings;

    @Autowired
    RestAvatarService client;
    @Autowired
    MockRestServiceServer server;

    @BeforeEach
    void setUp() {
        when(avatarSettings.getApiUrl()).thenReturn("http://localhost:1337/{style}/png?user={username}");
        when(avatarSettings.getDefaultStyle()).thenReturn("basic");

        ReflectionTestUtils.setField(client, "globalCosmeticParams", "");
    }

    @Test
    void it_retrieves_image_data() {
        server.expect(requestTo("http://localhost:1337/basic/png?user=juantamad"))
                .andRespond(withSuccess("data", MediaType.IMAGE_PNG));

        Optional<byte[]> imageData = client.retrieveImageData("juantamad");

        assertThat(imageData.isPresent()).isTrue();
    }

    @Test
    void it_retrieves_no_image_data() {
        server.expect(requestTo("http://localhost:1337/basic/png?user=juantamad"))
                .andRespond(withServerError());

        Optional<byte[]> imageData = client.retrieveImageData("juantamad");

        assertThat(imageData.isPresent()).isFalse();
    }

    @Test
    void it_suppresses_thrown_exception() {
        server.expect(requestTo("http://localhost:1337/basic/png?user=juantamad"))
                .andRespond(withServerError());

        assertThatNoException().isThrownBy(() -> client.retrieveImageData("juantamad"));
    }

    @TestConfiguration
    static class Configuration {

        @Bean
        RestClient restClient(RestClient.Builder builder) {
            return builder.build();
        }
    }
}
