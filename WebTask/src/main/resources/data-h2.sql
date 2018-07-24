INSERT INTO ROLE(id, name) VALUES (1, 'ADMIN'), (2, 'USER');


INSERT INTO T_USER(user_id, email, password, role_id, username) VALUES
(1, 'ajax@gmail.com', 'password', 1, 'ajax'),
(2, 'ivan@gmail.com', 'password', 1, 'Ivan'),
(3, 'georgi@gmail.com', 'password', 2, 'Georgi'),
(4, 'kaloi@gmail.com', 'password', 2, 'Kaloyan'),
(5, 'stefan@gmail.com', 'password', 2, 'Stefan'),
(6, 'bossman@gmail.com', 'password', 1, 'Viktor'),
(7, 'fin@gmail.com', 'password', 2, 'Fin');