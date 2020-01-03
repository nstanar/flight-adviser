-- User

create sequence fa_user_id_seq INCREMENT 1 START 1;

CREATE TABLE fa_user(
    id BIGINT DEFAULT nextval('fa_user_id_seq') PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    value_sec VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

ALTER TABLE fa_user
    ADD CONSTRAINT fa_user_username_unique_constraint UNIQUE (username);


-- Role

create sequence fa_role_id_seq INCREMENT 1 START 1;

create TABLE fa_role (
    id bigint DEFAULT nextval('fa_role_id_seq') PRIMARY KEY,
    name character varying(30) NOT NULL
);

ALTER TABLE fa_role
    ADD CONSTRAINT fa_role_name_unique_constraint UNIQUE (name);


-- User's role

create sequence fa_user_role_id_seq INCREMENT 1 START 1;

create TABLE fa_user_role(
    id bigint  DEFAULT nextval('fa_user_role_id_seq') PRIMARY KEY,
    user_id,
    role_id
);

ALTER TABLE fa_user_role
ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES fa_user (id);

ALTER TABLE fa_user_role
ADD CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES fa_role (id);

ALTER TABLE fa_user_role
    ADD CONSTRAINT fa_user_role_unique_constraint UNIQUE (user_id, role_id);


-- OAuth2
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255),
  authentication LONGVARBINARY);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);