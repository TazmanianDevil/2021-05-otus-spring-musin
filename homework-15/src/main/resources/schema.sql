DROP TABLE IF EXISTS BOOKS;

CREATE TABLE BOOKS
(
    ID      BIGINT GENERATED BY DEFAULT AS IDENTITY  PRIMARY KEY,
    TITLE   VARCHAR(255) NOT NULL,
    AUTHOR  VARCHAR(255) NOT NULL

);
