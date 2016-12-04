\c bloop sam;

DROP SCHEMA IF EXISTS test CASCADE;

CREATE SCHEMA test AUTHORIZATION sam;

SET search_path TO test;

CREATE EXTENSION postgis;

CREATE TABLE player
(
	player_id bigserial,
	"name" text NOT NULL,
	CONSTRAINT player_pk
		PRIMARY KEY (player_id)
);

CREATE TABLE flag
(
	flag_id bigserial,
	player_id bigint NOT NULL,
	location geography(point, 4326) NOT NULL,
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
