
GRANT CONNECT, RESOURCE TO madang;

drop sequence seq_questionboard;
drop table prj_board;
drop table question_board;

create sequence seq_questionboard;

create table question_board(
bno number(10,0),
title varchar2(200)not null,
message varchar2(2000)not null,
name varchar2(50)not null,
Email varchar2(100) not null,
regdate date default sysdate
)

alter table question_board add constraint pk_board
primary key(bno);

insert into question_board (bno,title,name,message,email)
values(Seq_questionboard.nextval,'문의사항1','아이언맨','프로젝트가 어렵습니다','ironman@gmail.com');

insert into question_board (bno,title,name,message,email)
values(Seq_questionboard.nextval,'문의사항2','헐크','쇼핑몰은어떻게만듭니까','hulk@gmail.com');

commit;