package com.jgarivera.qwest.auth.presentation;

import com.jgarivera.qwest.auth.infrastructure.AvatarService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
class AvatarController {

    private final AvatarService avatarService;

    AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping(path = "/avatar", produces = "image/svg+xml")
    ResponseEntity<byte[]> avatar(@RequestParam("username") String username) {
        Optional<byte[]> imageData = avatarService.retrieveImageData(username);

        return imageData.map(bytes -> ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(24, TimeUnit.HOURS))
                .body(bytes)
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
