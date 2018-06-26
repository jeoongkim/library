INSERT INTO users (email, password, name, phone_num)
	VALUES ('admin@koitt.com', '$2a$10$AF6PNoVqwj56NmOCuWz.1u8YO/km7XCA77ztKxbqIF3FVyQI1iYny', '관리자', 01000000001);
SELECT *FROM users;

SELECT u.user_no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname"
	FROM users u,
		(SELECT users_authority.user_no, authority.id, authority.name
		FROM users_authority, authority
		WHERE users_authority.authority_id = authority.id) ua
	WHERE u.user_no = ua.user_no AND u.user_no = 3;
	
	
SELECT u.user_no, u.email, u.password, u.name, u.attachment, ua.id, ua.name as "aname"
	FROM users u,
		(SELECT users_authority.user_no, authority.id, authority.name
		FROM users_authority, authority
		WHERE users_authority.authority_id = authority.id) ua
	WHERE u.user_no = ua.user_no AND u.email = "admin@koitt.com";
	
SELECT LAST_INSERT_ID();

SELECT COUNT(*) cnt FROM users;

SELECT * FROM authority WHERE id = 10;

SELECT *FROM loan;
INSERT INTO loan(reserv_loan_date, user_no, book_no,loan_date,deadline)
VALUES(null,1,1,STR_TO_DATE('2018-03-16', '%Y-%m-%d'),STR_TO_DATE('2018-03-16', '%Y-%m-%d'));

INSERT INTO loan(reserv_loan_date, user_no, book_no,loan_date,deadline)
VALUES(null,5,1,STR_TO_DATE('2018-03-16', '%Y-%m-%d'),STR_TO_DATE('2018-03-16', '%Y-%m-%d'));


INSERT INTO loan(reserv_loan_date, user_no, book_no,loan_date,deadline)
VALUES(null,1,1,STR_TO_DATE(CURDATE(), '%Y-%m-%d'),STR_TO_DATE(CURDATE()+15, '%Y-%m-%d'));

INSERT INTO reservation(user_no, isbn)
VALUES(1,1);

INSERT INTO reservation(user_no, isbn)
VALUES(1,2);

INSERT INTO reservation(user_no, isbn)
VALUES(2,3);

INSERT INTO reservation(user_no, isbn)
VALUES(2,4);

DELETE FROM loan WHERE loan_no =3;

SELECT loan_no FROM loan WHERE book_no=1;

INSERT INTO reservation(isbn,user_no)
VALUES(1,1);
SELECT *FROM reservation;
SELECT * FROM reservation WHERE isbn = 1 order by reser_no asc limit 1;

SELECT COUNT(*) FROM BookList WHERE isbn = 1;
SELECT COUNT(*) FROM reservation WHERE isbn = 1;

SELECT COUNT(if(whether=true, whether, null)) FROM BookList WHERE isbn = 1;
UPDATE history SET return_date = CURDATE()
  		WHERE book_no = 1 and user_no = 3
  		order by his_no desc limit 1;

  		SELECT * FROM book WHERE title LIKE '%어서와%' OR author LIKE '%어서와%' OR publisher LIKE '%어서와%';
  		
SELECT b.title b.attachment bl.isbn
	FROM book b, book_list bl, history h
	WHERE b.isbn = bl.isbn AND h.loan_date=CURDATE();
	
SELECT l.loan_no, u.name, b.title, l.loan_date, l.deadline FROM loan l, users u, book b, bookList bl
	WHERE l.user_no = u.user_no AND l.book_no = bl.book_no AND bl.isbn = b.isbn;

SELECT l.loan_no, u.name, bo.title, l.loan_date, l.deadline
		FROM loan l, users u, 
			(SELECT b.title ,bl.book_no
			FROM book b, bookList bl
			WHERE bl.isbn = b.isbn )bo
		WHERE l.user_no = u.user_no AND l.book_no = bo.book_no;
	
SELECT r.reser_no, u.name, b.title
		FROM reservation r, users u, book b
		WHERE r.user_no = u.user_no AND r.isbn = b.isbn;
		
SELECT h.his_no, h.book_no, h.loan_date, h.return_date, bo.title 
		FROM history h, (SELECT b.title ,bl.book_no
			FROM book b, bookList bl
			WHERE bl.isbn = b.isbn )bo 
			WHERE h.book_no = bo.book_no AND user_no = 2
		
