create table client
(
    id   bigserial not null primary key,
    name varchar(200),
    age  integer
);

create table addressData
(
    id       bigserial not null primary key,
    addr     varchar(255),
    clientid bigint    not null,
    constraint client_fk foreign key (clientid) references client (id)
);

create table phonesData
(
    id       bigserial not null primary key,
    number   varchar(255),
    clientid bigint    not null,
    constraint client_fk foreign key (clientid) references client (id)
);
