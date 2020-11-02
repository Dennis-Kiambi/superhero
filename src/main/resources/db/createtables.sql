SET MODE PostgreSQL;

create TABLE IF NOT EXISTS heroes(
id int PRIMARY KEY auto_increment,
heroName VARCHAR,
heroPower VARCHAR,
heroWeakness VARCHAR,
heroGender VARCHAR,
squadId INTEGER,
heroAge INTEGER,
);

create TABLE IF NOT EXISTS squads(
id int PRIMARY KEY auto_increment,
squadName VARCHAR,
squadPurpose VARCHAR,
squadNumber INTEGER,
squadGroup VARCHAR,
);

