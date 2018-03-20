-- Part One - Existing Database
-- 1.0
-- Ran Chinook sql file seperately

-- 2.1
-- Select all records from Employee table
SELECT *
FROM employee;

-- Select all records where last name is King
SELECT *
FROM employee
WHERE lastname = 'King';

-- Select all records where first name is Andrew and reportsto is null
SELECT *
FROM employee
WHERE firstname = 'Andrew' AND reportsto IS null;

-- 2.2
-- Select all albums in Album table and sort result set in descending order
SELECT *
FROM album
ORDER BY title DESC;

-- Select first name from Customer and sort result set in ascending order by city
SELECT firstname
FROM customer
ORDER BY city;

-- 2.3
-- Insert two new records into Genre table
INSERT ALL
INTO genre (genreid, name) 
    VALUES (26, 'Christian Contemporary')
INTO genre (genreid, name)
    VALUES (27, 'Christian Rock')
SELECT * FROM dual;

-- Insert two new records into Employee table
INSERT ALL
INTO employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, 
Phone, Fax, Email) 
    VALUES (9, 'Calvin', 'Lance', 'Security Guard', 1, DATE '1992-04-14', Date '2006-06-01', '4538 Deyo Ave', 'Brookfield', 
    'IL', 'USA', '60513', '1(708)485-9593', null, 'lance@chinookcorp.com')
INTO employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, 
Phone, Fax, Email) 
    VALUES (10, 'Calvin', 'Alanna', 'Security Guard', 1, DATE '1994-05-10', Date '2006-06-01', '4538 Deyo Ave', 'Brookfield', 
    'IL', 'USA', '60513', '1(708)485-9593', null, 'alanna@chinookcorp.com')
SELECT * FROM dual;

-- Insert two records into Customer table
INSERT ALL
INTO customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
    VALUES (60, 'Greg', 'Parker', 'Strategic Response Unit', '4538 Deyo Ave', 'Brookfield', 'IL', 'USA', '60513', 
    '1(708)485-9593', null, 'parker@sru.gov', 1)
INTO customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
    VALUES (61, 'Kevin', 'Wordsworth', 'Strategic Response Unit', '6452 Maple Ave', 'Brookfield', 'IL', 'USA', '60513', 
    '1(708)485-8267', null, 'wordsworth@sru.gov', 1)
SELECT * FROM dual;

-- 2.4
-- Update Aaron Mitchell in Customer to Robert Walter
UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

-- Update Creedence Clearwater Revival in Artist to CCR
UPDATE artist
Set name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

-- 2.5
-- Select all invoices with a billing address like T%
SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';

-- 2.6
-- Select all invoices that have a total between 15 and 50
SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

-- Select all employees hired between June 1st and March 1st of 2004
SELECT *
FROM employee
WHERE hiredate BETWEEN DATE '2003-06-01' AND DATE '2004-03-01';

-- 2.7
-- Overall task: Delete a record in Customer where the name is Robert Walter
-- Altering invoice table
ALTER TABLE invoice
    DROP CONSTRAINT fk_invoicecustomerid;
ALTER TABLE invoice
    Add CONSTRAINT fk_invoicecustomerid_cascade
    FOREIGN KEY (customerid) REFERENCES chinook.customer(customerid) ON DELETE CASCADE;

-- Altering invoiceline table
ALTER TABLE invoiceline
    DROP CONSTRAINT fk_invoicelineinvoiceid;
ALTER TABLE invoiceline
    Add CONSTRAINT fk_invoicelineinvoiceid_cas
    FOREIGN KEY (invoiceid) REFERENCES chinook.invoice(invoiceid) ON DELETE CASCADE;

-- Deleting Robert Walter
DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

-- 3.1
-- Function to return the current time
CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;

BEGIN
    SELECT systimestamp
    INTO l_systimestamp
    FROM dual;
    RETURN l_systimestamp;
END;
/
-- Run get_current_time()
SELECT get_current_time() FROM dual;

-- Function to return the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION mediatype_length (m_id IN NUMBER)
RETURN NUMBER IS m_length NUMBER;

BEGIN
    SELECT LENGTH(name)
    INTO m_length
    FROM mediatype
    WHERE m_id = mediatype.mediatypeid;
    RETURN m_length;
END;
/
-- Run mediatype_length()
SELECT mediatype_length(4) FROM dual;

-- 3.2
-- Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION avg_invoice_items
RETURN NUMBER IS avg_items NUMBER;

BEGIN
    SELECT ROUND(AVG(total), 2)
    INTO avg_items
    FROM invoice;
    RETURN avg_items;
END;
/
-- Run avg_invoice_items()
SELECT avg_invoice_items() FROM dual;

-- Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION expen_track
RETURN NUMBER IS costly NUMBER;

BEGIN
    SELECT MAX(unitprice)
    INTO costly
    FROM track;
    RETURN costly;
END;
/
-- Run expen_track()
SELECT expen_track() FROM dual;

-- 3.3
-- Create a function that returns the average price of invoiceline items in the invoice table
CREATE OR REPLACE FUNCTION avg_invoiceline_item
RETURN NUMBER IS avg_item NUMBER;

BEGIN
    SELECT ROUND(AVG(unitprice), 2)
    INTO avg_item
    FROM invoiceline;
    RETURN avg_item;
END;
/
-- Run avg_invoiceline_item()
SELECT avg_invoiceline_item() FROM dual;

-- 3.4
-- Create a function that returns all employees who are born after 1968
-- Use REFCURSOR
CREATE OR REPLACE FUNCTION after_sixty_eight
RETURN SYS_REFCURSOR IS sixty_eight SYS_REFCURSOR;

BEGIN
    OPEN sixty_eight FOR
    SELECT *
    --INTO sixty_eight
    FROM employee
    WHERE employee.birthdate < DATE '1968-12-31';
    RETURN sixty_eight;
END;
/
-- Run after_sixty_eight()
--SELECT after_sixty_eight() FROM dual;
VARIABLE cur REFCURSOR;
BEGIN
    :cur := after_sixty_eight();
END;
/
PRINT cur;

