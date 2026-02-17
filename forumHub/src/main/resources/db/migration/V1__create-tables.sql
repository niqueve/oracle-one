CREATE TABLE profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
INSERT INTO profile (name) VALUES ('ROLE_ADMIN');
INSERT INTO profile (name) VALUES ('ROLE_PROFESSOR');
INSERT INTO profile (name) VALUES ('ROLE_ALUNO');

CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category ENUM("MOBILE", "PROGRAMACAO", "FRONTEND", "DEVOPS", "UX_DESIGNER", "DATA_SCIENCE", "INOVACAO_GESTAO", "IA") NOT NULL
);
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE user_profile (
    user_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, profile_id),
    CONSTRAINT fk_user_profile_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_user_profile_profile FOREIGN KEY (profile_id) REFERENCES profile(id)
);

CREATE TABLE topic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM("ATIVO", "INATIVO") NOT NULL,
    author_id BIGINT NOT NULL ,
    course_id BIGINT NOT NULL,

    CONSTRAINT fk_topic_author FOREIGN KEY (author_id) REFERENCES user(id),
    CONSTRAINT fk_topic_course FOREIGN KEY (course_id) REFERENCES course(id)

);

CREATE TABLE  answer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    topic_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    author_id BIGINT NOT NULL ,
    solution BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_answer_topic FOREIGN KEY (topic_id) REFERENCES topic(id),
    CONSTRAINT fk_answer_author FOREIGN KEY (author_id) REFERENCES user(id)
);