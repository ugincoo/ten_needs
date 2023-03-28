drop database if exists tenneeds;
create database tenneeds;

use tenneeds;
drop table if exists member;
create table member(
	mNo int auto_increment primary key,
    mId varchar(30),
    mPw varchar(30),
    mEmail varchar(50),
    mPhone varchar(12)
);

drop table if exists game;
create table game(
	gNo int auto_increment primary key,
    gDate datetime default now()
);

drop table if exists gamestatus;
create table gamestatus(
	gsNo int auto_increment primary key,
    gsAccute varchar(10), -- type check
    gsResult boolean,
    mNo int,
    gNo int,
    foreign key ( mNo ) references member ( mNo ) on delete set null,
    foreign key ( mNo ) references game ( gNo ) on delete cascade
);

drop table if exists racket;
create table racket(
	rNo int auto_increment primary key,
    rName varchar(30),
    rLevle int, 	-- type check
    rSize_x int, 	-- type check
    rSize_y int 	-- type check
);

drop table if exists board;
create table board(
	bNo int auto_increment primary key,
    bTitle varchar(100),
    bContent longtext,
    bDate datetime default now()	-- adding mNo check
);

drop table if exists reply;
create table reply(
	reNo int auto_increment primary key,
    reContent text,	-- type check
    reDate datetime default now(),
    bNo int,
    foreign key ( bNo ) references board ( bNo ) on delete set null
);