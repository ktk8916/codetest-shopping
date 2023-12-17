create table member (
    id int auto_increment primary key,
    username varchar(255),
    password varchar(255),
    nickname varchar(255),
    unique uk_username(username)
);

create table product (
    id int auto_increment primary key,
    name varchar(255),
    price int,
    manager_id int,
    foreign key (manager_id) references member(id)
);

create table cart_item (
    id int auto_increment primary key,
    member_id int,
    product_id int,
    quantity int,
    foreign key (member_id) references member(id),
    foreign key (product_id) references product(id)
);

create table orders (
    id int auto_increment primary key,
    member_id int,
    total_price int,
    created_at datetime default now(),
    foreign key (member_id) references member(id)
);

create table order_item (
    id int auto_increment primary key,
    order_id int,
    product_id int,
    order_price int,
    quantity int,
    foreign key (order_id) references orders(id),
    foreign key (product_id) references product(id)
);