#DB생성 테스트
drop database if exists testbase;
create database testbase;

use testbase;
#테이블 테스트

drop table if exists myaccount;
drop table if exists mycharacter;
drop table if exists monster;
drop table if exists dungeonDetail;
drop table if exists skill;
drop table if exists Myskill;
drop table if exists dungeon;

create table myaccount(
akey int auto_increment,
aid varchar(20) not null unique,
apwd varchar(40) not null,
aname varchar(40) not null,
anum varchar(20) not null,
abirth varchar(6) not null,
adate datetime default now(),
primary key(akey)
);
create table mycharacter(
ckey int auto_increment,
cnickname varchar(20) not null unique,
chp int default 100,
primary key(ckey),
akey int,
foreign key(akey) references myaccount(akey)
);
create table monster(
mkey int auto_increment,
mname varchar(20),
primary key(mkey)
);
create table dungeonDetail(
dtkey int auto_increment,
dname varchar(20),
dlevel tinyint default 1,
primary key(dtkey)
);
create table skill(
skkey int auto_increment,
skname varchar(10),
skinfo varchar(40),
skdamage int default 0,
primary key (skkey)
);
create table Myskill(
mskey int auto_increment,
ckey int,
skkey int,
primary key(mskey),
foreign key(ckey) references mycharacter(ckey),
foreign key(skkey) references skill(skkey)
);
create table dungeon(
dgkey int auto_increment,
mkey int,
dtkey int,
primary key(dgkey),
foreign key(mkey) references monster(mkey),
foreign key(dtkey) references dungeonDetail(dtkey)
);

select * from myaccount;
select * from mycharacter;
select * from monster;
select * from dungeonDetail;
select * from skill;
select * from Myskill;
select * from dungeon;

#테스트용
insert into myaccount(aid,apwd,aname,anum,abirth) values ('bc','1234','김병찬','010-9999-8888','010101');
insert into myaccount(aid,apwd,aname,anum,abirth) values ('bk','1234','유재석','010-7777-8888','990808');
insert into myaccount(aid,apwd,aname,anum,abirth) values ('bq','1234','강호동','010-6666-8888','891111');

insert into mycharacter(cnickname) values ('카카오');
insert into mycharacter(cnickname) values ('네이버');
insert into mycharacter(cnickname) values ('페이커');

insert into monster(mname,mhp,mdamage) values ('스켈레톤',100,5);
insert into monster(mname,mhp,mdamage) values ('스켈레톤1',100,5);
insert into monster(mname,mhp,mdamage) values ('스켈레톤2',100,5);

insert into dungeonDetail(dname) values ('초원');
insert into dungeonDetail(dname) values ('동굴');
insert into dungeonDetail(dname) values ('용암');

insert into skill(skname,skinfo,skdamage) values ('참격','참격을날립니다.',10);
insert into skill(skname,skinfo,skdamage) values ('파이어볼','파이어볼 날립니다.',20);
insert into skill(skname,skinfo,skdamage) values ('활쏘기','화살을 쏩니다.',30);

insert into Myskill(ckey, skkey) values (1,2);

insert into dungeonDetail(ckey, mkey) values (1,1);
