\set user sam
\set schema test

\c bloop :"user"

DROP SCHEMA IF EXISTS :"schema" CASCADE;

CREATE SCHEMA test AUTHORIZATION :"user";

SET search_path TO :"schema";

CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE player
(
    player_id bigserial,
    "name" text NOT NULL,
    google_play_id text NOT NULL,
    firebase_token text,
    CONSTRAINT player_pk
        PRIMARY KEY (player_id)
);

CREATE TABLE flag
(
    flag_id bigserial,
    player_id bigint NOT NULL,
    "location" geography(point, 4326) NOT NULL,
    color int NOT NULL,
    time_placed timestamp with time zone NOT NULL DEFAULT now(),
    is_captured boolean NOT NULL DEFAULT FALSE,
    time_captured timestamp with time zone,
    capturing_player_id bigint,
    CONSTRAINT flag_pk
        PRIMARY KEY (flag_id),
    CONSTRAINT flag_player_id_fk
        FOREIGN KEY (player_id)
        REFERENCES player (player_id),
    CONSTRAINT capturing_player_id_fk
        FOREIGN KEY (capturing_player_id)
        REFERENCES player (player_id)
);
