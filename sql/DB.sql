# CREATE DATABASE library;

DROP TABLE audioList;
DROP TABLE volunteer;
DROP TABLE readingRoom;
DROP TABLE users_authority;
DROP TABLE authority;
DROP TABLE book_apply;
DROP TABLE reservation;
DROP TABLE history;
DROP TABLE loan;
DROP TABLE bookList;
DROP TABLE notice;
DROP TABLE score;
DROP TABLE book;
DROP TABLE users;

CREATE TABLE users (
	user_no			INT 			NOT NULL PRIMARY KEY AUTO_INCREMENT,
	email			VARCHAR(30)		NOT NULL,
	password		VARCHAR(255)	NOT NULL,
	name			VARCHAR(20)		NOT NULL,
	phone_num		VARCHAR(20)		NOT NULL,
	attachment      VARCHAR(255),
	stop_date		DATE,
	UNIQUE (email)
); 

CREATE TABLE book (
	isbn_no			INT				NOT NULL PRIMARY KEY AUTO_INCREMENT,
	isbn			VARCHAR(20)		NOT NULL,
	title			VARCHAR(255)	NOT NULL,
	author			VARCHAR(255)	NOT NULL,
	publisher		VARCHAR(255)	NOT NULL,
	attachment		VARCHAR(255),
	group_name		VARCHAR(10)		NOT NULL,
	UNIQUE (isbn)
);

CREATE TABLE bookList (
	book_no			INT 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
	wearing_day		DATE 		NOT NULL,
	publication_day DATE 		NOT NULL,
	isbn			VARCHAR(20) NOT NULL,
	whether			BOOLEAN 	NOT NULL,
	FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE loan (
	loan_no				INT 	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	reserv_loan_date	DATE		NULL,
	user_no 			INT 	NOT NULL,
	book_no				INT 	NOT NULL,
	loan_date 			DATE 		NULL,
	deadline 			DATE 	NOT NULL,
	extension			BOOLEAN	NOT NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no),
	FOREIGN KEY (book_no) REFERENCES bookList(book_no)
);

CREATE TABLE history (
	his_no				INT 	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	book_no 			INT		NOT NULL,
	user_no				INT		NOT NULL,
	loan_date 			DATE 	NOT NULL,
	return_date 		DATE 		NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no),
	FOREIGN KEY (book_no) REFERENCES bookList(book_no)
);

CREATE TABLE reservation (
	reser_no		INT 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
	isbn 			VARCHAR(20) NOT NULL,
	user_no			INT 		NOT NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no),
	FOREIGN KEY (isbn) REFERENCES book(isbn)
);

# 사용자 권한 정의한 테이블
CREATE TABLE authority (
	id	INT NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

# 사용자 번호와 사용자 권한 아이디값을 연결하는 테이블
CREATE TABLE users_authority (
	user_no INT 		NOT NULL,
	authority_id INT 	NOT NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no),
	FOREIGN KEY (authority_id) REFERENCES authority(id)
);

