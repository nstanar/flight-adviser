-- TODO: remove this when finished

INSERT INTO country(name) VALUES ('Serbia');


-- Belgrade
INSERT INTO city(name, description, country_id) VALUES ('Belgrade', 'Belgrade is the capital of Serbia.', currval('country_id_seq'));
INSERT INTO comment(title, description, city_id, created_by, created_date, modified_by, modified_date)
                                        VALUES
                   ('Amazing!', 'City itself is spectacular. Good people, nice food and beverages.', currval('city_id_seq'), 'user', 1578046129197 ,'user', 1578046129197);

-- Novi Sad
INSERT INTO city(name, description, country_id) VALUES ('Novi Sad', 'Novi Sad is the second largest city in Serbia.', currval('country_id_seq'));
INSERT INTO comment(title, description, city_id, created_by, created_date, modified_by, modified_date)
                                        VALUES
                   ('Really cool!', 'So many things to see. Open-minded people, great pubs and relaxing parks.', currval('city_id_seq'), 'user', 1578046129197 ,'user', 1578046129197);
INSERT INTO comment(title, description, city_id, created_by, created_date, modified_by, modified_date)
                                       VALUES
                  ('Chilling', 'Last year I was visiting Novi Sad with my spouse and kids. We had quite a time enjoying wonderful streets and restaurants.', currval('city_id_seq'), 'user', 1578046450382 ,'user', 1578046450382);
