CREATE DATABASE IF NOT EXISTS wishlist;
USE wishlist;

DROP TABLE IF EXISTS wishlist_wishes;
DROP TABLE IF EXISTS wishes;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS users;

CREATE TABLE wishes
(wish_id int not null auto_increment,
 wishName varchar(100) not null,
 price double not null,
 description varchar(300),
 link varchar(300),
 primary key (wish_id));

CREATE TABLE users
(email varchar(75) not null,
 username varchar(50) not null,
 primary key(email),
 index(username));

CREATE TABLE wishlist
(wishlist_id int not null auto_increment,
 wishlistName varchar(100) not null,
 username varchar(50) not null,
 primary key (wishlist_id),
 foreign key (username) references users (username));

CREATE TABLE wishlist_wishes
(wishlist_id int not null,
 wish_id int not null,
 primary key (wishlist_id, wish_id),
 foreign key (wishlist_id) references wishlist (wishlist_id),
 foreign key (wish_id) references wishes (wish_id));

