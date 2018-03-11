--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE employee_names
IS
BEGIN
    SELECT firstname, lastname FROM employee;
END;
/
EXECUTE employee_names;
--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE update_employee_info(employee_id IN number)
IS
BEGIN
    UPDATE employee 
    SET city = 'Reston', state = 'Virginia', country = 'United States', postalcode = '20190'
    WHERE employeeid = employee_id;
END;
/
--Task – Create a stored procedure that returns the managers of an employee.

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE get_customer_name_company(customer_id IN number, customer_first OUT varchar2, 
        customer_last OUT varchar2, customer_company OUT varchar2)
IS
BEGIN
    SELECT firstname, lastname, company
    INTO customer_first, customer_last, customer_company
    FROM customer WHERE customerid = customer_id;
END;
/
