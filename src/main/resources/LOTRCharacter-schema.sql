DROP TABLE IF EXISTS LOTRCharacter CASCADE;
CREATE TABLE LOTRCharacter
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    age  INTEGER      NOT NULL,
    race VARCHAR(255),
    PRIMARY KEY (id)
);
