--6.1 After/For Triggers
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER new_employee_trigger
AFTER INSERT ON employee
FOR EACH ROW

BEGIN
    UPDATE employee SET employeeid = employeeid;
END;
/

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER album_update_trigger 
    AFTER UPDATE ON album 
    FOR EACH ROW 
        
    BEGIN 
      DELETE FROM album WHERE albumid = 0;
    END; 
/

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER customer_delete_trigger 
    AFTER DELETE ON customer 
    FOR EACH ROW
    
    BEGIN 
        UPDATE customer SET customerid = customerid;
    END; 
/