-- Section 2

--2.1

SELECT * FROM employee;
SELECT * FROM employee where lastname = 'King';
SELECT * FROM employee where firstname = 'Andrew' AND reportsto is null;

--2.2
SELECT * FROM album ORDER BY title DESC;
SELECT firstname, city FROM customer ORDER BY city;

--2.3
INSERT INTO genre (GenreId, Name) VALUES (26, 'Psytrance');
INSERT INTO genre (GenreId, Name) VALUES (27, 'Drum and bass');

INSERT INTO employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Email) VALUES (9, 'Rosser', 'William', 'Software Developer', TO_DATE('1996-4-9 21:08:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-2-26 00:00:00','yyyy-mm-dd hh24:mi:ss'), '3905 Remington Way', 'Marietta', 'GA', 'USA', '30066', '+1 (404) 512-1359', 'will.e179@gmail.com');
INSERT INTO employee (EmployeeId, LastName, FirstName, Title, BirthDate) VALUES (10, 'Shmoe', 'Joe', 'The Office HOBO',  TO_DATE('1966-11-9 00:00:00','yyyy-mm-dd hh24:mi:ss'));

INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) 
                VALUES (60, 'Billy', 'Bob', 'Somewhere over the rainbow', 'Oz', 'Wonderland', '777777', '+1 555 555 5555', 'example@example.com', 3);

INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) 
                VALUES (61, 'Louis', 'Cifer', 'Lake Cocytus', 'Judecca', 'Seventh Circle', 'Hell', '666', '+1 666 666 6666', 'sexyAsSin@hotmail.com', 3);
                
--SELECT * FROM customer where CustomerID = 60 OR CustomerID = 61;

--2.4
UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

--2.6
SELECT * FROM invoice WHERE total >= 15 AND total <= 50;
SELECT * FROM employee WHERE hiredate >= DATE '2003-6-1' AND hiredate <= DATE '2004-3-1';

--2.7
DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';


-- Section 3

--3.1
CREATE OR REPLACE FUNCTION getTime RETURN VARCHAR2
IS
    cur_time VARCHAR2(50);
BEGIN
    cur_time := TO_CHAR(SYSDATE, 'HH:MI:SS');
    RETURN cur_time;
END;
/

SELECT getTime() FROM dual;

CREATE OR REPLACE FUNCTION getMediatypeLength RETURN NUMBER
IS
    len NUMBER;
BEGIN
    SELECT COUNT(*) INTO len FROM mediatype;
    RETURN len;
END;
/

--SELECT getMediatypeLength() FROM dual;

--3.2
CREATE OR REPLACE FUNCTION invoiceAve RETURN NUMBER
IS
    ave NUMBER;
BEGIN
    SELECT SUM(total) / count(total) INTO ave FROM invoice;
    RETURN ROUND(ave, 2);
END;
/

SELECT invoiceAve() FROM dual;

CREATE OR REPLACE FUNCTION maxTrackPrice RETURN VARCHAR2
IS
    track_name VARCHAR2(200 BYTE);
BEGIN
    SELECT name INTO track_name FROM (SELECT * FROM track ORDER BY unitprice DESC) WHERE ROWNUM = 1;
    DBMS_OUTPUT.PUT_LINE(track_name);
    return track_name;
END;
/

SELECT maxTrackPrice() FROM dual;

--3.3
CREATE OR REPLACE FUNCTION invoiceLineAve RETURN NUMBER
IS
    ave NUMBER;
BEGIN
    SELECT SUM(unitprice * quantity) / count(unitprice) INTO ave FROM invoiceline;
    RETURN ROUND(ave, 2);
END;
/

SELECT invoiceLineAve() FROM dual;

--3.4

CREATE OR REPLACE FUNCTION after1968 RETURN SYS_REFCURSOR
AS
    tbl SYS_REFCURSOR;
BEGIN
    OPEN tbl FOR
        SELECT firstname, lastname, birthdate FROM employee WHERE birthdate >= DATE '1969-1-1' ORDER BY birthdate DESC;
    return tbl;
END;
/

var rc refcursor;
exec :rc := after1968();
print rc;
    
--SECTION 4
--4.1
CREATE OR REPLACE PROCEDURE employeeNames (names OUT SYS_REFCURSOR)
IS
BEGIN
    open names FOR
        SELECT * FROM employee;
    RETURN;
END;
/

var refcur refcursor; 
EXECUTE employeeNames(:refcur);
print refcur;

-- Section 7

--7.1
--I went a bit overboard with this one...
--I'm bored. Sue me.
SELECT customer.customerid as "Customer Id",
    invoice.invoiceid as "Invoice Id",
    customer.firstname as "First Name", 
    customer.lastname as "Last Name", 
    invoice.total as "Total"
FROM customer
INNER JOIN invoice ON customer.customerid = invoice.customerid
ORDER BY customer.customerid;

--7.2
SELECT customer.customerid,
    customer.firstname,
    customer.lastname,
    invoice.invoiceid,
    invoice.total
FROM customer
FULL OUTER JOIN invoice ON customer.customerid = invoice.invoiceid
ORDER BY customer.customerid;

--7.3
SELECT artist.name, album.title 
FROM album
RIGHT JOIN artist ON artist.artistid = album.artistid;

--7.4
SELECT artist.name, album.title
FROM album
CROSS JOIN artist 
WHERE artist.artistid = album.artistid
ORDER BY artist.name;


--7.5
SELECT a.firstname as "Subordinate Firstname", a.lastname as "Subordinate Lastname", 
    b.firstname as "Superior Firstname", b.lastname as "Superior Lastname"
FROM employee a, employee b
WHERE a.reportsto = b.employeeid
ORDER BY b.lastname;













