INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender) VALUES
('Marko','Petrovic','1203983840001', 'Liman 3, Novi Sad', 'markop','$2a$12$cTceNi6rBsXNRk5P2Nc5iu.H8hf0eg03446/LF2qtKUmhFkofyn3W
', 'TEACHER', 'MALE');

INSERT INTO user(name,lastname,jmbg,address,username,password,role,gender) VALUES
    ('Nikola','Markovic','1201983840001', 'Silbas', 'nikola','$2a$12$cTceNi6rBsXNRk5P2Nc5iu.H8hf0eg03446/LF2qtKUmhFkofyn3W
', 'STUDENT', 'MALE');

INSERT INTO teacher(user_id) VALUES  (1);
INSERT INTO student(user_id,index_number) VALUES  (1,'RA-123/2020');

INSERT INTO teacher_role(name) VALUES ('PREDAVAC');
INSERT INTO teacher_role(name) VALUES ('ASISTENT');
INSERT INTO teacher_role(name) VALUES ('PROFESOR');

INSERT into study_type(name) values ('Osnovne strukovne studije');
INSERT into study_type(name) values ('Osnovne akademske studije');

insert into study_program(name,study_length, study_type_id) values ('Softverske i informacione tehn',3, 1);
insert into study_program(name,study_length, study_type_id) values ('Racunarstvo i automatika',4, 2);

INSERT into course(espb,name,sylabus) values ('8ESPB','Osnove web programiranje','Lorem');
INSERT into course(espb,name,sylabus) values ('4ESPB','Web Dizajn','Lorem');

insert into study_program_courses(courses_id, study_program_id) values (1,1);
insert into study_program_courses(courses_id, study_program_id) values (2,1);

insert into studying_information(financial_type,school_year,start_school_year,student_user_id,study_program_id) VALUES
('budget','3','2019',2,1);

insert into performance(school_year, course_id) values ('2022', 1);
insert into performance(school_year, course_id) values ('2022', 2);

insert into performance_teacher_relationship(performance_id, teacher_id) values (1, 1);
insert into performance_teacher_relationship(performance_id, teacher_id) values (2, 1);

insert into attending(performance_id,student_user_id) values (1,2);
insert into attending(performance_id,student_user_id) values (2,2);













INSERT INTO exam_period(end_date,start_date, name) VALUES ('2022-05-05','2022-05-01', 'aprilski');

