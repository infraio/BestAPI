drop database if exists bestapi;
create database bestapi;

use bestapi;

drop table if exists user;
create table user(
	email varchar(255) primary key,
	username varchar(20) not null,
	password varchar(20) not null
);

drop table if exists api;
create table api(
	name varchar(255) primary key,
	owner varchar(255),
	endpoint varchar(255),
	homepage varchar(255),
	contact_email varchar(255),
	category varchar(20),
	protocol_formats varchar(20),
	hub_url varchar(255),
	authentication_mode varchar(20)
);

drop table if exists domain;
create table domain (
	name varchar(20) primary key
);

drop table if exists user_api;
create table user_api (
	username varchar(20) not null,
	apiname varchar(255) not null,
	relation int not null,
	CONSTRAINT combination_key PRIMARY KEY (username, apiname, relation)
);