SELECT *
FROM employee
WHERE MONTHLY_SALARY =
    (
     SELECT MAX(monthly_salary) --SCALAR SUBQUERY! ALWAYS RETURNS
     FROM employee              --ONE AND ONLY ONE VALUE!
     );
     
SELECT * 
FROM employee
WHERE DEPARTMENT_ID IN
    ( 
     SELECT id
     FROM department
     WHERE monthly_budget > 20000
     );
     

--INCORRECT CORRELATED SUBQUERY
SELECT * 
FROM employee
WHERE department_id IN
    (
     SELECT department_id 
     FROM department
     WHERE monthly_budget > 25000
     );
     
--CORRECT
SELECT * 
FROM department d
WHERE 3 < 
    (
     SELECT COUNT(*)
     FROM employee
     WHERE department_id = d.id
    );
    
SELECT name,(
    SELECT MAX(monthly_salary)
    FROM EMPLOYEE
    WHERE department_id = d.id) as max_sal_in_dept
FROM department d;
    
--------------------------------------------------------------------
--UNION is an operator used in SQL that is used to combine the 
--results from queries, and then removes the duplicate rows from
--the final result.

SELECT id
FROM department
UNION ALL
SELECT department_id
FROM employee;
  
-- UNION things to know
--The result set of each SELECT statement must have the same # of columns
--THe columns in each result set MUST have compatible data types


--INTERSECT
/*Return only the rows which are returned by both queries    
So, in order for a row to be included inthe final result set,
it must be inclued in teh first AND the second queries. ALso, after calculating the intersection of the reuslts from the component
queries, duplicate row are eliminted.    
    
    
  */  
    
    
SELECT id
FROm department
WHERE monthly_budget > 15000
INTERSECT
SELECT department_id
FROM employee
WHERE MONTHLY_SALARY BETWEEN 2000 AND 2500;



--SLACK QUERSTION
SELECT ID, name, email, birthdate, hire_date
FROM employee
WHERE TO_CHAR(birthdate, 'MM') IN ('05','06')
MINUS
SELECT ID, name, email, birthdate, hire_date
FROM employee
WHERE TO_CHAR(hire_date, 'MM') IN ('05','06');

--JOINS
--OUTER
--INNER
--CROSS
--SELF
--EQUI
--Thetha (NON-equi)

SELECT e.first_name,e.last_name,j.job_id, j.min_salary
FROM employees e
JOIN jobs j -- INNER JOINS,EQUI JOIN
on e.job_id = j.job_id;

SELECT e.first_name,e.last_name, job_id,j.min_salary
FROM employees e
JOIN jobs j --INNER jOINS, EQUI JOIN
using(job_id);

--THETA EXAMPLES ALSO called INNER JOIN
SELECT e.employee_id, e.first_name,e.last_name, e.salary, d.department_id
FROM employees e
JOIN departments d
on e.department_id BETWEEN 10 AND 40;

--CROSS join produces a CARTESIAN PRODUCT
SELECT *
FROM employees
cross JOIN department;

--ACCIDENTAL CROSS JOIN
SELECT *
FROM employees, departments, jobs;

--SELF JOIN
SELECT e.employee_id,e.first_name,e.last_name, e.salary
FROM employees 
JOIN employees em
ON e.manager_id = em.employee_id
ORDER BY employee_id;

SELECT e.employee_id,e.first_name,e.last_name, e.salary
FROM employees e
ORDER BY employee_id;


--Functions

Create OR REPLACE function get_current_time
RETURN TIMESTAMP IS l_systimestamp TIMESTAMP;
BEGIN 
    SELECT systimestamp
    INTO l_systimestamp
    FROM DUAL;
    Return l_systimestamp;
END;
/
SELECT get_current_time() FROM DUAL;

CREATE OR REPLACE PROCEDURE insertTest(

  testid IN NUMBER,

  testname IN VARCHAR2,

  testvalue IN NUMBER)

IS

BEGIN

  INSERT INTO test VALUES(testid, testname, testvalue);

END;
/