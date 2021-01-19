create table guci_question(
quesNo number,
quesCateCode VARCHAR2(50) not null,
quesTit VARCHAR2(50) not null,
quesCon VARCHAR2(1000) not null,
quesWri VARCHAR2(30) not null,
quesDate date DEFAULT sysdate

);
drop table guci_question;
CREATE SEQUENCE seq_guciquestion INCREMENT BY 1 START WITH 0 MINVALUE 0;

alter table guci_question  add constraint pk_guci_question primary key(quesNo);
select * from  guci_question;

-----------------------------------------------------------------------------------------------------
commit;
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) values(seq_guciquestion.nextval, '기본제목', '기본내용','기본사용자','a-1');
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) values(seq_guciquestion.nextval, '기본제목', '기본내용','기본사용자','a-2');
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) values(seq_guciquestion.nextval, '기본제목', '기본내용','기본사용자','b-1');
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) values(seq_guciquestion.nextval, '기본제목', '기본내용','기본사용자','b-2');

commit;

insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) (select seq_guciquestion.nextval, quesTit,quesCon, quesWri,quesCateCode from guci_question);
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) (select seq_guciquestion.nextval, quesTit,quesCon, quesWri,quesCateCode from guci_question);
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) (select seq_guciquestion.nextval, quesTit,quesCon, quesWri,quesCateCode from guci_question);
insert into guci_question(quesNo, quesTit, quesCon, quesWri,quesCateCode) (select seq_guciquestion.nextval, quesTit,quesCon, quesWri,quesCateCode from guci_question);
commit;


select * from guci_question;
--------------------------------------------댓글답변------------------------------------

create table reply(
replyNo number(10,0),
quesNo number(10,0) not null,
replyCon VARCHAR2(1000) not null,
replyWri VARCHAR2(30) default '관리자',
replyDate date default sysdate
);

drop table reply;
CREATE SEQUENCE seq_reply INCREMENT BY 1 START WITH 0 MINVALUE 0;

alter table reply add constraint pk_reply primary key(replyNo);

alter table reply add constraint fk_reply
foreign key(quesNo) references guci_question(quesNo);

select * from guci_question where rownum <10 order by quesNO desc;

---------------------------------첨부파일테이블--------------------------------------------------------------------------

create table guci_question_attach(
    uuid        varchar2(100) not null,
    uploadPath  varchar2(200) not null,
    fileName    varchar2(100) not null,
    fileType    char(1) default 'I',
    quesNo         number(10,0)
);

drop table guci_question_attach;

alter table guci_question_attach add constraint pk_question_attach primary key(uuid);

alter table guci_question_attach add constraint fk_question_attach foreign key(quesNo) references guci_question(quesNo);

commit;
-----------------댓글의 갯수를 보기위한쿼리-------------------------------
alter table guci_question add (replycnt number default 0);

update guci_question set replycnt = (
                                select count(replyNo) 
                                from reply
                                where reply.quesNo = guci_question.quesNo);