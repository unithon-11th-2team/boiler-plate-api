CREATE DATABASE core CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    message VARCHAR(255) NOT NULL,
    uid VARCHAR(255) NOT NULL,
    type INT NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL
);
create table user
(
    id          bigint auto_increment primary key,
    created_at  datetime(6) null,
    modified_at datetime(6) null,
    device_id   varchar(512) null,
    nickname    varchar(512) null,
    token       varchar(512) null
);
CREATE TABLE item_count (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    INDEX (item_id),
    INDEX (user_id)
);
