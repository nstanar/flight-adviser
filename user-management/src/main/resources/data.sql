INSERT INTO oauth_client_details VALUES('fa-client', '', '$2a$10$9BG9KwMu3//irQDT5J0gnOLzX8QLa3gTQyJDrbc79KnS8s97.hcki', 'any', 'password,refresh_token,client_credentials', '', '', 3600, 2147483647, null, '');


INSERT INTO fa_role(name) VALUES ('ADMIN');
INSERT INTO fa_user(username, value_sec, first_name, last_name) VALUES('admin', '$2a$10$SzUuCX5lseWIuKZ0cQhof.wiwXXgOeQfT7vQpqrunaaXVLmibhMBK', 'Admin', 'Admin');
INSERT INTO fa_user_role(user_id, role_id) VALUES (currval("fa_user_id_seq"), currval("role_id_seq)");

INSERT INTO fa_role(name) VALUES ('REGULAR_USER');