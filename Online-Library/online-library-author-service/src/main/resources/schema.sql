DROP TABLE IF EXISTS AUTHORS;

CREATE TABLE AUTHORS
(
    ID        BIGINT IDENTITY PRIMARY KEY,
    FULL_NAME VARCHAR(300) NOT NULL
);

