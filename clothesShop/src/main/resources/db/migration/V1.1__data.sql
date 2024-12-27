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
INSERT INTO customers (name, contact_name, email, phone)
values ('Acme', 'Wylie Coyote', 'wcoyote@acme.com', '1-515-555-2348');
INSERT INTO customers (name, contact_name, email, phone)
values ('Spacely Space Sprockets', 'George Jettson', 'gjettson@spacely.com', '1-515-555-2350');
INSERT INTO customers (name, contact_name, email, phone)
values ('Callahan Auto', 'Thomas Callhan', 'tcallahan@callhhanauto.com', '1-515-555-2333');
INSERT INTO customers (name, contact_name, email, phone)
values ('Dundler Mifflin Inc', 'Michael Scott', 'mscott@dundlermifflin.com', '1-515-555-2320');
INSERT INTO customers (name, contact_name, email, phone)
values ('Stark Industries', 'Tony Stark', 'tstark@stark.com', '1-515-555-7777');
INSERT INTO customers (name, contact_name, email, phone)
values ('Initech', 'Peter Gibbons', 'pgibbons@initec.com', '1-515-555-0666');
INSERT INTO customers (name, contact_name, email, phone)
values ('Wayne Enterprises', 'Bruce Wayne', 'bwayne@wayne.com', '1-515-555-1111');

INSERT INTO orders (customer_id, order_info)
values ((SELECT customer_id FROM customers where name = 'Acme'), '1500 Widgets');
INSERT INTO orders (customer_id, order_info)
values ((SELECT customer_id FROM customers where name = 'Acme'), '3000 Widgets');
INSERT INTO orders (customer_id, order_info)
values ((SELECT customer_id FROM customers where name = 'Callahan Auto'), '200 Widgets');


INSERT INTO users (username, password, enabled)
values ('user', '{bcrypt}$2a$10$XlkdPQQhYcolx8bgp6nL3uNvDs8ZwDXA4KFaDencZsIhjMQO3j5lq', true);
INSERT INTO users (username, password, enabled)
values ('admin', '{bcrypt}$2a$10$XlkdPQQhYcolx8bgp6nL3uNvDs8ZwDXA4KFaDencZsIhjMQO3j5lq', true);

INSERT INTO authorities (username, authority)
values ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');

