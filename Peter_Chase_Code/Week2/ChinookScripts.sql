--------------------------------
---------- 2.1 SELECT ----------
--------------------------------
-- Select all records from employee table
SELECT * FROM employee;
-- Select all records from the employee table where last name is King
SELECT * FROM employee
    WHERE lastname = 'King';
-- Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM employee
    WHERE firstname = 'Andrew'
      AND reportsto = NULL;
--------------------------------
--------- 2.2 ORDER BY ---------
--------------------------------
-- Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM album
    ORDER BY title desc;
-- Select first name from Customer and sort result set in ascending order by city
SELECT firstname FROM customer
    ORDER BY city ASC;
--------------------------------
------- 2.3 INSERT INTO --------
--------------------------------
-- Insert two new records into Genre table
INSERT INTO genre VALUES (26, 'Meme');
INSERT INTO genre VALUES (27, 'Bad');
-- Insert two new records into Employee table
INSERT INTO employee VALUES (
    9,
    'Chase',
    'Peter',
    'IT Staff',
    6,
    TO_DATE('1995-12-11 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
    TO_DATE('2018-03-06 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
    '15160 20th Rd',
    'New York',
    'NY',
    'United States',
    '11357',
    '+1 (413) 652-5514',
    '+1 (413) 652-5514',
    'pchase95@protonmail.com'
);
INSERT INTO employee VALUES (
    10,
    'Chase',
    'Rodney',
    'Sales Support Agent',
    2,
    TO_DATE('1995-12-11 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
    TO_DATE('2005-10-04 00:00:00', 'yyyy-mm-dd hh24:mi:ss'),
    '60 Whiteadder Way',
    'London',
    'England',
    'United Kingdom',
    'E14 9UR',
    '+0 (773) 623-0058',
    '+0 (773) 623-0058',
    'rpeterchase@hotmail.com'
);
-- Insert two new records into Customer table
INSERT INTO customer VALUES (
    60,
    'John',
    'Doe',
    NULL,
    '420 Memory Lane',
    'Copenhagen',
    NULL,
    'Denmark',
    '49210',
    '+45 392 1049 3728',
    NULL,
    'john.doe@gmail.com',
    4
);
INSERT INTO customer VALUES (
    61,
    'Peter',
    'Chase',
    'Revature',
    '2284 307 Astoria Circle',
    'Reston',
    'VA',
    'United States',
    '20170',
    '+1 (413) 652-5514',
    NULL,
    'pc5162@mcla.edu',
    5
);
--------------------------------
---------- 2.4 UPDATE ----------
--------------------------------
-- Update Aaron Mitchell in Customer table to Robert Walterss
-- 32
UPDATE customer
    SET firstname = 'Robert',
        lastname = 'Walters'
    WHERE firstname = 'Aaron'
      AND lastname = 'Mitchell';
-- Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE artist
    SET name = 'CCR'
    WHERE name = 'Creedence Clearwater Revival';
--------------------------------
----------- 2.5 LIKE -----------
--------------------------------
-- Select all invoices with a billing address like “T%”
SELECT * FROM invoice
    WHERE billingaddress LIKE 'T%';
--------------------------------
--------- 2.6 BETWEEN ----------
--------------------------------
-- Select all invoices that have a total between 15 and 50
SELECT * FROM invoice
    WHERE total BETWEEN 15 AND 50;
-- Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee
    WHERE hiredate BETWEEN
        DATE '2003-06-01' AND
        DATE '2004-03-01';
--------------------------------
---------- 2.7 DELETE ----------
--------------------------------
-- Delete a record in Customer table where the name is Robert Walter
-- (There may be constraints that rely on this, find out how to resolve them)
ALTER TABLE invoice DROP CONSTRAINT FK_INVOICECUSTOMERID;
ALTER TABLE invoice DROP CONSTRAINT CUSTOMERID;
ALTER TABLE invoiceline DROP CONSTRAINT FK_INVOICELINEINVOICEID;

ALTER TABLE invoice
    ADD CONSTRAINT customerid
    FOREIGN KEY (customerid) REFERENCES customer(customerid)
    ON DELETE CASCADE;
    
DELETE FROM customer
    WHERE firstname = 'Robert'
      AND lastname = 'Walters';