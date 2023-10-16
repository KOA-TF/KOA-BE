INSERT INTO member (member_id, name, email, profile_image_url, password, authority)
values (1, '안정후', 'ajh@gmail.com', 'picture.com', '20001215', 'MEMBER');

INSERT INTO member_info (member_info_id, member_id, major, part, phone_number, description)
values (1, 1, '전자공학과', '개발팀', '010-6570-8852', '설명입니다.');

INSERT INTO curriculum (curriculum_id, curriculum_name)
values (1, '기업프로젝트');

INSERT INTO notice (notice_id, member_id, curriculum_id, title, content)
values (1, 1, 1, '제목입니다.', '내용입니다.');

INSERT INTO comment (comment_id, content, member_id, notice_id, parent_id)
values (1, '댓글입니다.', 1, 1, 1);

-- INSERT INTO report (report_id, content, member_id, comment_id)
-- values (1, '신고 내용입니다', 1, 1);

UPDATE Curriculum
SET notice_id = 1
WHERE curriculum_id = 1;
