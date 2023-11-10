-- notice 테이블 sample datas
INSERT INTO member (member_id, name, email, password, fcm_token, authority)
values (1, '안정후', 'ajh@gmail.com', '20001215', 'fcm_token', 'MEMBER');

INSERT INTO member_info (member_info_id, member_id, major, part, phone_number, description)
values (1, 1, '전자공학과', '개발팀', '010-6570-8852', '설명입니다.');

INSERT INTO curriculum (curriculum_id, curriculum_name)
values (1, '기업프로젝트');

INSERT INTO notice (notice_id, member_id, curriculum_id, title, content, is_deleted)
values (1, 1, 1, '제목입니다.', '내용입니다.', false);

INSERT INTO notice (notice_id, member_id, curriculum_id, title, content, is_deleted)
values (2, 1, 1, '제목입니다.2', '내용입니다.2', false);

INSERT INTO notice_team (notice_team_id, notice_id, team_name)
values (1, 1, '경영총괄팀');

INSERT INTO notice_image (notice_image_id, notice_id, image_url, is_deleted)
values (1, 1, 'image.png', false);

INSERT INTO notice_view (notice_view_id, member_id, notice_id, view)
values (1, 1, 1, 1);
