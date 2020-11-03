SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes(
id int PRIMARY KEY auto_increment,
heroName VARCHAR,
heroPower VARCHAR,
heroWeakness VARCHAR,
squadId INTEGER,
heroAge INTEGER,
);

CREATE TABLE IF NOT EXISTS squads(
id int PRIMARY KEY auto_increment,
squadName VARCHAR,
squadPurpose VARCHAR,
squadNumber INTEGER,
squadGroup VARCHAR,
);

