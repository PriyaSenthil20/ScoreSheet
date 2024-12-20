BEGIN TRANSACTION;

--DROP EXISTING
DROP TABLE IF EXISTS games,players,rounds,scores,users;
-- Create the 'Games' table to store game information
CREATE TABLE games (
    game_id SERIAL PRIMARY KEY,
    game_name VARCHAR(255) NOT NULL,
    game_date VARCHAR(50) NOT NULL
);

-- Create the 'Players' table to store player information
CREATE TABLE players (
    player_id SERIAL PRIMARY KEY,
    game_id INT REFERENCES Games(game_id) ON DELETE CASCADE,
    player_name VARCHAR(255) NOT NULL,
	is_active BOOLEAN DEFAULT true
);

-- Create the 'Rounds' table to store round information
CREATE TABLE rounds (
    round_id SERIAL PRIMARY KEY,
    game_id INT REFERENCES Games(game_id) ON DELETE CASCADE,
    round_number INT NOT NULL,
    round_name VARCHAR(255)
);

-- Create the 'Scores' table to store player scores for each round
CREATE TABLE scores (
    score_id SERIAL PRIMARY KEY,
    round_id INT REFERENCES Rounds(round_id) ON DELETE CASCADE,
    player_id INT REFERENCES Players(player_id) ON DELETE CASCADE,
	player_name VARCHAR(255) NOT NULL,
    score INT NOT NULL,
    note VARCHAR(255)
);
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

commit transaction;
