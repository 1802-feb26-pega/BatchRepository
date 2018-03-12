--SUBQUERIES
SELECT *
FROM employee
WHERE monthly_salary =
    (
     SELECT MAX(monthly_salary) -- SCALAR SUBQUERY! ALWAYS RETURNS
     FROM employee              -- ONE AND ONLY ONE VALUE!
    );
    
SELECT *
FROM employee
WHERE department_id IN
    (
     SELECT ID
     FROM department
     WHERE monthly_budget > 20000
    );
    
SELECT *
FROM employee
WHERE department_id IN
    (
     SELECT department_id
     FROM department
     WHERE monthly_budget > 25000
    );
    
SELECT * 
FROM department d                 --TABLE ALIASING
WHERE 3 <
 (
    SELECT COUNT(*)
    FROM employee
    WHERE department_id = d.id
 );
 
SELECT name, (
    SELECT MAX(monthly_salary)
    FROM employee
    WHERE department_id = d.id) AS max_sal_in_dept
FROM department d;

--------------------------------------------------------------------------------
-- UNION is a n operator keyword in SQL that is used to combine the results   -- 
-- from queries and then removes the duplicate rows from the final result set --
--------------------------------------------------------------------------------

SELECT id
FROM department
UNION
SELECT department_id
FROM employee;

-- UNION things to know!
-- 1) The result set of each SELECT statement MUST have the same number of columns
-- 2) The columns in each result set MUST have compatible datatypes

SELECT id
FROM department
UNION ALL
SELECT department_id
FROM employee;

-- INTERSECT
-- returns only the rows which are returned by both queries.
-- So, in order for a row to be included in the final result set,
-- it must be included in the first AND the second quries. Also,
-- after calculting the intersection of the results from the component
-- queries, duplicate rows are eliminated.

SELECT id
FROM department
WHERE monthly_budget > 15000
INTERSECT
SELECT department_id
FROM employee
WHERE monthly_salary BETWEEN 2000 AND 2500;


/* The company wants to send a congratulations letter to employees who were born in the months of May or 
   June, and they also want to send a letter to employees who were hired by the company in those months.

   Write a compound query to generate the list of employees that should get the two letters. Every employee 
   needs to appear only once in this report. Columns in the result set should be pertinent to the task. */
   
SELECT id, name, email, birthdate, hire_date
FROM employee
WHERE TO_CHAR(birthdate, 'MM') IN ('06', '05')
INTERSECT
SELECT id, name, email, birthdate, hire_date
FROM employee
WHERE TO_CHAR(hire_date, 'MM') IN ('06', '05');

COMMIT;

--JOINS
--OUTER JOINS
--INNER JOIN
--CROSS JOIN
--SELF JOIN

--EQUI JOIN
--THETA JOINS (NON-EQUI JOINS)

--INNER JOIN (EQUI JOIN) EQUI JOIN IS =
SELECT *
FROM employees e
INNER JOIN jobs j
ON e.job_id = j.job_id;

--INNER (THETA JOIN / NON-EQUI JOIN) is not using =
SELECT e.employee_id, e.first_name, e.last_name, e.salary, d.department_id
FROM employees e
JOIN departments d
on e.department_id BETWEEN 10 AND 60;


-- CROSS JOIN: PRODUCES A CARTESIAN PRODUCT (why would you do this?)
SELECT *
FROM employees
CROSS JOIN departments;

-- ACCIDENTAL CROSS JOIN
SELECT *
FROM employees, departments;

-- SELF JOIN: compares two different columns of the same table
SELECT e.employee_id, e.first_name, e.last_name, e.salary
FROM employees e
JOIN employees em
ON e.manager_id = em.employee_id
ORDER BY employee_id;

/* SELECT * 
FROM test; */