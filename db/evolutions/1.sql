# Play Jug Schema

# --- !Ups

    create table Event (
        id bigint not null auto_increment,
        capacity integer not null,
        date datetime,
        description longtext,
        location varchar(255),
        map longtext,
        open bit not null,
        registrationURL varchar(255),
        report longtext,
        title varchar(255),
        primary key (id)
    );

    create table Participation (
        id bigint not null auto_increment,
        code varchar(255),
        status integer,
        event_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table Speaker (
        id bigint not null auto_increment,
        activity varchar(255),
        company varchar(255),
        description longtext,
        fullName varchar(255),
        jugmember bit,
        memberFct varchar(255),
        photoUrl varchar(255),
        url varchar(255),
        primary key (id)
    );

    create table Talk (
        id bigint not null auto_increment,
        orderInEvent integer not null,
        tags varchar(255),
        teaser varchar(255),
        time varchar(255),
        title varchar(255),
        event_id bigint,
        speaker_id bigint,
        primary key (id)
    );

    create table User (
        id bigint not null auto_increment,
        email varchar(255),
        primary key (id)
    );

    alter table Participation
        add constraint FKE5A0BD21BAFEA2D6
        foreign key (event_id)
        references Event (id);

    alter table Participation
        add constraint FKE5A0BD2147140EFE
        foreign key (user_id)
        references User (id);

    alter table Talk
        add constraint FK27A8CCBAFEA2D6
        foreign key (event_id)
        references Event (id);

    alter table Talk
        add constraint FK27A8CCF3EB05B6
        foreign key (speaker_id)
        references Speaker (id);


# --- !Downs

    alter table Participation
        drop
        foreign key FKE5A0BD21BAFEA2D6;

    alter table Participation
        drop
        foreign key FKE5A0BD2147140EFE;

    alter table Talk
        drop
        foreign key FK27A8CCBAFEA2D6;

    alter table Talk
        drop
        foreign key FK27A8CCF3EB05B6;

    drop table if exists Event;

    drop table if exists Participation;

    drop table if exists Speaker;

    drop table if exists Talk;

    drop table if exists User;

