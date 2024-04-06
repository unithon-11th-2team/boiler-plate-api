CREATE
DATABASE core CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- auto-generated definition
create table item
(
    latitude    decimal(38, 2) null,
    longitude   decimal(38, 2) null,
    type        int null,
    created_at  datetime(6) null,
    id          bigint auto_increment
        primary key,
    modified_at datetime(6) null,
    uid         bigint null,
    message     varchar(255) null
);

-- auto-generated definition
create table item_comment
(
    id      bigint auto_increment
        primary key,
    item_id bigint null,
    uid     bigint null,
    message varchar(255) null
);
CREATE UNIQUE INDEX uidx__item_id_uid ON item_comment (item_id, uid);

-- auto-generated definition
create table item_like
(
    id      bigint auto_increment
        primary key,
    item_id bigint null,
    uid     bigint null
);
CREATE UNIQUE INDEX uidx__item_id_uid ON item_like (item_id, uid);

-- auto-generated definition
create table user
(
    created_at  datetime(6) null,
    id          bigint auto_increment
        primary key,
    modified_at datetime(6) null,
    device_id   varchar(255) null,
    nickname    varchar(255) null,
    token       varchar(255) null
);
CREATE UNIQUE INDEX uidx__nickname ON user (nickname);
CREATE UNIQUE INDEX uidx__token ON user (token);
CREATE UNIQUE INDEX uidx__device_id ON user (device_id);

create table item_comment_like
(
    id           bigint auto_increment
        primary key,
    item_comment_id bigint null,
    uid          bigint null
);
CREATE UNIQUE INDEX uidx__item_id_uid ON item_comment_like (item_comment_id, uid);

CREATE TABLE rank_address (
                              id BIGINT(20) NOT NULL AUTO_INCREMENT,
                              type int,
                              address VARCHAR(255),
                              latitude decimal(38, 10),
                              longitude decimal(38, 10),
                              score DOUBLE,
                              PRIMARY KEY (id)
);