#DB생성 테스트
drop database if exists testbase;
create database testbase;

use testbase;
#테이블 테스트

drop table if exists myaccount;
drop table if exists mycharacter;
drop table if exists monster;
drop table if exists skill;
drop table if exists Myskill;
drop table if exists admin;
drop table if exists friends;

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
    cexp int default 1,
    cmoney int default 0,
	akey int,
	foreign key(akey) references myaccount(akey)
	on delete cascade  
	on update cascade
);
create table monster(
	mkey int auto_increment,
	mname varchar(20),
    mimage longtext,
	primary key(mkey)
);

create table skill(
	skkey int auto_increment,
	skname varchar(10),
	skinfo varchar(40),
	skdamage int default 0,
    skmoney int default 0,
	primary key (skkey)
);
create table Myskill(
	mskey int auto_increment,
	ckey int,
	skkey int,
	primary key(mskey),
	foreign key(ckey) references mycharacter(ckey)
    on delete cascade
    on update cascade , 
	foreign key(skkey) references skill(skkey)
	on delete cascade  
	on update cascade
);

create table admin(
	adkey int auto_increment,
	adid varchar(50) not null unique,
	adpwd varchar(50) not null,
	primary key(adkey)
);

create table friends(
fkey int auto_increment,
primary key(fkey),
state boolean default false, 
fromckey int,
tockey int not null,
foreign key(fromckey) references mycharacter(ckey), #내 캐릭터키랑
foreign key(tockey) references mycharacter(ckey) #상대방 캐릭터키
);

select * from myaccount;
select * from mycharacter;
select * from monster;
select * from skill;
select * from Myskill;
select * from admin;
select * from friends;

#테스트용
insert into admin(adid,adpwd) values ('admin','admin');

insert into myaccount(aid,apwd,aname,anum,abirth) values ('bc','1234','김병찬','01099998888','010101');
insert into myaccount(aid,apwd,aname,anum,abirth) values ('bk','1234','유재석','01077778888','990808');
insert into myaccount(aid,apwd,aname,anum,abirth) values ('bq','1234','강호동','01066668888','891111');

insert into mycharacter(cnickname , akey, cmoney) values ('카카오' , 1 , 1000000);
insert into mycharacter(cnickname , akey) values ('네이버' , 2);
insert into mycharacter(cnickname , akey) values ('페이커' , 3);

