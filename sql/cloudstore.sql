drop database if exists cloudstore;

create database cloudstore character set utf8;


use cloudstore;

create table member(
    id bigint,
    username    varchar(32),
    nickname    varchar(32),
    password    varchar(100),
    email       varchar(100),
    mobile      varchar(100),
    avatar      varchar(100),
    salt        varchar(100),
    create_time datetime,
    update_time datetime,
    login_time  datetime,
    deleted     tinyint(1),
    primary key(id)
);

create table oauth2_qq_info(
    id bigint,
    openid      varchar(100),
    member_id   bigint,
    primary key(id)
);
