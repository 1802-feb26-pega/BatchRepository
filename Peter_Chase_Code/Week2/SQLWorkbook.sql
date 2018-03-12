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
      
      
--------------------------------------------------------------
--------------- 3.1 System Defined Functionss ----------------
--------------------------------------------------------------
-- Create a function that returns the current time.
CREATE OR REPLACE FUNCTION get_date
RETURN DATE
IS
    l_sysdate DATE;
BEGIN
    SELECT SYSDATE
        INTO l_sysdate
        FROM dual;
        
    RETURN l_sysdate;
END;
/
 -- Calling
SELECT get_date() FROM dual;

-- create a function that returns the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION type_length(media_id IN NUMBER, media_name IN VARCHAR2)
RETURN NUMBER
IS
BEGIN
    RETURN LENGTH(TRIM(media_name)) + LENGTH(media_Id);
END;
/
-- Call
SELECT type_length(mediatypeid, name) FROM mediatype WHERE mediatypeid = 1;
--------------------------------------------------------------
----------- 3.2 System Defined Aggregate Functions -----------
--------------------------------------------------------------
-- Create a function that returns the average total of all invoices
-- Definition Manual
CREATE OR REPLACE FUNCTION avg_total_invoice_manual
RETURN NUMBER
IS
total_avg NUMBER;
invoice_total NUMBER;
res NUMBER;
BEGIN
    total_avg := 0;
    invoice_total := 0;
    res := 1;
    FOR x IN (SELECT total FROM invoice) LOOP
        total_avg := total_avg + x.total;
        invoice_total := invoice_total + 1;
    END LOOP;
    res := total_avg / invoice_total;
    RETURN res;
END;
/
SELECT avg_total_invoice_manual FROM dual;
-- Definition Smart
CREATE OR REPLACE FUNCTION avg_total_invoice_smart
RETURN NUMBER
IS
total_avg NUMBER;
BEGIN
    SELECT AVG(total) INTO total_avg FROM invoice;
    RETURN total_avg;
END;
/
SELECT avg_total_invoice_smart FROM dual;
    
-- Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION max_track_price
RETURN NUMBER
IS
max_price NUMBER;
BEGIN
    SELECT MAX(unitprice) INTO max_price FROM TRACK;
    RETURN max_price;
END;
/
SELECT max_track_price FROM dual;
--------------------------------------------------------------
------------- 3.3 User Defined Scalar Functions --------------
--------------------------------------------------------------
-- Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION avg_invoiceline_price
RETURN NUMBER
IS
avg_price NUMBER;
BEGIN
    SELECT AVG(unitprice) INTO avg_price FROM invoiceline;
    RETURN avg_price;
END;
/
SELECT avg_invoiceline_price FROM dual;
--------------------------------------------------------------
---------- 3.4 User Defined Table Valued Functions -----------
--------------------------------------------------------------
-- Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION employees_born_after_1968
RETURN NUMBER
IS
young_employees NUMBER;
BEGIN
    SELECT COUNT(birthdate) INTO young_employees FROM employee
        WHERE birthdate > DATE '1960-01-01';
    RETURN young_employees;
END;
/
SELECT employees_born_after_1968 FROM dual;


--------------------------------------------------------------
----------------- 4.1 Basic Stored Procedure -----------------
--------------------------------------------------------------
-- Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE employee_names(curs OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN curs FOR SELECT firstname, lastname FROM employee;
END;
/
-- In Use
VAR curs REFCURSOR;
EXEC employee_names(:curs);
PRINT curs;
--
--------------------------------------------------------------
----------- 4.2 Stored Procedure Input Parameters ------------
--------------------------------------------------------------
-- Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE update_employee(p_id IN employee.employeeid%TYPE,
                                            p_address IN employee.address%TYPE,
                                            p_city IN employee.city%TYPE,
                                            p_state IN employee.state%TYPE,
                                            p_country IN employee.country%TYPE,
                                            p_pcode IN employee.postalcode%TYPE,
                                            p_phone IN employee.phone%TYPE,
                                            p_fax IN employee.fax%TYPE,
                                            p_email IN employee.email%TYPE)
IS
    l_employee employee%ROWTYPE;
BEGIN
    l_employee.address := p_address;
    l_employee.city := p_city;
    l_employee.state := p_state;
    l_employee.country := p_country;
    l_employee.postalcode := p_pcode;
    l_employee.phone := p_phone;
    l_employee.fax := p_fax;
    l_employee.email := p_email;
    
    UPDATE employee
        SET ROW = l_employee
    WHERE employeeid = p_id;
END;
/

-- Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE get_managers(emp_id IN employee.employeeid%TYPE, curs OUT SYS_REFCURSO)
IS
    l_employee employee%ROWTYPE;
BEGIN
    SELECT * INTO l_employee FROM employee WHERE employeeid = emp_id;
    
END;
/
-- In Use
VAR curs REFCURSOR;
EXEC employee_names(:curs);
PRINT curs;
--

--------------------------------------------------------------
------------------- 5.0 Transactions 2 -----------------------
--------------------------------------------------------------
-- Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE delete_invoice(p_invoiceId IN NUMBER)
IS
BEGIN
    DELETE FROM invoice
    WHERE invoiceid = p_invoiceid;
    commit;
END;
/

Execute delete_invoice(5);
SELECT invoiceid FROM invoice;

-- Create a transaction nested within a stored procedure that inserts a new record in the Customer table

--------------------------------------------------------------
------------------------ 7.1 INNER ---------------------------
--------------------------------------------------------------
-- Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT firstname, lastname, invoiceid FROM customer
    INNER JOIN invoice ON customer.customerid = invoice.customerid;

--------------------------------------------------------------
------------------------ 7.2 OUTER ---------------------------
--------------------------------------------------------------
-- Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total FROM invoice
   FULL OUTER JOIN customer ON invoice.customerid = customer.customerid;
   

--------------------------------------------------------------
------------------------ 7.3 RIGHT ---------------------------
--------------------------------------------------------------
-- Create a right join that joins album and artist specifying artist name and title.
SELECT artist.name, album.title FROM artist
    RIGHT JOIN album ON album.artistid = artist.artistid;
    
--------------------------------------------------------------
------------------------ 7.4 RIGHT ---------------------------
--------------------------------------------------------------
-- Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * FROM album
    CROSS JOIN artist
    ORDER BY artist.name ASC;

--------------------------------------------------------------
------------------------ 7.5 SELF ----------------------------
--------------------------------------------------------------
-- Perform a self-join on the employee table, joining on the reportsto column.
SELECT * FROM employee A
LEFT JOIN employee B ON A.reportsto = B.employeeid;