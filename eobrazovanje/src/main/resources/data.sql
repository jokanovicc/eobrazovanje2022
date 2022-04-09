INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender) VALUES
('Marko','Petrovic','1203983840001', 'Liman 3, Novi Sad', 'markop','$2a$12$cTceNi6rBsXNRk5P2Nc5iu.H8hf0eg03446/LF2qtKUmhFkofyn3W
', 'TEACHER', 'MALE');

INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender) VALUES
    ('Nikola','Markovic','1201983840001', 'Silbas', 'nikola','$2a$12$cTceNi6rBsXNRk5P2Nc5iu.H8hf0eg03446/LF2qtKUmhFkofyn3W
', 'STUDENT', 'MALE');

INSERT INTO teacher(user_id) VALUES  (1);
INSERT INTO student(user_id,index_number) VALUES  (2,'RA-123/2020');

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

insert into study_program_courses(courses_id, study_program_id) values (1,1);
insert into study_program_courses(courses_id, study_program_id) values (2,1);
insert into study_program_courses(courses_id, study_program_id) values (3,1);
insert into study_program_courses(courses_id, study_program_id) values (4,1);

insert into studying_information(financial_type,school_year,start_school_year,student_user_id,study_program_id) VALUES
('budget','3','2019',2,1);

insert into performance(school_year, course_id) values ('2022', 1);
insert into performance(school_year, course_id) values ('2022', 2);
insert into performance(school_year, course_id) values ('2022', 3);
insert into performance(school_year, course_id) values ('2022', 4);

insert into performance_teacher_relationship(performance_id, teacher_id) values (1, 1);
insert into performance_teacher_relationship(performance_id, teacher_id) values (2, 1);
insert into performance_teacher_relationship(performance_id, teacher_id) values (3, 1);
insert into performance_teacher_relationship(performance_id, teacher_id) values (4, 1);

insert into attending(performance_id,student_user_id) values (1,2);
insert into attending(performance_id,student_user_id) values (2,2);
insert into attending(performance_id,student_user_id) values (3,2);
insert into attending(performance_id,student_user_id) values (4,2);

insert into exam_period(start_date, end_date, name) values ('2022-01-15', '2022-01-30', 'januarski');
insert into exam_period(start_date, end_date, name) values ('2022-02-01', '2022-02-17', 'februarski');
insert into exam_period(start_date, end_date, name) values ('2022-04-09', '2022-05-05', 'aprilski aktuelni');

insert into exam(classroom, date, pre_exam_duty_points, final_exam_points, grade, status, attending_id)
values ('417', '2022-01-17', 45, 31, 8, 'PASSED', 1);

insert into exam(classroom, date, pre_exam_duty_points, final_exam_points, grade, status, attending_id)
values ('310', '2022-01-25', 0, 0, null, 'REGISTERED', 2);

insert into exam(classroom, date, pre_exam_duty_points, final_exam_points, grade, status, attending_id)
values ('312', '2022-01-26', 0, 0, null, 'FAILED', 3);

insert into exam(classroom, date, pre_exam_duty_points, final_exam_points, grade, status, attending_id)
values ('312', '2022-02-13', 41, 40, 9, 'PASSED', 3);

insert into exam_registration(exam_id, exam_period_id, student_user_id) values (1, 1, 2);
insert into exam_registration(exam_id, exam_period_id, student_user_id) values (2, 1, 2);
insert into exam_registration(exam_id, exam_period_id, student_user_id) values (3, 1, 2);
insert into exam_registration(exam_id, exam_period_id, student_user_id) values (4, 2, 2);


