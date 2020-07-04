DROP IF EXISTS TABLE client_deal_table;

CREATE TABLE client_deal_table 
 (
   client_deal_id BIGSERIAL PRIMARY KEY,
   client_id BIGINT NOT NULL,
   client_name VARCHAR(255) NOT NULL,
   deal_id BIGINT NOT NULL,
   deal_name VARCHAR(255) NOT NULL,
   date_time DATE,
   accepted INT NOT NULL,
   refused INT NOT NULL
 );