
CREATE DATABASE pharmacyLayered;

CREATE TABLE customer(
    customer_id VARCHAR(10) PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100),
    customer_address VARCHAR(50),
    customer_contact VARCHAR(20)

);

CREATE TABLE user(
    user_id VARCHAR(10) PRIMARY KEY,
    user_name VARCHAR(50),
    user_pw VARCHAR(25),
);

CREATE TABLE medicine(
    medicine_id VARCHAR(10) PRIMARY KEY,
    medicine_name VARCHAR(50),
    medicine__unit_price  DECIMAL(10,2),
    medicine_pack_price DECIMAL(10,2),
    medicine_unt_qty INT,
    medicine_pack_qty INT
);

CREATE TABLE orders(
    order_id VARCHAR(10) PRIMARY KEY,
    customer_id VARCHAR(10),
    order_date DATE,
    total_price DECIMAL(10,2)
);


create table orders (
    order_id varchar(10) not null primary key,
    customer_id varchar(10) null,
    order_date date not null,
    total_price decimal(10,2) not null,
    employee_id varchar(10) null,

    foreign key (employee_id) references employee(employee_id)
        on update cascade
        on delete cascade,
    foreign key (customer_id) references customer(customer_id)
        on update cascade
        on delete cascade
);

create table order_details (
    order_id varchar(10) not null,
    medicine_id varchar(10) not null,
    quantity_single int default 0,
    quantity_packs int default 0,
    price_single decimal(10,2) not null,
    price_packs decimal(10,2) not null,

    foreign key (order_id) references orders(order_id)
        on update cascade
        on delete cascade,
    foreign key (medicine_id) references medicines(medicine_id)
        on update cascade
        on delete cascade
);

CREATE TABLE employee (
    employee_id VARCHAR(10) NOT NULL PRIMARY KEY,
    employee_last_name VARCHAR(50),
    employee_email VARCHAR(50),
    employee_contact_number VARCHAR(20),
    employee_position VARCHAR(40),
    employee_first_name VARCHAR(20),
    employee_nic VARCHAR(15),
    employee_address VARCHAR(255)
);

CREATE TABLE employee_salary_details (
    employee_id VARCHAR(10),
    salary DECIMAL(10,4),
    salary_payed_months INT,
    employee_total_salary DECIMAL(12,4),
    task_id VARCHAR(20),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (task_id) REFERENCES task(task_id)
);

CREATE TABLE task (
    task_id VARCHAR(10) NOT NULL PRIMARY KEY,
    task_desc VARCHAR(100),
    task_time INT,
    task_unit VARCHAR(50)
);

CREATE TABLE supplier (
    supplier_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(15),
    email VARCHAR(100),
    address VARCHAR(255)
);