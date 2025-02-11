CREATE DATABASE pharmacyLayered;

CREATE TABLE customer(
    customer_id VARCHAR(10) PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100),
    customer_address VARCHAR(50),
    customer_contact VARCHAR(20)
);

CREATE TABLE users(
    user_id VARCHAR(10) PRIMARY KEY,
    user_name VARCHAR(50),
    user_pw VARCHAR(25),
    user_mail VARCHAR(15)
);

CREATE TABLE medicine(
    medicine_id VARCHAR(10) PRIMARY KEY,
    medicine_name VARCHAR(50),
    medicine_unit_price  DECIMAL(10,2),
    medicine_pack_price DECIMAL(10,2),
    medicine_unt_qty INT,
    medicine_pack_qty INT
);

CREATE TABLE orders(
    order_id VARCHAR(10) PRIMARY KEY,
    customer_id VARCHAR(10),
    order_date DATE,
    total_price DECIMAL(10,2),

    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE order_detail(
    order_id VARCHAR(10),
    medicine_id VARCHAR(10),
    qty_unit INT,
    qty_packs INT,
    qty_unit_price DECIMAL(10,2),
    qty_packs_price DECIMAL(10,2),

    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO users (user_id,user_name,user_pw, user_mail) VALUES ('U001','sasindu','sasindu1234','sasindudenuwan2006wpsk@gmail.com'), ('U002','kavindu','kavindu1234','sasindudenuwan2006wpsk@gmail.com');