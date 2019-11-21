# noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE secret_questions
(
    id       TINYINT UNSIGNED,
    question VARCHAR(64) NOT NULL, # 制限なし 32文字まで
    INDEX (id),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE users
(
    id                 INT UNSIGNED AUTO_INCREMENT, # 代理キー
    user_id            VARCHAR(32),                 # 半角英数字-_合わせて32文字まで
    first_name         VARCHAR(64)      NOT NULL,   # 制限なし 32文字まで
    last_name          VARCHAR(64)      NOT NULL,   # 制限なし 32文字まで
    nickname           VARCHAR(64),                 # 制限なし 32文字まで
    email_address      VARCHAR(64)      NOT NULL,   # 半角英数字全体で64文字まで
    openness           BOOL             NOT NULL,
    password           CHAR(32)         NOT NULL,   # SHA256でハッシュ化するから32Byte
    secret_question_id TINYINT UNSIGNED NOT NULL,
    secret_answer      VARCHAR(64)      NOT NULL,   # 制限なし 32文字まで
    created_at         DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX (id),
    PRIMARY KEY (id),
    FOREIGN KEY (secret_question_id) REFERENCES secret_questions (id)
) ENGINE = InnoDB;

CREATE TABLE follows
(
    from_user_id INT UNSIGNED,
    to_user_id   INT UNSIGNED,
    followed_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX (from_user_id, to_user_id),
    PRIMARY KEY (from_user_id, to_user_id),
    FOREIGN KEY (from_user_id) REFERENCES users (id),
    FOREIGN KEY (to_user_id) REFERENCES users (id)
) ENGINE = InnoDB;

CREATE TABLE blocks
(
    from_id_user INT UNSIGNED,
    to_user_id   INT UNSIGNED,
    blocked_at   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX (from_id_user, to_user_id),
    PRIMARY KEY (from_id_user, to_user_id),
    FOREIGN KEY (from_id_user) REFERENCES users (id),
    FOREIGN KEY (to_user_id) REFERENCES users (id)
) ENGINE = InnoDB;

CREATE TABLE files
(
    id            INT UNSIGNED AUTO_INCREMENT,
    name          VARCHAR(128) NOT NULL, # ファイル名64文字まで
    parent_id     INT UNSIGNED NOT NULL, # 親のファイルIDをもつ
    size          INT UNSIGNED NOT NULL, # kb単位で保持
    is_directory  BOOL         NOT NULL,
    openness      BOOL         NOT NULL,
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    thumbnail_id  INT UNSIGNED NOT NULL,
    owner_user_id INT UNSIGNED NOT NULL,
    INDEX (id, parent_id, owner_user_id),
    PRIMARY KEY (id),
    FOREIGN KEY (owner_user_id) REFERENCES users (id),
    FOREIGN KEY (parent_id) REFERENCES files (id)
) ENGINE = InnoDB;

CREATE TABLE trash
(
    id         INT UNSIGNED,
    deleted_at DATETIME     NOT NULL,
    INDEX (id),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES files (id)
) ENGINE = InnoDB;