CREATE TABLE score (
	score_no	INT 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
	book_score	INT 		NOT NULL,
	user_no		INT 		NOT NULL,
	isbn 		VARCHAR(20) NOT NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no),
	FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE notice (
	notice_no	INT				NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title 		VARCHAR(30)		NOT NULL,
	description	VARCHAR(255) 	NOT NULL,
	reg_date	DATE 			NOT NULL,
	user_no 	INT 			NOT NULL,	
	FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE book_apply (
	apply_no	INT				NOT NULL PRIMARY KEY AUTO_INCREMENT,
	title		VARCHAR(255) 	NOT NULL,
	author		VARCHAR(255)	NOT NULL,
	publisher	VARCHAR(255)	NOT NULL,
	user_no		INT				NOT NULL,
	apply_date	DATE			NOT NULL,
	isbn		VARCHAR(20)		NOT NULL,
	warehousing tinyint(1) 		DEFAULT 0,		
	FOREIGN KEY (user_no) REFERENCES users(user_no)
);
#0 false / 1 true

CREATE TABLE readingRoom (
	readingRoom_no		INT 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_no				INT 		NULL,			
	room_no 			INT			NOT NULL,
	seat_no				INT 		NOT NULL,
	reservation			BOOLEAN		NOT NULL,
	time				DATETIME		NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE volunteer (
	volun_no	INT				NOT NULL 	PRIMARY KEY AUTO_INCREMENT,
	title 		VARCHAR(30)		NOT NULL,
	description	VARCHAR(255) 	NOT NULL,
	reg_date	DATE 			NOT NULL,
	user_no 	INT 			NOT NULL,	
	password	VARCHAR(20)		NOT NULL,
	apply_date	DATE			NOT NULL,
	FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE audioList (
	audio_no		INT 		NOT NULL PRIMARY KEY AUTO_INCREMENT,
	wearing_day		DATE 		NOT NULL,
	publication_day DATE 		NOT NULL,
	isbn			VARCHAR(20) NOT NULL,
	file			VARCHAR(255) NOT NULL,
	FOREIGN KEY (isbn) REFERENCES book(isbn)
);

#권한 입력
INSERT INTO authority (id, name)
	VALUES (10, 'ADMIN');

INSERT INTO authority (id, name)
	VALUES (20, 'USERS');
	
INSERT INTO authority (id, name)
	VALUES (30, 'BREAKUSERS');
	
INSERT INTO authority (id, name)
	VALUES (40, 'UNUSERS');

# 사용자 입력 (비밀번호는 1234)
INSERT INTO users (email, password, name, phone_num, stop_date)
	VALUES ('admin@koitt.com', '$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', 
	'관리자', '01000000001', null);
	
INSERT INTO users (email, password, name, phone_num, stop_date)
	VALUES ('user1@koitt.com', '$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', 
	'유저1', '01000000002', null);
	
INSERT INTO users (email, password, name, phone_num, stop_date)
	VALUES ('user2@koitt.com', '$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', 
	'유저2', '01000000003', null);
	
INSERT INTO users (email, password, name, phone_num, stop_date)
	VALUES ('user3@koitt.com', '$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', 
	'유저3', '01000000004', null);
	
#INSERT INTO loan (reserv_loan_date, user_no, book_no, loan_date, deadline)
#	VALUES ();
	
#INSERT INTO history (book_no, user_no, loan_date, return_date)
#	VALUES ();


# 사용자에게 권한 부여
INSERT INTO users_authority VALUES (1, 10);	#관리자 사용자에게 관리자 권한 부여
INSERT INTO users_authority VALUES (2, 20);	#유저1 사용자에게 사용자 권한 부여
INSERT INTO users_authority VALUES (3, 20);	#유저2 사용자에게 사용자 권한 부여
INSERT INTO users_authority VALUES (4, 20);	#유저3 사용자에게 사용자 권한 부여

# 공지사항 입력
INSERT INTO notice (title, description, reg_date, user_no) 
VALUES ('공지사항-1', '내용-1', CURDATE(), 1);

# 희망도서신청 입력
INSERT book_apply (title, author, publisher, user_no, isbn, apply_date)
VALUES ('제목-미입고', '저자-1', '출판사-1', 2, 13, CURDATE());
INSERT book_apply (title, author, publisher, user_no, apply_date, isbn, warehousing)
VALUES ('제목-입고완료', '저자-2', '출판사-2', 3, CURDATE(), 21, 1);


#자원봉사 신청 입력
INSERT volunteer (title, description, reg_date, user_no, password, apply_date)
VALUES ('봉사자-1', '내용-1', CURDATE(), 1, '0000', '2018-06-01');
INSERT volunteer (title, description, reg_date, user_no, password, apply_date)
VALUES ('봉사자-2', '내용-2', CURDATE(), 2, '0000', '2018-05-31');

SELECT * FROM users;
SELECT * FROM users_authority;
SELECT * FROM authority;
SELECT * FROM book;
SELECT * FROM bookList;
SELECT * FROM score;
SELECT * FROM reservation;
SELECT * FROM history;
SELECT * FROM loan;
SELECT * FROM Notice;
SELECT * FROM book_apply;
SELECT * FROM readingRoom;
SELECT * FROM volunteer;
SELECT * FROM audioList;