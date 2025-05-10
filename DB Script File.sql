drop database StudentDB;

CREATE DATABASE student_db;

USE student_db;

studentsCREATE TABLE students (
    student_id VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    dob DATE,
    gender VARCHAR(10)
);

Select * from students;