-- Sample Procedure (Cursor starts above rows [zero based])
CREATE OR REPLACE PROCEDURE get_all_artists(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT * FROM artist;
END;
/
-- Run Sample procedure
VARIABLE cursorParam REFCURSOR;
EXECUTE get_all_artists(:cursorParam);
PRINT cursorParam;

-- 4.1
-- Create a stored procedure that selects the first and last names of all the employees
CREATE OR REPLACE PROCEDURE first_last_name(cursorCust OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorCust FOR
    SELECT firstname, lastname
    FROM employee;
END;
/

-- Run first_last_name() procedure
VARIABLE cursorCust REFCURSOR;
EXECUTE first_last_name(:cursorCust);
PRINT cursorCust;

-- 4.2
-- Create a stored procedure that updates the personal information of an employee
CREATE OR REPLACE PROCEDURE change_employee_phone(e_id IN NUMBER, new_phone IN VARCHAR2)
IS
BEGIN
    UPDATE employee
    SET phone = new_phone
    WHERE e_id = employeeid;
END;
/

-- Run change_employee_phone (for this example, phone before is: 1(708)485-9593
CALL change_employee_phone(9, '+1 (708) 485-9593');
-- Showing output
SELECT employeeid, firstname, lastname, phone
FROM employee
WHERE employeeid = 9;

-- Create a stored procedure that returns the managers of an employee
CREATE OR REPLACE PROCEDURE employee_managers (
e_id IN NUMBER,
managers OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN managers FOR
    SELECT *
    FROM employee
    WHERE employee.employeeid = 
    (
        SELECT reportsto
        FROM employee
        WHERE e_id = employeeid
    );
    --RETURN m_id;
END;
/

-- Run employee_managers() procedure
VARIABLE managers REFCURSOR;
EXECUTE employee_managers(9, :managers);
PRINT managers;

-- 4.3
-- Create a stored procedure that returns the name and company of a customer *Needs cursor, not individual values
CREATE OR REPLACE PROCEDURE customer_name_company(
c_id IN customer.customerid%TYPE,
f_customer OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN f_customer FOR
    SELECT firstname, lastname, company
    FROM customer
    WHERE c_id = customerid;
END;
/

-- Run employee_managers() procedure
VARIABLE f_customer REFCURSOR;
EXECUTE customer_name_company(60, :f_customer);
PRINT f_customer;

-- 5.0 Transactions Need commit
-- Create a transaction that, given an invoiceID, will delete that invoice
CREATE OR REPLACE PROCEDURE remove_invoice(
i_id IN invoice.invoiceid%TYPE)
IS
BEGIN
    DELETE FROM invoice
    WHERE invoiceid = i_id;
    COMMIT;
END;
/
-- Run remove_invoice() transaction
CALL remove_invoice(215);

-- Savepoint...worked when testing delete, did NOT work twice in a row
-- unless recreated.
-- Also did not work after COMMIT added to transaction
SAVEPOINT before_delete;
ROLLBACK TO SAVEPOINT before_delete;

-- Restoring invoice for testing
INSERT INTO Invoice (InvoiceId, CustomerId, InvoiceDate, BillingAddress, 
BillingCity, BillingCountry, BillingPostalCode, Total) 
VALUES (215, 42, TO_DATE('2011-7-30 00:00:00','yyyy-mm-dd hh24:mi:ss'), 
'9, Place Louis Barthou', 'Bordeaux', 'France', '33000', 13.86);

-- Create a transaction within a store procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE add_customer(
c_fname IN customer.firstname%TYPE,
c_lname IN customer.lastname%TYPE,
c_comp IN customer.company%TYPE,
c_add IN customer.address%TYPE,
c_city IN customer.city%TYPE,
c_state IN customer.state%TYPE,
c_country IN customer.country%TYPE,
c_postal IN customer.postalcode%TYPE,
c_phone IN customer.phone%TYPE,
c_email IN customer.email%TYPE)
IS
BEGIN
    INSERT INTO customer (CustomerId, FirstName, LastName, Company, Address, City, 
    State, Country, PostalCode, Phone, Fax, Email, SupportRepId)
    VALUES((SELECT MAX(customerid)+1 FROM customer), c_fname, c_lname, c_comp,
        c_add, c_city, c_state, c_country, c_postal, c_phone, null, c_email, 1);
    COMMIT;
END;
/
-- Run add_customer()transaction
CALL add_customer('Spike', 'Scarlatti', 'Strategic Response Unit', '6879 Elm St', 'Brookfield', 'IL', 'USA', '60513', 
    '+1 (708) 485-5555', 'scarlatti@sru.gov');

-- Checking customer table
SELECT *
FROM customer
WHERE customerid > 59;

-- Removing new customer for testing purposes
DELETE FROM customer WHERE customerid = 62;

-- 6.1
-- Create an after insert trigger on the employee fired after a new record is inserted into the table
CREATE OR REPLACE TRIGGER new_employee
AFTER INSERT ON employee
BEGIN
    --DBMS_OUTPUT.ENABLE;
    DBMS_OUTPUT.PUT_LINE('New Employee Hired!');
    --DBMS_OUTPUT.GET_LINE(line OUT VARCHAR2,status out interger);
    --SELECT firstname, lastname
    --FROM employee;
END;
/

-- Test trigger
INSERT INTO employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, 
Phone, Fax, Email) 
    VALUES ((SELECT MAX(EmployeeId)+1 FROM employee), 'Parker', 'Dean', 'Security Guard', 1, DATE '1991-11-05', Date '2008-06-01', '4538 Deyo Ave', 'Brookfield', 
    'IL', 'USA', '60513', '+1 (708) 485-9593', null, 'dean@chinookcorp.com');

-- Remove employee for further testing
SAVEPOINT before_new_employee;
ROLLBACK TO SAVEPOINT before_new_employee;

-- Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER new_album
AFTER INSERT ON album
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('New Album!');
END;
/

-- Test trigger
INSERT INTO album (albumid, title, artistid) 
    VALUES ((SELECT MAX(albumid)+1 FROM album), 'Casting Crowns', 276);

-- Remove album for further testing
SAVEPOINT before_new_album;
ROLLBACK TO SAVEPOINT before_new_album;

-- Create an after delete trigger on the customer table that fires after a row is deleted from the table
CREATE OR REPLACE TRIGGER remove_customer
AFTER DELETE ON customer
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('Customer removed!');
END;
/

-- Test trigger
DELETE FROM customer
    WHERE customerid = 61;

-- Restore customer for further testing
SAVEPOINT before_remove_customer;
ROLLBACK TO SAVEPOINT before_remove_customer;

-- 7.1
-- Create an inner join between customers and invoice, and specify the name of the customer and invoiceID
SELECT c.firstname, c.lastname, i.invoiceID
FROM customer c
INNER JOIN invoice i
ON c.customerid = i.customerid;

-- 7.2
-- Create an outer join that joins customer and invoice, specifying customer ID, name, invoice ID, and total
SELECT c.customerid, c.firstname, c.lastname, i.invoiceid, i.total
FROM customer c
FULL OUTER JOIN invoice i
ON c.customerid = i.customerid;

-- 7.3
-- Create a right join that joins album and artist, specifying artist name and title
SELECT art.name, alb.title
FROM album alb
RIGHT JOIN artist art
ON alb.artistid = art.artistid;

-- 7.4
-- Create a cross join that joins album and artist, sorting by artist name in ascending order
SELECT *
FROM album
CROSS JOIN artist
ORDER BY name;

-- 7.5
-- Perform a self join on employee, joining on the reportsto column (to employee ID?)
SELECT *
FROM employee e
INNER JOIN employee em
ON e.reportsto = em.employeeid;


--Note: end of Part One, see OfficeSupply.sql file