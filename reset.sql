\c bloop sam;

DROP SCHEMA IF EXISTS test CASCADE;

CREATE SCHEMA test AUTHORIZATION sam;

SET search_path TO test;

CREATE EXTENSION postgis SCHEMA test;

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
	player_id bigint,
	location geography(point, 4326),
	CONSTRAINT flag_pk
		PRIMARY KEY (flag_id),
	CONSTRAINT flag_player_id_fk
		FOREIGN KEY (player_id)
		REFERENCES player (player_id)
);

CREATE TABLE captured_flags
(
	capturing_player_id bigint,
	defending_player_id bigint,
	CONSTRAINT captured_flags_pk
		PRIMARY KEY (capturing_player_id, defending_player_id),
	CONSTRAINT capturing_player_id_fk
		FOREIGN KEY (capturing_player_id)
		REFERENCES player (player_id),
	CONSTRAINT defending_player_id_fk
		FOREIGN KEY (defending_player_id)
		REFERENCES player (player_id)
);
