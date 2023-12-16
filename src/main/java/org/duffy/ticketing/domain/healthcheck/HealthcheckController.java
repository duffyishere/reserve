package org.duffy.ticketing.domain.healthcheck;

import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthcheckController {
    private final Bucket bucket;

    @GetMapping
    public ResponseEntity<Boolean> healthcheck() {
        return ResponseEntity.ok(bucket.getAvailableTokens() != 0);
    }
}
