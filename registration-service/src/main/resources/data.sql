INSERT INTO roles (role_id, authority)
VALUES (1, 'ADMIN'),
       (2, 'USER')
ON DUPLICATE KEY UPDATE authority=VALUES(authority);

INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head)
VALUES (1, 0, 0, 0, 0, 0)
ON DUPLICATE KEY UPDATE height=VALUES(height), shoe_size=VALUES(shoe_size), jacket_size=VALUES(jacket_size), pant_size=VALUES(pant_size), head=VALUES(head);

INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head)
VALUES (2, 179, 43, 50, 50, 44)
ON DUPLICATE KEY UPDATE height=VALUES(height), shoe_size=VALUES(shoe_size), jacket_size=VALUES(jacket_size), pant_size=VALUES(pant_size), head=VALUES(head);

INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head)
VALUES (3, 170, 42, 48, 48, 43)
ON DUPLICATE KEY UPDATE height=VALUES(height), shoe_size=VALUES(shoe_size), jacket_size=VALUES(jacket_size), pant_size=VALUES(pant_size), head=VALUES(head);

INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head)
VALUES (4, 180, 44, 52, 52, 45)
ON DUPLICATE KEY UPDATE height=VALUES(height), shoe_size=VALUES(shoe_size), jacket_size=VALUES(jacket_size), pant_size=VALUES(pant_size), head=VALUES(head);

INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head)
VALUES (5, 175, 43, 50, 50, 44)
ON DUPLICATE KEY UPDATE height=VALUES(height), shoe_size=VALUES(shoe_size), jacket_size=VALUES(jacket_size), pant_size=VALUES(pant_size), head=VALUES(head);


INSERT INTO performers (id, first_name, last_name, email, phone_nr, department, measurements_id)
VALUES (1, 'Admin', '', '', '', '', 1),
       (2, 'John', 'Doe', 'john@gmail.com', '123456789', 'Singer', 2),
        (3, 'Jane', 'Doe', 'jane@gmail.com', '987654321', 'Singer', 3),
        (4, 'Jack', 'Doe', 'Jack@hotmail.com', '123456789', 'Dancer', 4),
        (5, 'Jill', 'Doe', 'Jill@msn.com', '987654321', 'Dancer', 5)
ON DUPLICATE KEY UPDATE first_name=VALUES(first_name), last_name=VALUES(last_name), email=VALUES(email), phone_nr=VALUES(phone_nr), department=VALUES(department), measurements_id=VALUES(measurements_id);

-- admin pass is "password2", user pass is "password1"
INSERT INTO users (user_id, username, password, performer_id)
VALUES (1, 'admin', '$2a$12$bmGRGyuAcx9ejuWHrUdaL.mbqhDcU4kIfoa62CIF9CX0.vsQ5nOMK', 1),
       (2, 'user', '$2a$12$z.UuwHuzIAVlKY4ZNZYlUeTuz9f0C8pH3KpwgrWb2H1kM/JeMa6EG', 2),
        (3, 'jane', '$2a$12$z.UuwHuzIAVlKY4ZNZYlUeTuz9f0C8pH3KpwgrWb2H1kM/JeMa6EG', 3),
        (4, 'jack', '$2a$12$z.UuwHuzIAVlKY4ZNZYlUeTuz9f0C8pH3KpwgrWb2H1kM/JeMa6EG', 4),
        (5, 'jill', '$2a$12$z.UuwHuzIAVlKY4ZNZYlUeTuz9f0C8pH3KpwgrWb2H1kM/JeMa6EG', 5)
ON DUPLICATE KEY UPDATE username=VALUES(username), password=VALUES(password), performer_id=VALUES(performer_id);

INSERT INTO user_role_junction (role_id, user_id)
VALUES (1, 1),
       (2, 2),
        (2, 3),
        (2, 4),
        (2, 5)
ON DUPLICATE KEY UPDATE role_id=VALUES(role_id);