SELECT COUNT(if(whether=true, whether, null)) FROM BookList WHERE isbn = 3;

select * from book;

INSERT INTO history(book_no, user_no, loan_date, return_date)
VALUES(1, 2, STR_TO_DATE('2018-04-16', '%Y-%m-%d'),STR_TO_DATE('2018-04-16', '%Y-%m-%d'));
INSERT INTO history(book_no, user_no, loan_date, return_date)
VALUES(1, 2, STR_TO_DATE('2018-04-26', '%Y-%m-%d'),STR_TO_DATE('2018-04-26', '%Y-%m-%d'));
INSERT INTO history(book_no, user_no, loan_date, return_date)
VALUES(1, 2, STR_TO_DATE('2018-05-14', '%Y-%m-%d'),STR_TO_DATE('2018-04-16', '%Y-%m-%d'));
INSERT INTO history(book_no, user_no, loan_date, return_date)
VALUES(1, 2, STR_TO_DATE('2018-04-16', '%Y-%m-%d'),STR_TO_DATE('2018-04-16', '%Y-%m-%d'));

SELECT h.book_no, bo.isbn
FROM history h, (SELECT b.isbn ,bl.book_no
			FROM book b, bookList bl
			WHERE bl.isbn = b.isbn)bo 
WHERE h.book_no = bo.book_no 
AND loan_date LIKE DATE_FORMAT((NOW() - INTERVAL 1 MONTH), '%Y-%m-%')
order by isbn desc limit 10;

SELECT h.book_no, bo.title, bo.author, bo.publisher, bo.attachment, bo.isbn , COUNT(isbn)
FROM history h, 
	(SELECT b.isbn, b.title, b.author, b.publisher, b.attachment, bl.book_no
		FROM book b, bookList bl
		WHERE bl.isbn = b.isbn)bo
WHERE h.book_no = bo.book_no 
AND loan_date LIKE DATE_FORMAT((NOW() - INTERVAL 1 MONTH), '%Y-%m-%')
group by isbn
order by COUNT(isbn) desc limit 10;

select name, count(name) from testbl group by name

SELECT n.notice_no, n.title, n.description, n.reg_date, n.user_no, u.name 
FROM notice n, users u
WHERE n.user_no = u.user_no AND n.user_no = 1;

SELECT deadline, user_no
FROM loan
WHERE reserv_loan_date IS null 
AND deadline LIKE DATE_FORMAT((NOW() + INTERVAL 1 DAY), '%Y-%m-%d');

INSERT INTO loan (reserv_loan_date, user_no, book_no, loan_date, deadline, extension)
VALUES ('2018-05-24', 1, 1, null, '2018-06-07', false);
INSERT INTO loan (reserv_loan_date, user_no, book_no, loan_date, deadline, extension)
VALUES ('2018-05-24', 1, 2, null, '2018-06-07', false);

SELECT b.apply_no, b.title, b.author, b.publisher, b.apply_date, b.user_no, u.name, b.warehousing 
FROM book_apply b, users u
WHERE b.user_no = u.user_no
AND b.warehousing = false
ORDER BY apply_no DESC;

SELECT COUNT(user_no) FROM book_apply
WHERE user_no = 5
AND apply_date LIKE DATE_FORMAT((NOW()), '%Y-%m-%');

UPDATE users_authority SET authority_id = 40
WHERE user_no = #{userNo}

select * from users_authority;

SELECT l.loan_no, u.name, bo.title, l.loan_date, l.deadline, l.book_no
FROM loan l, users u, 
	(SELECT b.title ,bl.book_no
	FROM book b, bookList bl
	WHERE bl.isbn = b.isbn )bo
WHERE l.user_no = u.user_no AND l.book_no = bo.book_no
and l.user_no = 1 and l.reserv_loan_date is null;
		
SELECT l.*, r.* FROM loan l, reservation r
WHERE l.user_no = r.user_no
AND l.loan_date IS null
AND l.user_no = 1;

update loan set deadline = DATE_FORMAT((NOW() - INTERVAL 1 DAY), '%Y-%m-%d')
WHERE book_no = 1;
update loan set deadline = DATE_FORMAT((NOW()), '%Y-%m-%d')
WHERE book_no = 2;
