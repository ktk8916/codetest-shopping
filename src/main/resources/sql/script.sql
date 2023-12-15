-- table history

create table member(
    id int auto_increment primary key,
    username varchar(255),
    password varchar(255),
    nickname varchar(255),
    unique uk_username(username)
);

create table product(
    id int auto_increment primary key,
    name varchar(255),
    price int,
    manager_id int
);