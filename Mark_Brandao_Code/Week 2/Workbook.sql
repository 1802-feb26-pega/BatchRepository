/***************************************
        SECTION 2 OF WORKBOOK
***************************************/

/* 2.1 SELECT */
SELECT *
FROM employee;

SELECT *
FROM employee
WHERE lastname = 'King';

SELECT *
FROM employee
WHERE firstname = 'Andrew' AND reportsto IS NULL;


/* 2.2 ORDER BY */
SELECT *
FROM album
ORDER BY title DESC;

SELECT firstname
FROM customer
ORDER BY city;


/* 2.3 INSERT INTO */
INSERT INTO Genre (Genreid, Name) VALUES (26, 'Folk');
INSERT INTO Genre (Genreid, Name) VALUES (27, 'Musical Theater');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (9, 'Brandao', 'Mark', 'IT Staff', 6, TO_DATE('1995-05-04 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-02-26 00:00:00','yyyy-mm-dd hh24:mi:ss'), '20853 Cardiff Ct', 'Ashburn', 'VA', 'United States', '20147', '+1 (540) 847-1587', '+1 (540) 847-1587', 'mark@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10, 'Lopez', 'Jackie', 'Sales Support Agent', 2, TO_DATE('1993-11-19 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2017-7-13 00:00:00','yyyy-mm-dd hh24:mi:ss'), '20853 Cardiff Ct', 'Ashburn', 'VA', 'United States', '20147', '+1 (571) 247-7761', '+1 (571) 247-7761', 'jackie@chinookcorp.com');

INSERT INTO Customer VALUES (60, 'Mark', 'Brandao', NULL, '20853 Cardiff Ct', 'Ashburn', 'VA', 'United States', '20147', '(540) 847-1587', '(540) 847-1587', 'mark@me.com', 4);
INSERT INTO Customer VALUES (61, 'Jackie', 'Lopez', NULL, '20853 Cardiff Ct', 'Ashburn', 'VA', 'United States', '20147', '(571) 247-7761', '(571) 247-7761', 'jackie@me.com', 4);


/* 2.4 UPDATE */
UPDATE Customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

UPDATE Artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

/* 2.5 LIKE */
SELECT *
FROM invoice
WHERE billingaddress LIKE 'T%';


/* 2.6 BETWEEN */
SELECT *
FROM invoice
WHERE total BETWEEN 15 AND 50;

SELECT *
FROM employee
WHERE hiredate BETWEEN DATE '2003-06-1' AND DATE '2004-3-1';


/* 2.7 DELETE */
ALTER TABLE invoice
DROP CONSTRAINT FK_INVOICECUSTOMERID;

ALTER TABLE invoice ADD CONSTRAINT FK_INVOICECUSTOMERID
FOREIGN KEY(customerid) REFERENCES customer(customerid)
ON DELETE CASCADE;

ALTER TABLE invoiceline
DROP CONSTRAINT FK_INVOICELINEINVOICEID;

ALTER TABLE invoice ADD CONSTRAINT FK_INVOICELINECUSTOMERID
FOREIGN KEY(invoiceid) REFERENCES invoice(invoiceid)
ON DELETE CASCADE;

DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

/***************************************
        SECTION 3 OF WORKBOOK
***************************************/

/* 3.1 SYSTEM DEFINED FUNCTIONS */
CREATE OR REPLACE FUNCTION get_current_time
RETURN DATE IS l_current_time DATE;
BEGIN
    SELECT CURRENT_TIMESTAMP
    INTO l_current_time
    FROM dual;
    return l_current_time;
END;
/
SELECT get_current_time() FROM dual;

CREATE OR REPLACE FUNCTION get_mediatype_length(id NUMBER)
RETURN NUMBER IS mediatype_length NUMBER;
BEGIN
    SELECT LENGTH(mt.name)
    INTO mediatype_length
    FROM mediatype mt
    WHERE mt.mediatypeid = id;
    return mediatype_length;
END;
/
SELECT get_mediatype_length(1) FROM dual;


/* 3.2 SYSTEM DEFINED AGGREGATE FUNCTIONS */
CREATE OR REPLACE FUNCTION l_invoice_avg
RETURN NUMBER IS l_avg NUMBER(4,2);
BEGIN
    SELECT AVG(total)
    INTO l_avg
    FROM invoice;
    RETURN l_avg;
END;
/
SELECT l_invoice_avg() FROM dual;



CREATE OR REPLACE FUNCTION l_max_price_track
RETURN NUMBER IS l_price NUMBER(4,2);
BEGIN
    SELECT MAX(unitprice)
    INTO l_price
    FROM track;
    RETURN l_price;
END;
/
SELECT l_max_price_track() FROM dual;


/* 3.3 USER DEFINED SCALAR FUNCTIONS */
CREATE OR REPLACE FUNCTION l_invoiceline_avg_price
RETURN NUMBER IS l_avg_price NUMBER(4,2);
BEGIN
    SELECT AVG(unitprice)
    INTO l_avg_price
    FROM invoiceline;
    RETURN l_avg_price;
END;
/
SELECT l_invoiceline_avg_price() FROM dual;


/* 3.4 USER DEFINED TABLE VALUED FUNCTIONS */
-- Task: Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE TYPE emp_row AS OBJECT(
    employeeid NUMBER,
    lastname VARCHAR2(20),
    firstname VARCHAR2(20),
    title VARCHAR2(30),
    reportsto NUMBER,
    birthdate DATE,
    hiredate DATE
);

CREATE OR REPLACE TYPE emp_tbl AS TABLE OF emp_row;

CREATE OR REPLACE FUNCTION get_emps
RETURN emp_tbl PIPELINED
AS
    CURSOR data IS
        SELECT employeeid AS id, lastname as ln, firstname as fn, title as t,
        reportsto as r, birthdate as bd, hiredate as hd
        FROM employee
        WHERE birthdate > DATE '1968-12-31';
BEGIN
    FOR row IN data
    LOOP
        PIPE ROW(emp_row(row.id, row.ln, row.fn, row.t, row.r, row.bd, row.hd));
    END LOOP;
END;
/

SELECT * FROM TABLE(get_emps());
/***************************************
        SECTION 4 OF WORKBOOK
***************************************/
/* 4.1 BASIC STORED PROCEDURE */
-- Task: Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE get_emp_names(rc OUT SYS_REFCURSOR)
Is
BEGIN
    OPEN rc FOR SELECT firstname, lastname FROM employee;
END;
/


/* 4.2 STORED PROCEDURE INPUT PARAMETERS */
-- Task: Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE 
(
  THE_EMPLOYEEID IN NUMBER,
  NEW_LASTNAME IN VARCHAR2,
  NEW_FIRSTNAME IN VARCHAR2,
  NEW_TITLE IN VARCHAR2,
  NEW_REPORTSTO IN NUMBER,
  NEW_BIRTHDATE IN DATE,
  NEW_HIREDATE IN DATE,
  NEW_ADDRESS IN VARCHAR2,
  NEW_CITY IN VARCHAR2,
  NEW_STATE IN VARCHAR2,
  NEW_COUNTRY VARCHAR2,
  NEW_POSTALCODE VARCHAR2,
  NEW_PHONE VARCHAR2,
  NEW_FAX VARCHAR2,
  NEW_EMAIL VARCHAR2
)
AS 
BEGIN
  UPDATE EMPLOYEE
  SET LASTNAME = 
        CASE WHEN NEW_LASTNAME IS NULL 
        THEN LASTNAME
        ELSE NEW_LASTNAME
        END,
      FIRSTNAME =
        CASE WHEN NEW_FIRSTNAME IS NULL 
        THEN FIRSTNAME
        ELSE NEW_FIRSTNAME
        END,
      TITLE = NEW_TITLE,
      REPORTSTO = NEW_REPORTSTO,
      BIRTHDATE = NEW_BIRTHDATE,
      HIREDATE = NEW_HIREDATE,
      ADDRESS = NEW_ADDRESS,
      CITY = NEW_CITY,
      STATE = NEW_STATE,
      COUNTRY = NEW_COUNTRY,
      POSTALCODE = NEW_POSTALCODE,
      PHONE = NEW_PHONE,
      FAX = NEW_FAX,
      EMAIL = NEW_EMAIL
    WHERE EMPLOYEEID = THE_EMPLOYEEID;
END UPDATE_EMPLOYEE;
/

-- Task: Create a stored procedure that returns the manager of an employee.
CREATE OR REPLACE PROCEDURE MANAGER_OF_EMPLOYEE 
(
  THE_EMPLOYEEID IN NUMBER
)
AS 
  m_fn VARCHAR2(20);
  m_ln VARCHAR2(20);
  e_fn VARCHAR2(20);
  e_ln VARCHAR2(20);
BEGIN
  SELECT m.firstname, m.lastname, e.firstname, e.lastname INTO m_fn, m_ln, e_fn, e_ln
  FROM employee e
  LEFT OUTER JOIN employee m 
  ON m.employeeid = e.reportsto
  WHERE e.employeeid = THE_EMPLOYEEID AND e.reportsto = m.employeeid;
  DBMS_OUTPUT.PUT_LINE(m_fn || ' ' || m_ln || ' is the manager of ' || e_fn || ' ' || e_ln);
END MANAGER_OF_EMPLOYEE;
/



/* 4.3 STORED PROCEDURE OUTPUT PARAMETERS */
-- Task: Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE cust_comp
(
    c_id IN NUMBER
)
AS
    c_fn VARCHAR2(20);
    c_ln VARCHAR2(20);
    c_comp VARCHAR2(20);
BEGIN
    SELECT firstname, lastname, company
    INTO c_fn, c_ln, c_comp
    FROM customer
    WHERE customerid = c_id;
    DBMS_OUTPUT.PUT_LINE(c_fn || ' ' || c_ln || ' works for: ' || c_comp);
END cust_comp;
/

/***************************************
        SECTION 5 OF WORKBOOK
***************************************/
/* TRANSACTIONS */
-- Task: Create a transaction that given an invoiceId will delete that invoice (There may
-- be constraints that rely on this, find out how to resolve them)
CREATE OR REPLACE PROCEDURE delete_invoice
(
    the_invoiceid IN NUMBER
)
IS
BEGIN
    DELETE FROM invoice
    WHERE invoiceid = the_invoiceid;
    COMMIT;
END;
/


-- Task: Create a transaction nested within a stored procedure that inserts a new record
-- in the Customer table



/***************************************
        SECTION 6 OF WORKBOOK
***************************************/
/* 6.1 TRIGGERS AFTER/FOR */
-- Task: Create an after insert trigger on the employee table fired after a new record
-- is inserted into the table.
CREATE OR REPLACE TRIGGER employee_trigger
AFTER INSERT ON employee
FOR EACH ROW

BEGIN
    DBMS_OUTPUT.put_line('Hey, you inserted something.');
END;
/

-- Task: Create an after update trigger on the album table that fires after a row is 
-- inserted in the table.
CREATE OR REPLACE TRIGGER album_update
AFTER UPDATE ON album
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.put_line('Hey, you updated something.');
END;
/

-- Task: Create an after delete trigger on the customer table that fires after a row is
-- deleted from the table.
CREATE OR REPLACE TRIGGER delete_customer
AFTER DELETE ON customer
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.put_line('Hey, you deleted something.');
END;
/


/***************************************
        SECTION 7 OF WORKBOOK
***************************************/
/* 7.1 INNER JOINS */
-- Task: Create an inner join that joins customers and orders and specifies the name
-- of the customer and the invoiceId.
SELECT *
FROM customer
JOIN invoice
USING (customerid);


/* 7.2 OUTER JOINS */
-- Task: Create an outer join that joins the customer and invoice table, specifying the
-- customerId, firstname, lastname, invoiceId, and total.
SELECT c.customerId, c.firstname, c.lastname, i.invoiceid, i.total
FROM customer c
FULL OUTER JOIN invoice i
ON c.customerid = i.customerid;


/* 7.3 RIGHT JOINS */
-- Task: Create a right join that joins album and artist specifying artist name and title.
SELECT al.title, ar.name
FROM album al
RIGHT JOIN artist ar
ON al.artistid = ar.artistid;


/* 7.4 CROSS JOINS */
-- Task: Create a cross join that joins album and artist and sorts by artist name
-- in ascending order
SELECT *
FROM album, artist
ORDER BY artist.NAME;


/* 7.5 SELF JOINS */
-- Task: Perform a self-join on the employee table, joining on the reportsto column.
SELECT *
FROM employee e, employee m
WHERE e.reportsto = m.employeeid;




