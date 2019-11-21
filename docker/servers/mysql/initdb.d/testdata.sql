# noinspection SqlResolveForFile
# noinspection SqlNoDataSourceInspectionForFile

# secret_questions
INSERT INTO secret_questions
VALUES (1, 'あなたの親の旧姓は？');
INSERT INTO secret_questions
VALUES (2, 'あなたが生まれた頃に住んでいた場所は？');
INSERT INTO secret_questions
VALUES (3, 'あなたの初めてのペットの名前は？');
INSERT INTO secret_questions
VALUES (4, 'あなたの思い出の場所は？');
INSERT INTO secret_questions
VALUES (5, 'あなたが通っていた小学校の名前は？');
INSERT INTO secret_questions
VALUES (6, 'あなたの尊敬してる人は？');
INSERT INTO secret_questions
VALUES (7, 'あなたの好きなアーティストは？');
INSERT INTO secret_questions
VALUES (8, 'あなたの苦手だった教科は？');

# users
INSERT INTO users(id, user_id, first_name, last_name, nickname, email_address, openness, password, secret_question_id,
                  secret_answer)
VALUES (1, 'root', 'root', 'root', 'root', 'root', false, 'root', 1, 'root');



INSERT INTO users(user_id, first_name, last_name, nickname, email_address, openness, password, secret_question_id,
                  secret_answer)
VALUES ('nullpo299', 'Haruto', 'Nakazawa', 'haruji', 'x17u026@chiba-fjb.ac.jp', true, 'password', 3, 'ぷりん');

INSERT INTO users(user_id, first_name, last_name, nickname, email_address, openness, password, secret_question_id,
                  secret_answer)
VALUES ('hirabo', 'Hiraku', 'Fukui', 'hirabo', 'x17u027@chiba-fjb.ac.jp', true, 'password', 5, '泊小学校');

INSERT INTO users(user_id, first_name, last_name, nickname, email_address, openness, password, secret_question_id,
                  secret_answer)
VALUES ('yuto', 'Yuto', 'Kohsaka', 'yuto', 'x17u011@chiba-fjb.ac.jp', true, 'password', 7, '森山 直太郎');


# files
INSERT INTO files(id, name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES (1, 'root', 1, 0, false, false, 1, 1);


INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('nullpo299', 1, 0, true, true, 1, 1);

INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('super', 2, 0, true, true, 1, 2);

INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('multi', 3, 0, true, true, 1, 2);

INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('otinpo', 4, 0, true, true, 1, 2);

INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('outfile', 2, 0, false, true, 1, 2);

INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('hirabo', 1, 0, true, true, 1, 1);


INSERT INTO files(name, parent_id, size, is_directory, openness, thumbnail_id, owner_user_id)
VALUES ('yuto', 1, 0, true, true, 1, 1);
