INSERT INTO oauth_client_details VALUES('fa-client', '', '$2a$10$9BG9KwMu3//irQDT5J0gnOLzX8QLa3gTQyJDrbc79KnS8s97.hcki', 'any', 'password,refresh_token,client_credentials', '', '', 3600, 2147483647, null, '');


INSERT INTO fa_role(name) VALUES ('ROLE_ADMIN');
INSERT INTO fa_user(username, value_sec, first_name, last_name, created_by, created_date, modified_by, modified_date)
                            VALUES
                            ('admin', '$2a$10$SzUuCX5lseWIuKZ0cQhof.wiwXXgOeQfT7vQpqrunaaXVLmibhMBK', 'Admin', 'Admin', 'system', 1578046129197 ,'system', 1578046129197);
INSERT INTO fa_user_role(user_id, role_id) VALUES (1, 1);

INSERT INTO fa_role(name) VALUES ('ROLE_REGULAR_USER');