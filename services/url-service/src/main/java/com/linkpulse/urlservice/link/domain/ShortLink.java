package com.linkpulse.urlservice.link.domain;

import java.time.Instant;
import java.util.UUID;

public record ShortLink(
    Long id,
    String ShortCode,
    String longUrl,
    UUID ownerId,
    boolean custom,
    Instant createdAt,
    Instant expiresAt,
    Instant deletedAt
){
    
    public boolean isExpired(Instant now){
        return expiresAt != null && expiresAt.isBefore(now);
    }

    public boolean isDeleted(){
        return deletedAt != null;
    }

}
