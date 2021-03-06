INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender, email) VALUES
    ('Marko','Petrovic','1203983840001', 'Liman 3, Novi Sad', 'markop','$2a$10$QtN25QQqHmQuBzvI4my65O1zQp5lQihr7FFD5Ru4bV7ojhcFGPQ92
', 'TEACHER', 'MALE', 'petrovic@gmail.com');

INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender, email) VALUES
    ('Nikola','Markovic','1201983840001', 'Silbas', 'nikola','$2a$10$QtN25QQqHmQuBzvI4my65O1zQp5lQihr7FFD5Ru4bV7ojhcFGPQ92
', 'STUDENT', 'MALE', 'markovic@gmail.com');

INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender, email) VALUES
    ('Novica','Nedeljkovic','1103981840001', 'Vracar Historical, Beograd', 'novica','$2a$12$cTceNi6rBsXNRk5P2Nc5iu.H8hf0eg03446/LF2qtKUmhFkofyn3W
', 'TEACHER', 'MALE', 'nedeljkovic@gmail.com');

INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender, email) VALUES
    ('Jovica','Jovanovic','1103981840001', 'Vracar Historical, Beograd', 'novica','$2a$12$cTceNi6rBsXNRk5P2Nc5iu.H8hf0eg03446/LF2qtKUmhFkofyn3W
', 'STUDENT', 'MALE', 'jovanovic@gmail.com');

INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender, email) VALUES
    ('Ado','Adminic','11039123213431', 'Nemanjina, Beograd', 'ado','$2a$12$lGAL1aaPLq8CC5YcDbhLj.61yYKK.6UMouXP6fy/vLWLWAb6G/1si', 'ADMIN', 'MALE', 'adminic@gmail.com');

INSERT INTO teacher(user_id) VALUES  (1);
INSERT INTO student(user_id,index_number,password_token,first_login,reference_number, completedsvform) VALUES  (2,'RA-123/2020','',true,'1ADRHF322', true);
INSERT INTO teacher(user_id) VALUES  (3);
INSERT INTO student(user_id,index_number,password_token,first_login,reference_number, completedsvform) VALUES  (4,'RA-22/2020','',true,'1ASDFF322s', false);

INSERT INTO admin(user_id) VALUES  (5);

INSERT INTO teacher_role(name) VALUES ('PREDAVAC');
INSERT INTO teacher_role(name) VALUES ('ASISTENT');
INSERT INTO teacher_role(name) VALUES ('PROFESOR');

INSERT into study_type(name) values ('Osnovne strukovne studije');
INSERT into study_type(name) values ('Osnovne akademske studije');

insert into study_program(name,study_length, study_type_id) values ('Softverske i informacione tehn',3, 1);
insert into study_program(name,study_length, study_type_id) values ('Racunarstvo i automatika',4, 2);

INSERT into course(espb,name,sylabus) values ('8ESPB','Osnove web programiranje','Lorem');
INSERT into course(espb,name,sylabus) values ('4ESPB','Web Dizajn','Lorem');
INSERT into course(espb,name,sylabus) values ('6ESPB','Baze podataka','Lorem');
INSERT into course(espb,name,sylabus) values ('6ESPB','Osnove softverskih arhitektura','Lorem');
INSERT into course(espb,name,sylabus) values ('8ESPB','E Obrazovanje','Lorem');

insert into study_program_courses(courses_id, study_program_id) values (1,1);
insert into study_program_courses(courses_id, study_program_id) values (2,1);
insert into study_program_courses(courses_id, study_program_id) values (3,1);
insert into study_program_courses(courses_id, study_program_id) values (4,1);
insert into study_program_courses(courses_id, study_program_id) values (5,1);

insert into studying_information(financial_type,school_year,start_school_year,student_user_id,study_program_id) VALUES
    ('budget','3','2019',2,1);

