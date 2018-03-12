SELECT *
FROM employee;

SELECT *
FROM employee
WHERE lastname = 'King';

SELECT *
FROM employee 
WHERE firstname = 'Andrew'
    AND 
    reportsto is null;

SELECT *
FROM album
ORDER BY title DESC;

SELECT firstname, city
FROM customer
ORDER BY city ASC;

INSERT INTO GENRE
(GENREID,NAME)
VALUES
(26, 'joshgenre');

INSERT INTO GENRE
(GENREID,NAME)
VALUES
(27, 'mygenre');

INSERT INTO employee
(EMPLOYEEID, FIRSTNAME, LASTNAME)
VALUES
(9, 'Joe', 'Smith');

INSERT INTO employee
(EMPLOYEEID, FIRSTNAME, LASTNAME)
VALUES
(10, 'Josh', 'Smith');

INSERT INTO customer
(FIRSTNAME, LASTNAME, EMAIL, CUSTOMERID)
VALUES
('Joe', 'Smith', 'fakeemail123@yahoo.com', 60);

INSERT INTO customer
(FIRSTNAME, LASTNAME, EMAIL, CUSTOMERID)
VALUES
('Josh', 'Smith', 'fakemail123@yahoo.com', 61);

UPDATE CUSTOMER 
SET firstname ='Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

UPDATE ARTIST
SET name = 'CCR'
where name = 'Creedence Clearwater Revival';

SELECT billingaddress
FROM invoice
WHERE billingaddress LIKE 'T%';

SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

SELECT *
FROM employee
WHERE hiredate BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

DELETE CASCADE
FROM customer
WHERE firstname ='Robert' AND lastname = 'Walter';

CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;
BEGIN
    SELECT systimestamp
    INTO l_systimestamp
    FROM dual;
    RETURN l_systimestamp;
END;
/
