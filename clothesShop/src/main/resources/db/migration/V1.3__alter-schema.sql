-- Constraints for Foreign keys of OneToOne relationship

ALTER TABLE customers
    ADD CONSTRAINT fk_customer_user_security
        FOREIGN KEY (user_security_id)
            REFERENCES user_security (id);

ALTER TABLE user_security
    ADD CONSTRAINT fk_user_security_customer
        FOREIGN KEY (customer_id)
            REFERENCES customers (customer_id);