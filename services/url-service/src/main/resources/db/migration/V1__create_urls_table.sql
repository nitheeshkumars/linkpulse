
CREATE SEQUENCE urls_id_seq START WITH 238328 INCREMENT BY 1;
CREATE TABLE urls (
    id BIGINT PRIMARY KEY DEFAULT nextval('urls_id_seq'), 
    short_code VARCHAR(20) NOT NULL,
    long_url TEXT NOT NULL,
    user_id UUID,
    is_custom BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    expired_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,

    CONSTRAINT uq_urls_short_code UNIQUE (short_code),
    CONSTRAINT ch_urls_short_code_format CHECK (short_code ~ '^[0-9a-zA-Z_-]{3,20}$'),
    CONSTRAINT ch_urls_short_code_length CHECK (char_length(long_url) <= 2048)
);

ALTER SEQUENCE urls_id_seq OWNED BY urls.id;

CREATE INDEX idx_urls_user_created 
    ON urls (user_id, created_at DESC, id DESC)
    WHERE deleted_at IS NULL;

CREATE INDEX idx_urls_expired_at
    ON urls (expired_at)
    WHERE expired_at IS NOT NULL AND deleted_at IS NULL;