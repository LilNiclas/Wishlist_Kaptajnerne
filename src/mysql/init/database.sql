DROP DATABASE IF EXISTS wishList2;
CREATE DATABASE wishList2;
USE wishList2;

CREATE TABLE users
(
    users_id int auto_increment,
    email    varchar(75) not null,
    username varchar(50),
    primary key (users_id),
    index(username)
);

CREATE TABLE wishlist
(
    wishlist_id  int          not null auto_increment,
    wishlistName varchar(100) not null,
    users_id     int          not null,
    primary key (wishlist_id),
    foreign key (users_id) references users (users_id)
);

CREATE TABLE wishes
(
    wish_id     int          not null auto_increment,
    wishName    varchar(100) not null,
    price       double       not null,
    description varchar(300),
    link        varchar(300),
    wishlist_id int          not null,
    primary key (wish_id),
    foreign key (wishlist_id) references wishlist (wishlist_id)
);
