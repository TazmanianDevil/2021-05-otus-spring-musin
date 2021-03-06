DROP TABLE IF EXISTS GENRES;

CREATE TABLE GENRES
(
    ID   BIGINT IDENTITY PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS AUTHORS;

CREATE TABLE AUTHORS
(
    ID        BIGINT IDENTITY PRIMARY KEY,
    FULL_NAME VARCHAR(300) NOT NULL
);

DROP TABLE IF EXISTS BOOKS;

CREATE TABLE BOOKS
(
    ID        BIGINT IDENTITY PRIMARY KEY,
    TITLE     VARCHAR(255) NOT NULL,
    AUTHOR_ID BIGINT       NOT NULL,
    GENRE_ID  BIGINT       NOT NULL,
    CONSTRAINT AUTHOR_ID_FK FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID),
    CONSTRAINT GENRE_ID_FL FOREIGN KEY (GENRE_ID) REFERENCES GENRES (ID)
);

