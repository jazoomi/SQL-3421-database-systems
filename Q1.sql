CREATE database IF NOT EXISTS Q1;
USE Q1;

CREATE TABLE Instructor (
	email varchar(50) NOT NULL,
	name varchar(20),
    affiliation varchar(255),
    primary key(email)
);

CREATE TABLE students (
	email varchar(255) NOT NULL,
	studentID int NOT NULL UNIQUE AUTO_INCREMENT,
	primary key(email, studentID)
);
CREATE TABLE courses (
	course_code int NOT NULL,
    year int,
    term int,
	primary key(course_code)

);
CREATE TABLE assignment (
	submission int,
	primary key(submission)
);
CREATE TABLE task (
	A_submission int,
    max_grade int NOT NULL,
    PRIMARY KEY(max_grade, A_submission),
    Foreign key (A_submission) REFERENCES assignment(submission)
);
CREATE TABLE adds (
	A_studentID int NOT NULL,
    A_Course_Code int NOT NULL,
    PRIMARY KEY(A_studentID, A_Course_Code),
    Foreign key (A_studentID) REFERENCES students(studentID),
    foreign key(A_Course_Code) references courses(course_code)
);
CREATE TABLE makes (
	Iemail varChar(50) NOT NULL,
    A_Submission int,
    PRIMARY KEY(Iemail, A_Submission),
    Foreign key (Iemail) REFERENCES Instructor(email),
    foreign key(A_Submission) references assignment(submission)
);

CREATE TABLE creates(
	Iemail varchar(50) NOT NULL,
    A_Course_Code int NOT NULL,
    PRIMARY KEY(Iemail, A_Course_Code),
    Foreign key (Iemail) references Instructor(email),
    foreign key(A_Course_Code) references courses(Course_Code)
);

describe courses;
