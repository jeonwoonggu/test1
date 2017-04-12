drop sequence board_no_seq;
drop sequence reply_id_seq;

create sequence board_no_seq nocache;
create sequence reply_id_seq;

drop table alba_board cascade constraints;
drop table alba_member cascade constraints;
drop table alba_likes cascade constraints;
drop table alba_reply cascade constraints;
drop table alba_report cascade constraints;
drop table alba_imgupload cascade constraints;


select * from alba_member;
select * from alba_board;
select * from ALBA_REPLY;
select * from alba_imgupload;
select * from alba_report;


create table alba_member(
   member_id varchar2(100) primary key,
   name varchar2(100) not null,
   address varchar2(100) not null,
   tel varchar2(100) not null,
   residentnumber varchar2(100) not null,
   gender varchar2(100) not null,
   password varchar2(100) not null,
   nickname varchar2(100) not null,
   deletemember varchar2(100) default 'true'
)


create table alba_board(
	board_no number primary key,
	category varchar2(100) not null,	
	title varchar2(100) not null,
    content clob not null,
	time_posted date not null,
	hits number default 0,
	likes number default 0,
	job varchar2(100) not null,
	startdate varchar2(100) not null,
	enddate varchar2(100) not null,
	member_id varchar2(100) not null,
	constraint alba_board_fk foreign key(member_id) references alba_member(member_id)
)


create table alba_likes(
   member_id varchar2(100) not null,
   constraint likes_id foreign key(member_id) references alba_member(member_id),
   board_no number not null,
   constraint likes_no foreign key(board_no) references alba_board(board_no) ON DELETE CASCADE
)

create table alba_report(
   member_id varchar2(100) not null,
   constraint report_id foreign key(member_id) references alba_member(member_id),
   board_no number not null,
   constraint report_no foreign key(board_no) references alba_board(board_no) ON DELETE CASCADE,
   content varchar2(100) not null --신고내역
)

create table alba_reply(
 reply_id number  primary key, -- 댓글 번호
 article_id number not null, --게시물 번호
 member_id varchar2(100) not null, -- 작성자
 nickname varchar2(100) not null,
 reply_date date, -- 작성일자
 description varchar2(1000) not null, -- 댓글 내용
 group_id number not null, -- 댓글과 대댓글 하나의 그룹번호
 parent_id number default 0, -- 부모 댓글 표시
 depth number default 0, -- 몇대손인지 depth
 order_id number default 1, -- 게시물 기준으로 몇번째인지
 constraint fk_article_no foreign key(article_id) references alba_board(board_no) ON DELETE CASCADE,
 constraint fk_member_id foreign key(member_id) references alba_member(member_id) -- 외래키 추가
)

create table alba_imgupload(
	img_no number not null,
	constraint fk_img_no foreign key(img_no) references alba_board(board_no) ON DELETE CASCADE ,
	board_imgsrc varchar2(100)
)
create table alba_admin(
admin_id varchar2(100) primary key,
admin_pass varchar2(100) not null
)
insert into alba_admin values('admin','0000');
select count(*) from alba_admin where admin_id='admin' and admin_pass='0000'
commit
