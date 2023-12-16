package org.duffy.ticketing.global.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BucketConfiguration {
    private static final int REFILL_COUNT = 3;
    private static final int BUCKET_SIZE = 3;

    @Bean
    public Bucket bucket() {
        Refill refill = Refill.intervally(REFILL_COUNT, Duration.ofSeconds(60));
        Bandwidth limit = Bandwidth.classic(BUCKET_SIZE, refill);
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
}
