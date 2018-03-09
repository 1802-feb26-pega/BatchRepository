--4.0 Stored Procedures 4
--In this section you will be creating and executing stored procedures.
--You will be creating various types of stored procedures that take input and output parameters.

--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE get_all_employee_names(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT firstName, lastName
    FROM employee;
END;
/

VAR c REFCURSOR;
EXECUTE get_all_employee_names(:c);
PRINT c;

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE update_employee_phone(l_empId IN NUMBER, l_phone IN VARCHAR2)
IS
BEGIN
    UPDATE employee
    SET phone = l_phone
    WHERE employeeId = l_empId;
END;
/

EXECUTE update_employee_phone(10, '(218) 739-3841');

--Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE get_employee_manager(l_empId IN NUMBER, cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR
    SELECT m.employeeId, m.firstName, m.lastName
    FROM employee e
    LEFT OUTER JOIN employee m
    ON e.reportsto = m.employeeId
    WHERE e.employeeID = l_empId
    ORDER BY employeeId; 
END;
/

VAR c2 REFCURSOR;
EXECUTE get_employee_manager(5, :c2);
PRINT c2;

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE get_customer_info(l_customerId IN NUMBER,
                                        o_firstName OUT VARCHAR2,
                                        o_lastName OUT VARCHAR2,
                                        o_company OUT VARCHAR2)
IS
BEGIN
    SELECT c.firstName, c.lastName, c.company
    INTO o_firstName, o_lastName, o_company
    FROM customer c
    WHERE c.customerId = l_customerId; 
END;
/

VAR l_firstName VARCHAR2;
VAR l_lastName VARCHAR2;
VAR l_company VARCHAR2;
EXECUTE get_customer_info(1, :l_firstName, :l_lastName, :l_company);
PRINT l_firstName; 
PRINT l_lastName; 
PRINT l_company;
