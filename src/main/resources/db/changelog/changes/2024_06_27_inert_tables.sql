INSERT INTO items(created_at, description, name, price, updated_at)
VALUES ('2024-06-25 18:33:12.000000', 'phone', 'Iphone 14 pro', 375999,null),
       ('2024-06-25 18:35:12.000000', 'mobile phone', 'Iphone 15 pro', 475999,null),
       ('2024-06-25 19:02:12.000000', 'phone', 'Iphone 15 pro Max', 500000,null);

INSERT INTO permissions(role)
VALUES ('ROLE_STUDENT'),
       ('ROLE_TEACHER'),
       ('ROLE_ADMIN');

INSERT INTO users(email, full_name, password)
VALUES ('robten@gmail.com', 'Rob Ten', '$2a$10$z.qJdHC74o7CiMZAZZ5kSeOsiQq4Z2h7xZhQtPaqLvlX//mdvNbs2'),
       ('dasalibek@gmail.com', 'Alibek Dasibekov', '$2a$12$nwafx.tMuYy7MrSVaISJoeVFrK2jrSCQeygsRSCfqFlPM8KsQVFkm');

INSERT INTO  users_permissions(user_id, permissions_id)
VALUES (1,1),
       (1,2),
       (1,3),
       (2,1);