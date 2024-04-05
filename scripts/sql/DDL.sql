CREATE
DATABASE core CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

create table user
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6) null,
    modified_at datetime(6) null,
    device_id   varchar(512) null,
    nickname    varchar(512) null,
    token       varchar(512) null
);
