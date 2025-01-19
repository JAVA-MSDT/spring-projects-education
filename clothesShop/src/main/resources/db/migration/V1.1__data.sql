-- products --
INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'COTTON', 'MALE', 'M', 'ADULT', 'Casual cotton shirt', '/images/clothes/ShirtCottonMaleMAdult.png',
        10);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'JEANS', 'MALE', 'L', 'ADULT', 'Blue denim jeans', '/images/clothes/TROUSER JEANS MALE.png', 15);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'SILK', 'FEMALE', 'S', 'ADULT', 'Elegant silk evening dress', '/images/clothes/DRESS SILK FEMALE.png',
        5);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'COTTON', 'MALE', 'XL', 'ADULT', 'Comfortable cotton t-shirt',
        '/images/clothes/T_SHIRT COTTON MALE.png', 20);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'OTHER', 'FEMALE', 'M', 'CHILD', 'Colorful childrens shirt',
        '/images/clothes/SHIRT other female M Child.png', 12);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'COTTON', 'FEMALE', 'XS', 'ADULT', 'Light cotton trousers',
        '/images/clothes/TROUSER COTTON FEMALE ADULT XS.png', 18);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'OTHER', 'FEMALE', 'M', 'ADULT', 'Floral printed dress',
        '/images/clothes/red low collar professional women suit.png', 7);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'COTTON', 'FEMALE', 'XXS', 'CHILD', 'Graphic t-shirt for kids',
        '/images/clothes/T-shirt Clothing Outerwear.png',
        25);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'JEANS', 'MALE', 'XXL', 'ADULT', 'Denim shirt', '/images/clothes/blue button-up denim jacket.png', 8);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'SILK', 'FEMALE', 'S', 'ADULT', 'Formal silk trousers',
        '/images/clothes/Waist Pants Khaki Skirt Shorts.png', 4);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'COTTON', 'FEMALE', 'L', 'ADULT', 'Summer cotton dress', '/images/clothes/mint green dress.png', 30);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'JEANS', 'MALE', 'M', 'CHILD', 'Trendy kids denim t-shirt',
        '/images/clothes/T-shirt Clothing Sleeve Hood.png', 9);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'SILK', 'FEMALE', 'XL', 'ADULT', 'Office silk shirt',
        '/images/clothes/T-shirt Collar Crew neck Sleeve.png', 6);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'OTHER', 'MALE', 'L', 'ADULT', 'Lightweight outdoor trousers',
        '/images/clothes/Pants Pfanner Schutzbekleidung Hiking apparel.png', 14);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'JEANS', 'FEMALE', 'XXS', 'CHILD', 'Denim jumper dress',
        '/images/clothes/Party dress Child Shrug Frock.png', 11);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'OTHER', 'FEMALE', 'M', 'ADULT', 'Stylish designer t-shirt', '/images/clothes/T-shirt Hoodie .png',
        22);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('SHIRT', 'COTTON', 'MALE', 'L', 'ADULT', 'Formal white cotton shirt',
        '/images/clothes/T-shirt Dress shirt Polo shirt Clothing.png', 13);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('TROUSER', 'JEANS', 'FEMALE', 'M', 'ADULT', 'Skinny jeans', '/images/clothes/T-shirt Jeans Slim-fit pants.png',
        19);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('DRESS', 'SILK', 'FEMALE', 'S', 'ADULT', 'Cocktail silk dress',
        '/images/clothes/Long-sleeved T-shirt Dress.png', 8);

INSERT INTO clothe (clothe_type, fabric, gender, size, age_type, description, images, quantity_in_store)
VALUES ('T_SHIRT', 'COTTON', 'MALE', 'S', 'CHILD', 'Plain cotton kids t-shirt',
        '/images/clothes/Plain cotton kids t-shirt.png', 10);

-- Customers --
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Ahmed', 'Samy', 'serenitydiver@hotmail.com', '+48-000-000000', 'Warsaw, Poland',
        '/images/users/ahmed-samy-profile.jpg',
        '/images/users/banner', 1);
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Spacely Space Sprockets', 'George Jettson', 'gjettson@spacely.com', '1-515-555-2350', 'Customer Full Address',
        '/images/users/Spacely-profile.png', '/images/users/banner',
        2);
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Callahan Auto', 'Thomas Callhan', 'tcallahan@callhhanauto.com', '1-515-555-2333', 'Customer Full Address',
        '/images/users/Callahan Auto-profile.png', '/images/users/banner', 3);
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Dundler Mifflin Inc', 'Michael Scott', 'mscott@dundlermifflin.com', '1-515-555-2320', 'Customer Full Address',
        '/images/users/Michael Scott-profile.png', '/images/users/banner',
        4);
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Stark Industries', 'Tony Stark', 'tstark@stark.com', '1-515-555-7777', 'Customer Full Address',
        '/images/users/Tony Stark-profile.png', '/images/users/banner', 5);
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Marya', 'Samy', 'marya.samy@mail.com', '1-515-555-0666', 'Customer Full Address',
        '/images/users/Matya-profile.png', '/images/users/banner', 6);
INSERT INTO customers (name, contact_name, email, phone, address, profile_picture_url, banner_picture_url,
                       user_security_id)
values ('Wayne Enterprises', 'Bruce Wayne', 'bwayne@wayne.com', '1-515-555-1111', 'Customer Full Address',
        '/images/users/Bruce Wayne-profile.png', '/images/users/banner', 7);

-- User Security --
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('serenity', 'serenitydiver@hotmail.com', '{bcrypt}$2a$10$E9aahtfuQQnhCVRhwVCVqOPjxuRrhppsIHpaVmFiDmMwN31wUtjLm',
        true, 1);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('spacely', 'gjettson@spacely.com', '{bcrypt}$2a$10$dMHEjG7K7fhs9mQGCJrFseL2q7/CHRLi71hYt2wFkagLwfla2skSy', true,
        2);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('tcal', 'tcallahan@callhhanauto.com', '{bcrypt}$2a$10$6XlFOIFLuYLFeDEBPfSGbuQ2gfd544zugin210uL7jmSEzDoe3x8S',
        true, 3);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('mscott', 'mscott@dundlermifflin.com', '{bcrypt}$2a$10$fL3vnmII70wYMDPVuWscxOiP4ex8arN.4bkiL60Neo9mwJ3P7ZqE.',
        true, 4);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('tstark', 'tstark@stark.com', '{bcrypt}$2a$10$FRcIGuqcVsBTcAJNHBdRWO70dUuz00og1Blf97t.xj.uDbPG7LbHy', true, 5);
INSERT INTO user_security (username, email, password, enabled, customer_id)
values ('marya', 'marya.samy@mail.com', '{bcrypt}$2a$10$td4QwRtTm7QIUHminn/X5eFSISn8LJ9J8n6JfUVtQCZZsKe8AcXb2', true,
        6);
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

INSERT INTO user_security_roles(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (1, 2);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (1, 3);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (1, 4);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (2, 2);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (2, 3);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (3, 2);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (3, 3);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (4, 2);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (5, 3);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (6, 3);
INSERT INTO user_security_roles(user_id, role_id)
VALUES (7, 3);

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
