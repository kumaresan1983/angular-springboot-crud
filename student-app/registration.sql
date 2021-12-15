DROP DATABASE IF EXISTS registration;
CREATE DATABASE  IF NOT EXISTS registration ;
USE registration;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS students;
CREATE TABLE students (
  id varchar(36) NOT NULL,
  name varchar(50) NOT NULL,
  age int NOT NULL,
  contact_no varchar(15) NOT NULL,
  created_date timestamp NULL DEFAULT NULL,
  created_by varchar(50) DEFAULT NULL,
  last_modified_date timestamp NULL DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS courses;
CREATE TABLE courses (
  id varchar(36) NOT NULL,
  name varchar(45) DEFAULT NULL,
  fees decimal(6,2) DEFAULT NULL,
  created_date timestamp NULL DEFAULT NULL,
  created_by varchar(50) DEFAULT NULL,
  last_modified_date timestamp NULL DEFAULT NULL,
  last_modified_by varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` VALUES ('1e607d8e-bea6-460a-8ae4-987eb509a148','Maths',500.00,'2021-12-13 20:35:09','SYSTEM_USER','2021-12-13 20:35:09','SYSTEM_USER'),('1e607d8e-bea6-460a-8ae4-987eb509a149','Science',200.00,'2021-12-13 20:35:09','SYSTEM_USER','2021-12-13 20:35:09','SYSTEM_USER'),('1e607d8e-bea6-460a-8ae4-987eb509a150','History',250.00,'2021-12-13 20:35:09','SYSTEM_USER','2021-12-13 20:35:09','SYSTEM_USER'),('1e607d8e-bea6-460a-8ae4-987eb509a151','English',300.00,'2021-12-13 20:35:09','SYSTEM_USER','2021-12-13 20:35:09','SYSTEM_USER');

--
-- Table structure for table `student_courses`
--

DROP TABLE IF EXISTS student_courses;
CREATE TABLE student_courses (
  student_id varchar(36) NOT NULL,
  course_id varchar(36) NOT NULL,
  KEY student_id_idx (student_id),
  KEY course_id_idx (course_id),
  CONSTRAINT course_id FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT student_id FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE ON UPDATE CASCADE
) ;



