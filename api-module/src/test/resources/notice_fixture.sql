-- notice 테이블 sample datas
INSERT INTO member (member_id, name, email, profileImageUrl, password, authority)
values (1, '안정후', 'ajh@gmail.com', 'picture.com', '20001215', 'MEMBER');

INSERT INTO memberinfo (member_info_id, major, part, phoneNumber, description)
values (1, '전자공학과', '개발팀', '010-6570-8852', '설명입니다.');

INSERT INTO notice (notice_id, title, content)
values (1, '제목입니다.', '내용입니다.');

INSERT INTO noticeteam (notice_team_id, teamName)
values (1, '경영총괄팀');

INSERT INTO noticeview (notice_view_id, view)
values (1, 1);