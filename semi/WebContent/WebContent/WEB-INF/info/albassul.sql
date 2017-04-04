drop sequence board_no_seq;

create sequence board_no_seq nocache;

drop table alba_board cascade constraints;
drop table alba_member cascade constraints;
drop table alba_likes cascade constraints;
drop table alba_reply cascade constraints;

create table alba_member(
   member_id varchar2(100) primary key,
   name varchar2(100) not null,
   address varchar2(100) not null,
   tel varchar2(100) not null,
   residentnumber varchar2(100) not null,
   gender varchar2(100) not null,
   password varchar2(100) not null,
   nickname varchar2(100) not null
)

insert into alba_member(member_id,password,name,residentnumber,gender,nickname,tel,address)
 values('java','1234','아이유','930516','woman','IU','031','판교');
insert into alba_member(member_id,password,name,residentnumber,gender,nickname,tel,address)
 values('ajax','abcd','강동원','810118','man','mr.kang','02','강남');

select * from alba_member;

create table alba_board(
	board_no number primary key,
	category varchar2(100) not null,
	title varchar2(100) not null,
    content clob not null,
	time_posted date not null,
	hits number default 0,
	likes number default 0,
	member_id varchar2(100) not null,
	constraint alba_board_fk foreign key(member_id) references alba_member(member_id)
)

create table alba_likes(
   member_id varchar2(100) not null,
   constraint likes_id foreign key(member_id) references alba_member(member_id),
   board_no number not null,
   constraint likes_no foreign key(board_no) references alba_board(board_no)
)

create table alba_reply(
reply_id number  primary key, -- 댓글 번호
 article_id number not null, --게시물 번호
 member_id varchar2(100) not null, -- 작성자
 reply_date date, -- 작성일자
 description varchar2(1000) not null, -- 댓글 내용
 group_id number not null, -- 댓글과 대댓글 하나의 그룹번호
 parent_id number default 0, -- 부모 댓글 표시
 depth number default 0, -- 몇대손인지 depth
 order_id number default 1, -- 게시물 기준으로 몇번째인지
 constraint fk_article_no foreign key(article_id) references alba_board(board_no)
)

create sequence reply_id_seq;


select board_no_seq.nextval from dual;

-- 실행하지 마세요 --
insert into alba_board(board_no,category,title,content,time_posted,member_id) values(board_no_seq.nextval,'추천','치킨','ㅋㅋ',sysdate,'java');
insert into alba_board(board_no,category,title,content,time_posted,member_id) values(board_no_seq.nextval,'비추천','족발','ㅋㅋ',sysdate,'spring');
insert into alba_board(board_no,category,title,content,time_posted,member_id) values(board_no_seq.nextval,'추천','닭발','ㅋㅋ',sysdate,'jquery');
insert into alba_board(board_no,category,title,content,time_posted,member_id) values(board_no_seq.nextval,'비추천','햄버거','ㅋㅋ',sysdate,'ajax');
insert into alba_board(board_no,category,title,content,time_posted,member_id) values(board_no_seq.nextval,'추천','피자','ㅋㅋ',sysdate,'servlet');
insert into alba_board(board_no,category,title,content,time_posted,member_id) values(board_no_seq.nextval,'비추천','짬뽕','ㅋㅋ',sysdate,'jsp');

select * from alba_board;

commit

-- hint 
/*
SELECT  리스트 페이지에서 필요한 컬럼 (게시물과 회원 관련 컬럼) FROM(
	SELECT row_number 이용한  Board SQL
) b,member m 
where 조인조건 and between 
order by 
*/




		
		
		
		
