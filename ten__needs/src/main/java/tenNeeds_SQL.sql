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
    gTitle varchar(30),	-- 23.03.29 추가(게임방 생성에 사용ㅋㅋ)
    gDate datetime default now()
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
    gsResult boolean,
    mNo int,
    gNo int,
    rNo int,
    foreign key ( mNo ) references member ( mNo ) on delete set null,
    foreign key ( gNo ) references gameroom ( gNo ) on delete cascade,
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

# test sql
insert into member ( mId, mPw, mImg, mEmail, mPhone ) values( 'admin', 'admin', null, 'admin@naver.com', '01000000000');
insert into gameroom ( gTitle, gDate ) values ( "zz" , now() );
insert into racket ( rName, rImg, rLevle, rSize_x, rSize_y ) values( '파리채', null, 3, 5, 5 );
insert into racket ( rName, rImg, rLevle, rSize_x, rSize_y ) values( '모기채', null, 4, 6, 6 );
insert into racket ( rName, rImg, rLevle, rSize_x, rSize_y ) values( '잡채', null, 2, 2, 2 );
insert into racket ( rName, rImg, rLevle, rSize_x, rSize_y ) values( '화랑채', null, 5, 7, 7 );
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, 0, 1, 1, 1 );
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 2, 1 ); -- true 1, false 0
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 3, 1 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, false, 1, 4, 1 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 1 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 2 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 2 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 2 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 2 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 2 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, false, 1, 5, 3 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, false, 1, 5, 3 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, false, 1, 5, 3 ); 
insert into gamestatus (  gsAccute, gsResult, mNo, gNo, rNo ) values(  5, true, 1, 5, 3 ); 
select * from gameroom;
select * from gamestatus;




select g.*, r.rName, r.rImg from gamestatus g natural join racket r where mNo = 1;
select g.*, r.rName, r.rImg from gamestatus g natural join racket r where mNo = 1 group by rNo;
select g.*, r.rName, r.rImg, count(g.gsNo) from gamestatus g natural join racket r where mNo = 1 group by gNo;

select g.*, count(*) as gCount, r.rName, r.rImg from gamestatus g, racket r where mNo = 1 group by gNo, gsNo, rName, rImg;

# 게임 데이터 출력1 ( 경기수, 승수, 승률 확인 )
select count(*) as gCount, sum(gsResult) as gWin, (sum(gsResult)/count(*))*100 as gWinRate from gamestatus where mNo = 1;
SELECT rNo, SUM(gsResult) as rWin FROM gamestatus where mNo = 1 GROUP BY rNo;
# 게임 데이터 출력2 ( 라켓정보: 라켓별로 gsResult 통계 내기 )
select r.rNo, r.rName, r.rImg, sum(g.gsResult) as total
from gamestatus g
join racket r on g.rNo = r.rNo where mNo = 1
group by r.rNo order by total desc;