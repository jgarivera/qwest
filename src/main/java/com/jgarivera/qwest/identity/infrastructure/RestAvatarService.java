package com.jgarivera.qwest.identity.infrastructure;

import com.jgarivera.qwest.identity.application.AvatarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Map;
import java.util.Optional;

@Service
class RestAvatarService implements AvatarService {

    private final Logger logger = LoggerFactory.getLogger(RestAvatarService.class);

    /**
     * Applied to all users' avatars to achieve desired look-and-feel in the app.
     */
    private String globalCosmeticParams = "&radius=10";

    private final RestClient restClient;
    private final AvatarSettings settings;

    public RestAvatarService(RestClient restClient, AvatarSettings settings) {
        this.restClient = restClient;
        this.settings = settings;
    }

    /**
     * Retrieves the image data of a user's avatar.
     *
     * @param username username of the user
     * @return optional byte array of image data
     */
    public Optional<byte[]> retrieveImageData(String username) {
        var params = Map.of(
                "style", settings.getDefaultStyle(),
                "username", username
        );

        byte[] imageData = null;

        try {
            imageData = restClient.get()
                    .uri(settings.getApiUrl() + globalCosmeticParams, params)
                    .retrieve()
                    .body(byte[].class);
        } catch (RestClientException exception) {
            logger.error("Failed to retrieve image data for user avatar");
        }

        return Optional.ofNullable(imageData);
    }

    void clearGlobalCosmeticParams() {
        globalCosmeticParams = "";
    }
}