insert into monster(mname , mimage) values ('스켈레톤' , '
@@@@@@@@@;#@@=#@@@@@@@@@@@@@@@
@@@@@@@@*.!#@**@@@@@@@@@@@@@@@
@@@@@@@@-.;!:;$@@@@@@@@@@@@@@@
@@@@@@@#,.:$!=!!$@@@@@@@@@@@@@
@@@@@@,~,-!!:~;!!$#@@@@@@@@@@@
@@@@@ .,=~*:-~;:!==@@@@@@@@@@@
@@@@#,=.:::,,~:;!***@@@@@@@@@@
@@@@~$.;::;-,~;;!**=@@@@@@@@@@
@@@#:==!-:!-~;:;****$@@@@@@@@@
@@@;,!.=~;-:;:;;!***=@@@@@@@@@
@@==~;=$!*!**!!!*=**#@@@@@@@@@
@@@:!!;;!!##==!**==*#@@@@@@@@@
@@@@=#$,,#$$#$;!!#!$=@@@@@@@@@
@@@@$#=.~@###$:!=$;=$@@@@@@@@@
@@@:*#:,~@##$$!;=;~#;*@@@@@@@@
@@@,!@-!,;#$$$,:@@@@@-*@@@@@@@
@@@@!,,-,-~;;;:!$@=#!=:*@@@@@@
@@@@@=:,::=#=##;!#@$;:$~*@@@@@
@@@@@@*-!;#!=@@!;@;.-!$@,*@@@@
$=@@@@-,~==*@@@$;$@@#**#$-;#@@
*-*@@@*--~;=@@@*=#@$==*!=***@@
*,~**#@@@@@@@@@=;,@##!~!#;~#@@
!:.:!$@@@@@@@@@!=@**!:=@@:*@@@
#;-.~*@@@@@@@@$-*$;$@#=@@~;@@@
@$$;,:*@@@@@@@~*@@@!!:**@~;@@@
@@@$-,:*#@@@@*:@@@#=#*!~=!*@@@
@@@@@*,:#@@@*~=@@@$!!;*;@!$@@@
@@@@@@=-!$=@=;=@@@-!#~#;@!$@@@
@@@@@@@@#*:!;@@@;-*@@@:~@!$@@@
@@@@@@@$~#=$=@@@@~@@@@*!*:=@@@
@@@@@@@@;#*$=@@=!-@@@@=!=*@@@@
@@@@@@@@@#*$$@@@@;=@@@@@#*#@$#
@@@@@@@@@@@=@@@=$=*#@@@@*!;@==
@@@@@@@@@@@@@@@=*=#$@@@@==#**$
');
insert into monster(mname , mimage) values ('좀비' , '
@@@@@@@@@*;*#@=*=#$@@@@@@@@@@@
@@@@@@@@$!!!~  ..:=##@@@@@@@@@
@@@@@@==*:.    ..~;=##@@@@@@@@
@@@@@$!:.  .,.-!==$$#@@@@@@@@@
@@@@@=;,,,-:!$====$=$$=$@@@@@@
@@@@@=~;!=$$=$=**!;---;=@@@@@@
@$=;==;;!!$$=*!- ...,::=$#@@@@
@#;,,*****=*:-....-~;*=$$#@@@@
@#.;;*$$==!:...,:;*$$$=@$##@@@
@#~:#@#$=!~,,-;*=##$=!;==$#@@@
@$=-#*$$;~~;!*==!=$==;:;*$@@@@
@@$:!:=::;*$$;~~-!==*::;$@@@@@
@@=$!~!~*==*~,  .;**!;~!$@@@@@
@@@@=!;*$;,-..,~:!==**!!$@@@@@
@@@@$=*=;,   ,;;,;====$!!$#@@@
@@@@#==*,-:.,;!, .~!==*==$$@@@
@@@@##@$~;=~!!;~  ,!:*;**$#@@@
@@@@@@$:**=;=~-.  -:,:=**#@@@@
@@@@#$*.-~..,-*$$:..;;:;*#@@@@
@@##=$* .-;~;.-~-,.~$*!=#@@@@@
@@@@#@@#!.......-~:;$=***$#@@@
@ .: ~@$!,,-.!**!;!==*!;;!#@@@
@,*!#!@*!@@#@$!!$*==!;;;;!#@@@
=#==#$@=;*:-:!!!=!!!;!;;;!#@@@
*~!*-;$#*==;-;!=!;=*!*;;!##@@@
@#;$$;!=**,:;;:,~;==!!;;*#@@@@
@@#-;!*#**~~.  ,;!;==**!*#@@@@
@@@@@##$*=**!--~=#!==$=!!#@@@@
@@@#$$$*$$;!;;:;;***:!***$@@@@
@@@@#$=$##*;;!:;!!!;:$=*=$@@@@
@@@@@@@@@@$==*;:=*:;!$=*$#@@@@
@@@@@@@@@@@@$**!*=!!=$==##@@@@
@@@@@@@@@@@$**$*;!!=$=$#@@@@@@
@@@@@@@@@@#$====!;;;*=$$#@@@@@
@@@@@@@@@@#=*!!!==!:;!*=$#@@@@
@@@@@@@@@*!:::::;***==$$=#@@@@
@@@@@@@@@=:::;!!=*;!**==#@@@@@
@@@@@@@@#$;;!!*=#*;;;!**#@@@@@
@@@@@@@@*;!!==!=@=*!;!*##@@@@@
@@@@@@@@:,-~~!**@@=!;;!=@@@@@@
@@@@@@@@$*=*;;=#@@==!!***$;=#@
@@@@@@@#==$###$#@@@$**=***!:,=
@@@@@@@###@@@@##@@@@@#$###@$@@
');
insert into monster(mname , mimage)values ('슬라임' , '
                 ~-~~-        
      .         ~;-  !=       
     :-         ::    ,*      
     ;:-       ;~.-    =      
      =-      =:-, .   .;     
      ,    .-.:~-,.~.  .@     
      *  ,;.~:~-,,   ;.,-     
       #~,-::~-,,.    ,=      
       ~~:::~--,,.     .      
      ,~ #::~-,,,~            
      ,:@@::~-,# @..    #     
     #,:;;!;~-,@.@, ... #     
     #,:;::#=-,,--,,.  .$     
     .-:;::##--,,,,,, . #     
      ,-~::!---,,,,,,,..-     
      --,~::!~--,,,,,, !      
       ---:::~~~----,-!       
        -;:-~~~~~-,!;-        
           ::,  ::~           
                              
');


insert into skill(skname,skinfo,skdamage,skmoney) values ('기본공격', '기본공격을 합니다.','0',0);
insert into skill(skname,skinfo,skdamage,skmoney) values ('참격','참격을날립니다.',10, 10);
insert into skill(skname,skinfo,skdamage,skmoney) values ('파이어볼','파이어볼 날립니다.',20, 20);
insert into skill(skname,skinfo,skdamage,skmoney) values ('활쏘기','화살을 쏩니다.',30, 30);

insert into Myskill(ckey, skkey) values (1,2);
insert into Myskill(ckey, skkey) values (1,1);
insert into Myskill(ckey, skkey) values (2,1);
insert into Myskill(ckey, skkey) values (3,3);

-- select * from mycharacter inner join myaccount on mycharacter.akey = myaccount.akey;
-- select *from Myskill inner join skill on Myskill.skkey = skill.skkey where ckey = 1 and skname = '나';

-- insert into friends(fromckey, tockey) values (1,2);
-- insert into friends(fromckey, tockey) values (1,3);
/*
select * from freinds inner join mycharacter on freinds.tockey = mycharacter.ckey where fromckey = 1;

select * from friends;
drop table if exists friends;

select * from freinds inner join mycharacter on freinds.tockey = mycharacter.ckey where fromckey = '1' and state = '1' ;
select * from mycharacter ;

select * from skill ;

update mycharacter set cexp = 90 where ckey = 1;



-- delete from freinds where tockey = (select tockey from(select mycharacter.ckey from freinds freinds inner join mycharacter on freinds.tockey = mycharacter.ckey where cnickname = '네이버')as a_t) and fromckey = 1;

-- select * from freinds inner join mycharacter on freinds.tockey = mycharacter.ckey where (fromckey = 1 or tockey = 1) and state = '0';

SELECT f.*, from_char.*, to_char.*
FROM friends f
INNER JOIN mycharacter from_char ON f.fromckey = from_char.ckey
INNER JOIN mycharacter to_char ON f.tockey = to_char.ckey
WHERE (f.fromckey = 2 OR f.tockey = 2)
AND f.state = '0';


update mycharacter set cmoney = 10000 where ckey = 1;
*/

