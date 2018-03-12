/*6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
6.1 AFTER/FOR 3
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
*/

CREATE OR REPLACE TRIGGER after_employee_insert
AFTER
INSERT ON employee
BEGIN
    dbms_output.put_line('employee added');
END;
/

CREATE OR REPLACE TRIGGER after_album_insert
AFTER
UPDATE ON album
BEGIN
    dbms_output.put_line('album added');
END;
/

CREATE OR REPLACE TRIGGER after_customer_delete
AFTER
DELETE ON customer
BEGIN
    dbms_output.put_line('customer deleted');
END;
/