insert into performance(school_year, course_id) values ('2022', 1);
insert into performance(school_year, course_id) values ('2022', 2);
insert into performance(school_year, course_id) values ('2022', 3);
insert into performance(school_year, course_id) values ('2022', 4);
insert into performance(school_year, course_id) values ('2022', 5);

INSERT into course_teacher(teacher_user_id, teacher_role_id) values (1, 1);
INSERT into course_teacher(teacher_user_id, teacher_role_id) values (3, 2);

insert into performance_teacher_relationship(performance_id, teacher_id) values (1, 1);
insert into performance_teacher_relationship(performance_id, teacher_id) values (1, 2);
insert into performance_teacher_relationship(performance_id, teacher_id) values (2, 1);

insert into attending(performance_id,student_user_id) values (1,2);
insert into attending(performance_id,student_user_id) values (2,2);
insert into attending(performance_id,student_user_id) values (3,2);
insert into attending(performance_id,student_user_id) values (4,2);
insert into attending(performance_id,student_user_id) values (5,2);

insert into exam_period(start_date, end_date, name) values ('2022-01-15', '2022-01-30', 'januarski');
insert into exam_period(start_date, end_date, name) values ('2022-04-01', '2022-02-17', 'februarski');
insert into exam_period(start_date, end_date, name) values ('2022-04-15', '2022-05-30', 'majski aktuelni');
insert into exam_period(start_date, end_date, name) values ('2022-06-03', '2022-06-20', 'junski kasniji al moze');
insert into exam_period(start_date, end_date, name) values ('2022-07-08', '2022-07-29', 'julski kasniji');

insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 1, '2022-01-17', '417', 1, 'UNRATED');
insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 2, '2022-01-25', '310', 1, 'UNRATED');
insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 3, '2022-01-26', '312', 1, 'UNRATED');
insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 3, '2022-02-13', '312', 2, 'UNRATED');
insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 4, '2022-05-30', '314', 3, 'UNRATED');
insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 5, '2022-05-15', '315', 3, 'UNRATED');
insert into performance_exam(performance_id, date, classroom, exam_period_id, status) values ( 5, '2022-06-06', '330', 4, 'UNRATED');

insert into exam(pre_exam_duty_points, final_exam_points, grade, status, attending_id, exam_id)
values ( 45, 31, 8, 'PASSED', 1, 1);

insert into exam(pre_exam_duty_points, final_exam_points, grade, status, attending_id, exam_id)
values ( 0, 0, null, 'REGISTERED', 2, 2);

insert into exam(pre_exam_duty_points, final_exam_points, grade, status, attending_id, exam_id)
values (0, 0, null, 'FAILED', 3, 3);

insert into exam(pre_exam_duty_points, final_exam_points, grade, status, attending_id, exam_id)
values (41, 40, 9, 'PASSED', 3, 4);

insert into exam_registration(exam_id, exam_period_id, student_user_id) values (1, 1, 2);
insert into exam_registration(exam_id, exam_period_id, student_user_id) values (2, 1, 2);
insert into exam_registration(exam_id, exam_period_id, student_user_id) values (3, 1, 2);
insert into exam_registration(exam_id, exam_period_id, student_user_id) values (4, 2, 2);

insert into financial_card(student_user_id, balance) values (2, 400);

insert into payment(amount,payment_date,text,student_user_id) values (-300,'2022-01-15','Prijava ispita',2);
insert into payment(amount,payment_date,text,student_user_id) values (3000,'2022-05-15','Ovjera semestra',2);
insert into payment(amount,payment_date,text,student_user_id) values (-300,'2022-01-15','Prijava ispita',2);
insert into payment(amount,payment_date,text,student_user_id) values (1000,'2022-01-15','Za ispitni rok',2);
insert into payment(amount,payment_date,text,student_user_id) values (-300,'2022-01-15','Prijava ispita',2);
insert into payment(amount,payment_date,text,student_user_id) values (-300,'2022-01-15','Prijava ispita',2);

insert into notification(message, performance_id, teacher_user_id) values ('aaa', 1, 1);


