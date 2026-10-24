package com.linkpulse.urlservice.link.domain;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ShortLinkTest
 */
class ShortLinkTest {

    private static final Instant NOW = Instant.parse("2026-01-01T00:00:00Z");

    @Test
    void neverExpiresWithoutAnExpireDate() {
        assertThat(link(null, null).isExpired(NOW)).isFalse();
    }

    @Test
    void isExpiredOnceTheExpiryDateHasPassed() {
        assertThat(link(NOW.minusSeconds(1), null).isExpired(NOW)).isTrue();
    }

    @Test
    void isNotExpiredOnceBeforeExpiredDatePassed() {
        assertThat(link(NOW.plusSeconds(1), null).isExpired(NOW)).isFalse();
    }

    @Test
    void isDeletedSetOnlyWhenDeleteAtSet() {
        assertThat(link(null, null).isDeleted()).isFalse();
        assertThat(link(null, NOW).isDeleted()).isTrue();
    }

    private ShortLink link(Instant expiresAt, Instant deletedAt) {
        return new ShortLink(1L, "abc123", "https://example.com", null, false, NOW, expiresAt, deletedAt);
    }

}