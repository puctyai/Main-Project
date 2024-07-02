CREATE TABLE items(
                      id SERIAL PRIMARY KEY,
                      created_at timestamp(6),
                      updated_at timestamp(6),
                      description text,
                      name varchar(255),
                      price int
);

CREATE TABLE permissions(
                            id SERIAL PRIMARY KEY,
                            role varchar(255)
);

CREATE TABLE users(
                      id SERIAL PRIMARY KEY,
                      email varchar(255),
                      full_name varchar(255),
                      password varchar(255)
);

CREATE TABLE users_permissions(
                                  user_id int,
                                  permissions_id int
);

ALTER TABLE users_permissions
    ADD CONSTRAINT fk_users_permissions_users
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON UPDATE CASCADE;

ALTER TABLE users_permissions
    ADD CONSTRAINT fk_users_permissions_permission
        FOREIGN KEY (permissions_id)
            REFERENCES permissions (id)
            ON UPDATE CASCADE;