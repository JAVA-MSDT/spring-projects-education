-- products --
INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'COTTON', 'MALE', 'M', 'ADULT', 'Casual cotton shirt', '{"image1.jpg", "image2.jpg"}', 10);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'JEANS', 'MALE', 'L', 'ADULT', 'Blue denim jeans', '{"image3.jpg"}', 15);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'SILK', 'FEMALE', 'S', 'ADULT', 'Elegant silk evening dress', '{"image4.jpg", "image5.jpg"}', 5);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'COTTON', 'MALE', 'XL', 'ADULT', 'Comfortable cotton t-shirt', '{"image6.jpg"}', 20);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'OTHER', 'FEMALE', 'M', 'CHILD', 'Colorful childrens shirt', '{"image7.jpg"}', 12);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'COTTON', 'FEMALE', 'XS', 'ADULT', 'Light cotton trousers', '{"image8.jpg"}', 18);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'OTHER', 'FEMALE', 'M', 'ADULT', 'Floral printed dress', '{"image9.jpg"}', 7);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'COTTON', 'FEMALE', 'XXS', 'CHILD', 'Graphic t-shirt for kids', '{"image10.jpg", "image11.jpg"}', 25);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'JEANS', 'MALE', 'XXL', 'ADULT', 'Denim shirt', '{"image12.jpg"}', 8);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'SILK', 'FEMALE', 'S', 'ADULT', 'Formal silk trousers', '{"image13.jpg", "image14.jpg"}', 4);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'COTTON', 'FEMALE', 'L', 'ADULT', 'Summer cotton dress', '{"image15.jpg"}', 30);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'JEANS', 'MALE', 'M', 'CHILD', 'Trendy kids denim t-shirt', '{"image16.jpg"}', 9);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'SILK', 'FEMALE', 'XL', 'ADULT', 'Office silk shirt', '{"image17.jpg"}', 6);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'OTHER', 'MALE', 'L', 'ADULT', 'Lightweight outdoor trousers', '{"image18.jpg"}', 14);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'JEANS', 'FEMALE', 'XXS', 'CHILD', 'Denim jumper dress', '{"image19.jpg"}', 11);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'OTHER', 'FEMALE', 'M', 'ADULT', 'Stylish designer t-shirt', '{"image20.jpg"}', 22);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'COTTON', 'MALE', 'L', 'ADULT', 'Formal white cotton shirt', '{"image21.jpg"}', 13);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'JEANS', 'FEMALE', 'M', 'ADULT', 'Skinny jeans', '{"image22.jpg"}', 19);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'SILK', 'FEMALE', 'S', 'ADULT', 'Cocktail silk dress', '{"image23.jpg"}', 8);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'COTTON', 'MALE', 'S', 'CHILD', 'Plain cotton kids t-shirt', '{"image24.jpg"}', 10);

-- Customers --
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Acme', 'Wylie Coyote', 'wcoyote@acme.com', '1-515-555-2348', 'Customer Full Address',1);
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Spacely Space Sprockets', 'George Jettson', 'gjettson@spacely.com', '1-515-555-2350', 'Customer Full Address',2);
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Callahan Auto', 'Thomas Callhan', 'tcallahan@callhhanauto.com', '1-515-555-2333', 'Customer Full Address',3);
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Dundler Mifflin Inc', 'Michael Scott', 'mscott@dundlermifflin.com', '1-515-555-2320', 'Customer Full Address',4);
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Stark Industries', 'Tony Stark', 'tstark@stark.com', '1-515-555-7777', 'Customer Full Address',5);
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Initech', 'Peter Gibbons', 'pgibbons@initec.com', '1-515-555-0666', 'Customer Full Address',6);
INSERT INTO customers (name, contact_name, email, phone, address, user_security_id)
values ('Wayne Enterprises', 'Bruce Wayne', 'bwayne@wayne.com', '1-515-555-1111', 'Customer Full Address',7);

-- User Security --
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('Acme', 'wcoyote@acme.com', '{bcrypt}$2a$10$E9aahtfuQQnhCVRhwVCVqOPjxuRrhppsIHpaVmFiDmMwN31wUtjLm', true, 1);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('spacely', 'gjettson@spacely.com', '{bcrypt}$2a$10$dMHEjG7K7fhs9mQGCJrFseL2q7/CHRLi71hYt2wFkagLwfla2skSy', true, 2);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('tcal', 'tcallahan@callhhanauto.com', '{bcrypt}$2a$10$6XlFOIFLuYLFeDEBPfSGbuQ2gfd544zugin210uL7jmSEzDoe3x8S', true, 3);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('mscott', 'mscott@dundlermifflin.com', '{bcrypt}$2a$10$fL3vnmII70wYMDPVuWscxOiP4ex8arN.4bkiL60Neo9mwJ3P7ZqE.', true, 4);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('tstark', 'tstark@stark.com', '{bcrypt}$2a$10$FRcIGuqcVsBTcAJNHBdRWO70dUuz00og1Blf97t.xj.uDbPG7LbHy', true, 5);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('pgib', 'pgibbons@initec.com', '{bcrypt}$2a$10$td4QwRtTm7QIUHminn/X5eFSISn8LJ9J8n6JfUVtQCZZsKe8AcXb2', true, 6);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('bwayne', 'bwayne@wayne.com', '{bcrypt}2a$10$4Uhhaxz8nyliCGcBCzlhkOqukyZrx7mXz4y48xxEQ/bMCQXXV5ZJW', true, 7);

INSERT INTO roles(id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles(id, role)
VALUES (2, 'USER');
INSERT INTO roles(id, role)
VALUES (3, 'VIEW_INFO');
INSERT INTO roles(id, role)
VALUES (4, 'VIEW_ADMIN');

INSERT INTO user_security_roles( user_id, role_id)
VALUES (1, 1);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (1, 2);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (1, 3);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (1, 4);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (2, 2);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (2, 3);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (3, 2);
INSERT INTO user_security_roles( user_id, role_id)
VALUES (3, 3);
INSERT INTO user_security_roles( user_id, role_id)
VALUES ( 4, 2);
INSERT INTO user_security_roles( user_id, role_id)
VALUES ( 5, 3);
INSERT INTO user_security_roles( user_id, role_id)
VALUES ( 6, 3);
INSERT INTO user_security_roles( user_id, role_id)
VALUES ( 7, 3);

INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (1, 1);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (2, 2);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (3, 3);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (4, 4);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (5, 5);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (6, 6);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (7, 7);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (1, 8);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (2, 9);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (3, 10);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (4, 11);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (5, 12);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (6, 13);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (7, 14);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (1, 15);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (2, 16);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (3, 17);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (4, 18);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (5, 19);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (6, 20);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (7, 1);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (1, 2);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (2, 3);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (3, 4);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (4, 5);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (5, 6);
INSERT INTO customer_clothe (customer_id, clothe_id)
VALUES (1, 1);
