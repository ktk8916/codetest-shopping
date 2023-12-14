-- table history

create table member(
    id int auto_increment primary key,
    username varchar(255),
    password varchar(255),
    nickname varchar(255),
    unique uk_username(username)
);
