-- notice 테이블 sample datas
INSERT INTO member (member_id, name, email, profile_image_url, password, authority)
values (1, '안정후', 'ajh@gmail.com', 'picture.com', '20001215', 'MEMBER');

INSERT INTO member_info (member_info_id, member_id, major, part, phone_number, description)
values (1, 1, '전자공학과', '개발팀', '010-6570-8852', '설명입니다.');

INSERT INTO curriculum (curriculum_id, curriculum_name)
values (1, '기업프로젝트');

INSERT INTO notice (notice_id, member_id, curriculum_id, title, content)
values (1, 1, 1, '제목입니다.', '내용입니다.');

INSERT INTO notice (notice_id, member_id, curriculum_id, title, content)
values (2, 1, 1, '제목입니다.2', '내용입니다.2');

INSERT INTO notice_team (notice_team_id, notice_id, team_name)
values (1, 1, '경영총괄팀');

INSERT INTO notice_image (notice_image_id, notice_id, image_url)
values (1, 1, 'image.png');

INSERT INTO notice_view (notice_view_id, member_id, notice_id, view)
values (1, 1, 1, 1);
