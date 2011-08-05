# Play Jug 0.0.2 Schema

# --- !Ups

    alter table Event add partner_id bigint;

    create table EventPartner (
        id bigint not null auto_increment,
        description longtext,
        logoURL varchar(255),
        name varchar(255),
        url varchar(255),
        primary key (id)
    );

    create table News (
        id bigint not null auto_increment,
        comments bit not null,
        content longtext,
        date datetime,
        title varchar(255),
        primary key (id)
    );

    alter table Talk drop column tags;

    alter table Speaker add email varchar(255);

    alter table Speaker add personalUrl varchar(255);

    create table Tag (
        id bigint not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table Talk_Tag (
        Talk_id bigint not null,
        tags_id bigint not null,
        primary key (Talk_id, tags_id)
    );

    create table YearPartner (
        id bigint not null auto_increment,
        description longtext,
        logoURL varchar(255),
        name varchar(255),
        startDate datetime,
        stopDate datetime,
        url varchar(255),
        primary key (id)
    );

    alter table Event
        add constraint FK403827AA33E91E4
        foreign key (partner_id)
        references EventPartner (id);

    alter table Talk_Tag
        add constraint FKDF7AD987222C70F7
        foreign key (tags_id)
        references Tag (id);

    alter table Talk_Tag
        add constraint FKDF7AD987F3D5575E
        foreign key (Talk_id)
        references Talk (id);

# --- !Downs

    alter table Event
        drop
        foreign key FK403827AA33E91E4;

    alter table Talk_Tag
        drop
        foreign key FKDF7AD987222C70F7;

    alter table Talk_Tag
        drop
        foreign key FKDF7AD987F3D5575E;

    alter table Event drop column partner_id;

    alter table Speaker drop email;

    alter table Speaker drop personalUrl;

    alter table Talk add tags varchar(255);

    drop table if exists EventPartner;

    drop table if exists News;

    drop table if exists Tag;

    drop table if exists Talk_Tag;

    drop table if exists YearPartner;

