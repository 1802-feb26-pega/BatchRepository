--6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.

--6.1 AFTER/FOR 3
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER after_employee_insert
AFTER
INSERT ON employee
FOR EACH ROW
BEGIN
    dbms_output.put_line('employee added');
END;
/

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER after_album_insert
AFTER
UPDATE ON album
FOR EACH ROW
BEGIN
    dbms_output.put_line('album added');
END;
/

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER after_customer_delete
AFTER
DELETE ON customer
FOR EACH ROW
BEGIN
    dbms_output.put_line('customer deleted');
END;
/
