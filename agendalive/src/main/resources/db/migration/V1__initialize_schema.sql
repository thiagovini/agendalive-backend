create table live (
    id                  VARCHAR NOT NULL,
    live_name           VARCHAR NOT NULL,
    channel_name        VARCHAR NOT NULL,
    live_date           TIMESTAMP,
    live_link           VARCHAR,
    registration_date   TIMESTAMP,
    primary key (id)
);
