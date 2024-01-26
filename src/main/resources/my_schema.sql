CREATE DATABASE character_database;
USE character_database;

CREATE TABLE user {
    user_id BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
};

CREATE TABLE character {
    character_id BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    level TINYINT NOT NULL,
    health_points SMALLINT NOT NULL,
    armor_points SMALLINT NOT NULL,
    power_points SMALLINT NOT NULL,
    class BIGINT NOT NULL 
        FOREIGN KEY REFERENCES class(class_id),
    attributes BIGINT NOT NULL 
        FOREIGN KEY REFERENCES attributes(attributes_id),
    user BIGINT NOT NULL 
        FOREIGN KEY REFERENCES user(user_id)
};

CREATE TABLE class {
    class_id BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    base_fortune SMALLINT NOT NULL,
    base_hp TINYINT NOT NULL,
    base_pp TINYINT NOT NULL,
    base_attributes BIGINT NOT NULL
        FOREIGN KEY REFERENCES attributes(attributes_id),
};

--CREATE TABLE active_ability {};

--CREATE TABLE passive_ability {};

CREATE TABLE attributes {
    attributes_id BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
    charisma TINYINT NOT NULL DEFAULT 5,
    constitution TINYINT NOT NULL DEFAULT 5,
    dexterity TINYINT NOT NULL DEFAULT 5,
    intelligence TINYINT NOT NULL DEFAULT 5,
    strenght TINYINT NOT NULL DEFAULT 5,
    wisdom TINYINT NOT NULL DEFAULT 5
};

--CREATE TABLE item {};