CREATE TABLE challenge_visibilities (
    id SMALLINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE challenges
ADD visibility SMALLINT NOT NULL;

ALTER TABLE challenges
ADD FOREIGN KEY (visibility) REFERENCES challenge_visibilities (id);
