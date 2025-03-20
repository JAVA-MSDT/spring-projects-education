----------------------------------------------------
------------------- MAIN TABLES --------------------
----------------------------------------------------

CREATE TABLE image (
    image_id BIGSERIAL PRIMARY KEY,
    image varbinary(255),
    image_alt varchar(255),
    image_title varchar(255)
);

CREATE TABLE user_table (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    full_name VARCHAR,
    email VARCHAR,
    phone_number VARCHAR,
    address VARCHAR
);

CREATE TABLE order_table (
    order_id BIGSERIAL PRIMARY KEY,
    product_id bigint,
    product_quantity INT,
    purchase_date date,
    total_price numeric(19,2),
    user_id bigint
);

CREATE TABLE product (
    product_id BIGSERIAL PRIMARY KEY,
    product_description varchar(255),
    product_price numeric(19,2),
    product_quantity integer,
    product_title varchar(255),
    product_type varchar(255)
);

----------------------------------------------------
--------------- ASSOCIATION TABLES -----------------
----------------------------------------------------

CREATE TABLE product_images (
    id BIGSERIAL PRIMARY KEY,
    product_id bigint not null,
    image_id bigint not null,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (image_id) REFERENCES image(image_id)
);

CREATE TABLE user_images (
    id BIGSERIAL PRIMARY KEY,
    user_id bigint not null,
    image_id bigint not null,
    FOREIGN KEY (user_id) REFERENCES user_table(user_id),
    FOREIGN KEY (image_id) REFERENCES image(image_id)
);

CREATE TABLE user_products (
    id BIGSERIAL PRIMARY KEY,
    user_id bigint not null,
    product_id bigint not null,
    FOREIGN KEY (user_id) REFERENCES user_table(user_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);
