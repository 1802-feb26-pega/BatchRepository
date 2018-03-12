--3.1 System Defined Functions
--Task: create a function that returns the current time
CREATE OR REPLACE FUNCTION get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;

BEGIN
    SELECT systimestamp
    INTO l_systimestamp
    FROM dual;
    RETURN l_systimestamp;
END;
/

--Task: create a function that returns the length of a mediatype from the mediatype table
CREATE OR REPLACE FUNCTION get_mediatype_len(media_type_id IN number)
RETURN number 
IS mtlength number;

BEGIN
    SELECT length(name)
    INTO mtlength
    FROM mediatype
    WHERE mediatypeid = media_type_id;
    RETURN mtlength;
END;
/

select get_mediatype_len(3)from dual;

--3.2 System Defined Aggregate Functions
--Task: Create a function that returns the average total of all invoices
CREATE OR REPLACE FUNCTION get_invoice_avg
RETURN number IS invoice_avg number;

BEGIN
    SELECT avg(total)
    INTO invoice_avg
    FROM invoice;
    RETURN invoice_avg;
END;
/
SELECT get_invoice_avg from dual;

--Task: Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION get_expensive_track
RETURN number IS track_id number;

BEGIN 
    SELECT trackid
    INTO track_id
    FROM track
    WHERE unitprice = (SELECT max(unitprice) FROM track);
    RETURN track_id;
END;
/

SELECT get_expensive_track FROM dual;

--3.3 User Defined Scalar Functions
--Task: Create a function that returns the average price of invoiceline items in the invoiceline table
CREATE OR REPLACE FUNCTION get_invoiceline_avg_price(invoice_id IN number)
RETURN number AS avg_price number;

BEGIN
    SELECT avg(unitprice)
    INTO avg_price
    FROM invoiceline
    WHERE invoiceid = invoice_id;
    RETURN avg_price;
END;
/

SELECT get_invoiceline_avg_price(180) FROM dual;

--3.4 User Defined Table Valued Functions
--Task: Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION employee_after_1968
RETURN SYS_REFCURSOR
IS 
    employee_cursor SYS_REFCURSOR;
BEGIN
    OPEN employee_cursor FOR 
        'SELECT employeeid, firstname, lastname FROM employee
        WHERE birthdate < DATE (12-31-1968)';
    RETURN employee_cursor;
END;
/

var rc refcursor 
EXEC :rc := employee_after_1968;
PRINT rc;