CREATE TABLE clothe
(
    id                bigint auto_increment primary key,
    clothe_type       varchar(64)  not null,
    fabric            varchar(128) not null,
    gender            varchar(128) not null,
    size              varchar(24)  not null,
    age_type          varchar(24)  not null,
    description       text         not null,
    images            text         not null,
    quantity_in_store int          not null
);

CREATE TABLE customers
(
    customer_id  bigint auto_increment primary key,
    name         varchar(64)  not null,
    contact_name varchar(128) not null,
    email        varchar(128) not null,
    phone        varchar(24)  not null
);

CREATE TABLE orders
(
    order_id    bigint auto_increment primary key,
    customer_id bigint        not null,
    order_info  varchar(2048) not null,
    foreign key (customer_id) references customers (customer_id)
);

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  boolean
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    constraint fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

-- CREATE UNIQUE INDEX ix_auth-username ON authorities (username, authority);