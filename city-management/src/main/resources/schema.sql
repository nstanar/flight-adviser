DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS comment;

-- Country

CREATE TABLE country(
    id BIGINT IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE country
    ADD CONSTRAINT country_name_unique_constraint UNIQUE (name);

-- City

CREATE TABLE city(
    id BIGINT IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(1000) NOT NULL,
    country_id BIGINT NOT NULL
);

ALTER TABLE city
ADD CONSTRAINT city_country_id_fkey FOREIGN KEY (country_id) REFERENCES country (id);

ALTER TABLE city
    ADD CONSTRAINT country_city_unique_constraint UNIQUE (country_id, name);

-- Comment

CREATE TABLE comment(
    id BIGINT IDENTITY PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(500) NOT NULL,
    city_id BIGINT NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    created_date BIGINT NOT NULL,
    modified_by VARCHAR(50) NOT NULL,
    modified_date BIGINT NOT NULL
);

ALTER TABLE comment
ADD CONSTRAINT comment_city_id_fkey FOREIGN KEY (city_id) REFERENCES city (id);