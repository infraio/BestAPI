
drop database if exists userbase;
create database userbase;

use userbase;

drop table if exists user;
create table user(
	email varchar(20) primary key,
	username varchar(20) not null,
	password varchar(20) not null
);