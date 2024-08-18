package com.jgarivera.qwest.identity.application;

import java.util.Optional;

public interface AvatarService {

    Optional<byte[]> retrieveImageData(String username);
}
