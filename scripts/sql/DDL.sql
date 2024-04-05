CREATE DATABASE core CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- auto-generated definition
create table item
(
    latitude    decimal(38, 2) null,
    longitude   decimal(38, 2) null,
    type        int            null,
    created_at  datetime(6)    null,
    id          bigint auto_increment
        primary key,
    modified_at datetime(6)    null,
    uid         bigint         null,
    message     varchar(255)   null
);

-- auto-generated definition
create table item_comment
(
    id      bigint auto_increment
        primary key,
    item_id bigint       null,
    uid     bigint       null,
    message varchar(255) null
);

-- auto-generated definition
create table item_like
(
    id      bigint auto_increment
        primary key,
    item_id bigint null,
    uid     bigint null
);

-- auto-generated definition
create table user
(
    created_at  datetime(6)  null,
    id          bigint auto_increment
        primary key,
    modified_at datetime(6)  null,
    device_id   varchar(255) null,
    nickname    varchar(255) null,
    token       varchar(255) null
);

