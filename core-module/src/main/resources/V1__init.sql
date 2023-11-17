CREATE TABLE member
(
    member_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(255),
    "period"          VARCHAR(255),
    email             VARCHAR(255),
    phone_number      VARCHAR(255),
    password          VARCHAR(255),
    fcm_token         VARCHAR(255),
    authority         VARCHAR(50),
    is_deleted        BOOLEAN
);

CREATE TABLE curriculum
(
    curriculum_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    curriculum_name VARCHAR(255),
    notice_id       BIGINT,
    FOREIGN KEY (notice_id) REFERENCES notice (notice_id)
);

CREATE TABLE notice
(
    notice_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(255),
    content          TEXT,
    is_deleted       BOOLEAN,
    member_id        BIGINT,
    curriculum_id    BIGINT,
    notice_team_id   BIGINT,
    notice_image_id  BIGINT,
    notice_view_id   BIGINT,
    FOREIGN KEY (member_id) REFERENCES member (member_id),
    FOREIGN KEY (curriculum_id) REFERENCES curriculum (curriculum_id),
    FOREIGN KEY (notice_team_id) REFERENCES  notice_team (notice_team_id),
    FOREIGN KEY (notice_image_id) REFERENCES  notice_image (notice_image_id),
    FOREIGN KEY (notice_view_id) REFERENCES notice_view (notice_view_id)
);

CREATE TABLE notice_image
(
    notice_image_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image_url       VARCHAR(255),
    is_deleted      BOOLEAN
);

CREATE TABLE notice_team
(
    notice_team_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_name      VARCHAR(255)
);

CREATE TABLE notice_view
(
    notice_view_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    view           VARCHAR(255),
    member_id      BIGINT,
    notice_id      BIGINT,
    FOREIGN KEY (member_id) REFERENCES member (member_id),
    FOREIGN KEY (notice_id) REFERENCES notice (notice_id)
);
