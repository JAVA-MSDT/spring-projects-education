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
    customer_id      bigint auto_increment primary key,
    name             varchar(64),
    contact_name     varchar(128),
    email            varchar(128),
    phone            varchar(24),
    address          varchar(500),
    user_security_id bigint
);

CREATE TABLE user_security
(
    id                      bigint auto_increment primary key,
    username                VARCHAR(50)  NOT NULL unique,
    email                   varchar(128) not null unique,
    password                VARCHAR(500) NOT NULL,
    account_non_expired     boolean default true,
    account_non_locked      boolean default true,
    credentials_non_expired boolean default true,
    enabled                 boolean default true,
    customer_id             bigint
);

create table roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(255)
);

---
create table user_security_roles
(
    user_id BIGINT  NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user_security (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE customer_clothe
(
    customer_clothe_id bigint auto_increment primary key,
    customer_id        BIGINT NOT NULL,
    clothe_id          BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    FOREIGN KEY (clothe_id) REFERENCES clothe (id)
);