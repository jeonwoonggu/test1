drop sequence board_login_inst_seq;
create sequence board_login_inst_seq nocache;


drop table board_member;
create table board_member(
	id varchar2(100) primary key,
	password varchar2(100) not null,
	name varchar2(100) not null
)
insert into board_member(id,password,name) values('java','1234','정우성');
insert into board_member(id,password,name) values('spring','1234','전지현');

drop table board_login_inst;
create table board_login_inst(
	no number primary key,
	category varchar2(50) not null,
	title varchar2(50) not null,
    content clob not null,
	time_posted date not null,
	hits number default 0,
	ghits number default 0,
	id varchar2(100) not null,
	constraint board_login_fk foreign key(id) references board_member(id)
)

select board_login_inst_seq.nextval from dual;

insert into board_login_inst(no,category,title,content,time_posted,id) 
values(board_login_inst_seq.nextval,'추천','방가','ㅋㅋ',sysdate,'java');
insert into board_login_inst(no,category,title,content,time_posted,id) 
values(board_login_inst_seq.nextval,'비추천','안녕','치맥',sysdate,'spring');
select * from board_login_inst;

commit

-- hint 
/*
SELECT  리스트 페이지에서 필요한 컬럼 (게시물과 회원 관련 컬럼) FROM(
	SELECT row_number 이용한  Board SQL
) b,member m 
where 조인조건 and between 
order by 
*/



SELECT b.no,b.title,b.time_posted,b.hits,b.id,m.name FROM(
		SELECT row_number() over(order by no desc) as rnum,no,title,hits,
		to_char(time_posted,'YYYY.MM.DD') as time_posted,id
		FROM board_login_inst
		) b,board_member m where b.id=m.id and rnum between 1 and 5
order by no desc
		
		
		
		
