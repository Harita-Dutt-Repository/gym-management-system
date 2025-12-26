


INSERT INTO gym_management_db.users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('john', 'john123', 'MEMBER');

INSERT INTO admin_details (user_id, full_name, phone, profile_image)
VALUES (7, 'Admin Instructor', '9876543210', 'admin.jpg');

INSERT INTO fee_packages(name, description,price,duration_months) VALUES ('Monthly', 'Monthly gym membership', 1500.00,1),('Quartely', '3 Months gym membership', 400.00,3);

INSERT INTO members (user_id, package_id, full_name, phone, join_date, status)
VALUES
(8, 1, 'John Doe', '9123456789', '2025-12-01', 'ACTIVE');


INSERT INTO payments (member_id, package_id, amount, payment_date, status, next_due_date)
VALUES
(2, 1, 1500.00, '2025-12-01', 'PAID', '2025-12-31');

INSERT INTO notifications (member_id, message)
VALUES
(2, 'Your membership expires on 31-Dec-2025');

INSERT INTO diet_plans (member_id, admin_id, diet_description)
VALUES
(2, 7, 'High protein diet with cardio on alternate days');

INSERT INTO supplements (name, price, stock)
VALUES
('Whey Protein', 2500.00, 20);


