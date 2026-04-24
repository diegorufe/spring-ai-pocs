create table if not exists products(
    code varchar(3) primary key,
    name varchar(50) not null
);

create table if not exists stores(
    code varchar(3) primary key,
    name varchar(50) not null
);

create table if not exists  stocks (
    store_code      varchar(3) NOT NULL,
    product_code   varchar(3) NOT NULL,
    quantity    NUMERIC(10, 2) NOT NULL DEFAULT 0,
    PRIMARY KEY (store_code, product_code)
);