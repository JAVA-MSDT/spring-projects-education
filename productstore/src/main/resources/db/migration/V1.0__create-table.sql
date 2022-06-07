CREATE TABLE image (
    image_id BIGSERIAL PRIMARY KEY,
    image varbinary(255),
    image_alt varchar(255),
    image_title varchar(255)
);

CREATE TABLE 'user' (
    user_id BIGSERIAL PRIMARY KEY,
    address varchar(255),
    email varchar(255),
    full_name varchar(255),
    password varchar(255),
    phone_number varchar(255),
    username varchar(255)
);

CREATE TABLE product (
    product_id BIGSERIAL PRIMARY KEY,
    product_description varchar(255),
    product_price numeric(19,2),
    product_quantity integer,
    product_ varchar(255),
    product_type varchar(255)
);

CREATE TABLE order (
    order_id BIGSERIAL PRIMARY KEY,
    product_id bigint,
    product_quantity INT,
    purchase_date date,
    total_price numeric(19,2),
    user_id bigint
);

CREATE TABLE product_images (
    id PRIMARY KEY,
    product_id bigint not null,
    image_id bigint not null
);

CREATE TABLE user_images (
    id PRIMARY KEY,
    user_id bigint not null,
    image_id bigint not null
);
