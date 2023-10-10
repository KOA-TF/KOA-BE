CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    profile_image_url VARCHAR(255),
    password VARCHAR(255),
    authority VARCHAR(50)
);

CREATE TABLE memberinfo (
    member_info_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    major VARCHAR(255),
    part VARCHAR(255),
    phone_number VARCHAR(20),
    description TEXT,
    member_id BIGINT,
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE curriculum (
    curriculum_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    curriculum_name VARCHAR(255),
    notice_id BIGINT,
    FOREIGN KEY (notice_id) REFERENCES notice(notice_id)
);

CREATE TABLE notice (
    notice_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    member_id BIGINT,
    curriculum_id BIGINT,
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    FOREIGN KEY (curriculum_id) REFERENCES curriculum(curriculum_id)
);

CREATE TABLE notice_team (
     notice_team_id BIGINT AUTO_INCREMENT PRIMARY KEY,
     team_name VARCHAR(255),
     notice_id BIGINT,
     FOREIGN KEY (notice_id) REFERENCES notice(notice_id)
);

CREATE TABLE notice_view (
     notice_view_id BIGINT AUTO_INCREMENT PRIMARY KEY,
     view_count INT,
     member_id BIGINT,
     notice_id BIGINT,
     FOREIGN KEY (member_id) REFERENCES member(member_id),
     FOREIGN KEY (notice_id) REFERENCES notice(notice_id)
);