INSERT INTO vehicles (vehicle_id, dtype, plate, model, color) VALUES (2000, 'CAR', 'HB20', 'Sedan', 'Rosa');

INSERT INTO users (user_id, dtype, username, first_name, last_name) VALUES (1000, 'ADMIN', 'adminUser', 'Admin', 'User');

INSERT INTO tickets (ticket_id, vehicle_id, user_id, start_time, end_time)
VALUES (1000, (SELECT vehicle_id FROM vehicles WHERE vehicle_id = 2000),
(SELECT user_id FROM users WHERE user_id = 1000), '2023-01-01 10:00:00', null);