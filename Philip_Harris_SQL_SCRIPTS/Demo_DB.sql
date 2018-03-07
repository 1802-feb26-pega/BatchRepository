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