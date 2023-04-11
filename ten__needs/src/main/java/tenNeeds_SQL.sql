drop database if exists tenneeds;
create database tenneeds;

use tenneeds;
drop table if exists member;
create table member(
	mNo int auto_increment primary key,
    mId varchar(30),
    mPw varchar(30),
    mImg longtext,
    mEmail varchar(50),
    mPhone varchar(13)
);

drop table if exists gameroom;
create table gameroom(
	gNo int auto_increment primary key,
    gTitle varchar(30) not null,	-- 23.03.29 추가(게임방 생성에 사용)
    gDate datetime default now(),
    mno int, -- 23.03.29 추가(게임방 생성에 사용)
	foreign key ( mno ) references member ( mno ) on delete set null -- 23.03.29 추가(게임방 생성에 사용)
);

drop table if exists racket;
create table racket(
	rNo int auto_increment primary key,
    rName varchar(30),
    rImg longtext,
    rLevle int, 	-- type check
    rSize_x int, 	-- type check
    rSize_y int 	-- type check
);

drop table if exists gamestatus;
create table gamestatus(
	gsNo int auto_increment primary key,
    gsAccute varchar(10), -- type check
    gsResult boolean default false,
    mNo int,
    gNo int,
    rNo int,
    foreign key ( mNo ) references member ( mNo ) on delete set null,
    foreign key ( gNo ) references gameroom ( gNo ) on delete set null, -- 23.03.29, set null로 조건 변경(gNo 사라져도 데이터 유지)
    foreign key ( rNo ) references racket ( rNo ) on delete set null
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
    mNo int,
    foreign key ( bNo ) references board ( bNo ) on delete set null,
    foreign key ( mNo ) references member ( mNo ) on delete set null
);

# ------------------------------------------------------------------------------- test inserting data
select * from member;
select * from racket;

insert into member ( mId, mPw, mImg, mEmail, mPhone ) values( 'admin', 'admin', null, 'admin@naver.com', '01000000000');

insert racket(rName, rImg, rLevle, rSize_x, rSize_y) values ('테니스라켓[정석]', 'tennisstick.png', 4, 100, 100);
insert racket(rName, rImg, rLevle, rSize_x, rSize_y) values ('탁구채[딱딱의 근본]', 'pingpong.png', 3, 85, 85);
insert racket(rName, rImg, rLevle, rSize_x, rSize_y) values ('파리채[유니크]', 'flapper.png', 2, 70, 70);
