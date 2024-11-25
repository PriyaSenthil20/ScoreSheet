BEGIN TRANSACTION;

--DROP EXISTING
DROP TABLE IF EXISTS team, player, scoresheet;

CREATE TABLE player (
	player_id int NOT NULL,
	player_name varchar(50) UNIQUE,
	recent_score int NOT NULL,
	player_total_score int NOT NULL DEFAULT 0,
	is_active boolean DEFAULT true,
	
	CONSTRAINT pk_player_id PRIMARY KEY (player_id)
);

CREATE TABLE team (
	team_id int NOT NULL,
	team_name varchar(50) UNIQUE,
	player_id int NOT NULL,
	is_active boolean DEFAULT true,
	team_total_score int NOT NULL DEFAULT 0,

	CONSTRAINT pk_team_id PRIMARY KEY (team_id),
	CONSTRAINT fk_team_player_id FOREIGN KEY (player_id) REFERENCES player (player_id)
);

CREATE TABLE scoresheet (
	scoresheet_id serial,
	game_id int NOT NULL,
	player_id int NOT NULL,
	scores int NOT NULL,
	player_total_score int NOT NULL DEFAULT 0,
	
	CONSTRAINT pk_scoresheet PRIMARY KEY (scoresheet_id, game_id),
	CONSTRAINT fk_scoresheet_player_id FOREIGN KEY (player_id) REFERENCES player (player_id)
	
);

create TABLE teamplayer (
 	team_id int NOT NULL,
	player_id int NOT NULL,
	
	CONSTRAINT fk_tp_team_id FOREIGN KEY (team_id) REFERENCES team (team_id),
	CONSTRAINT fk_tp_player_id FOREIGN KEY (player_id) REFERENCES player (player_id)
);
COMMIT;


