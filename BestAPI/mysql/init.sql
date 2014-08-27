
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
	API_NAME varchar(255) primary key,
	API_OWNER varchar(255),
	API_PROVIDER varchar(255),
	API_ENDPOINT varchar(255),
	API_HOMEPAGE varchar(255),
	CONTACT_EMAIL varchar(255),
	PRIMARY_CATEGORY varchar(255),
	SECONDARY_CATEGORIES varchar(255),
	PROTOCOL_FORMATS varchar(255),
	APIHUB_URL varchar(255),
	SSL_SUPPORT varchar(255),
	TWITER_URL varchar(255),
	AUTHENTICATION_MODE varchar(255)
);
