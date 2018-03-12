--2.1
SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE
WHERE LASTNAME = "King";

SELECT * FROM EMPLOYEE
WHERE (FIRSTNAME = 'Andrew') AND (REPORTSTO = NULL);

--2.2
SELECT * FROM ALBUM
ORDER BY TITLE DESC;

SELECT FIRSTNAME FROM CUSTOMER
ORDER BY CITY ASC;

--2.3
INSERT INTO GENRE VALUES(25,'Sing-a-longs');
INSERT INTO GENRE VALUES(26,'Snap-a-longs');

INSERT INTO EMPLOYEE VALUES(21,'Hallsted','Ammon','Slave',9,'30-MAY-89',DATE '2018-02-28','Meh','Provo','UT','United States BABAYYY','84604','1(801)602-3161','1234','ammonhallsted@gmail.com');
INSERT INTO EMPLOYEE VALUES(11,'Hallsted','Ammon','Slave',9,'30-MAY-89',DATE '2018-02-28','Meh','Provo','UT','United States BABAYYY','84604','1(801)602-3161','1234','ammonhallsted@gmail.com');

INSERT INTO CUSTOMER VALUES(60,'Ammon','Hallsted','Revature','Somewhere','Reston','VA','USA','20190','770-something','None','frontdesk@revature.com',5);
INSERT INTO CUSTOMER VALUES(61,'Ammon','Hallsted','Revature','Somewhere','Reston','VA','USA','20190','770-something','None','frontdesk@revature.com',5);

--2.4
UPDATE CUSTOMER
SET FIRSTNAME = 'Aaron', LASTNAME = 'Miitchell'
WHERE LASTNAME = 'Walter';

UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedance Clearwater Revival';

--2.5
SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

--2.6
SELECT * FROM INVOICE
WHERE (TOTAL > 15) AND (TOTAL < 50);

SELECT * FROM EMPLOYEE
WHERE (HIREDATE > '01-JUN-2003') AND (HIREDATE < 'O1-MAR-2004');

--2.7
DELETE FROM CUSTOMER
WHERE LASTNAME = 'Walter';

--7
--7.1
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID FROM CUSTOMER
INNER JOIN INVOICE ON INVOICE.CUSTOMERID=CUSTOMER.CUSTOMERID;

