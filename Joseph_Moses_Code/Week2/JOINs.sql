--JOINS
--OUTER JOIN
--INNER JOIN IMPLICIT WITH JOIN KEYWORD
--CROSS JOIN
--SELF JOIN
--EQUI JOINS (Checks for column equality)
--THETA JOINS (NON-EQUI JOINS)

SELECT *
FROM employees;

SELECT *
FROM jobs;

--INNER EQUI JOIN
SELECT *
FROM employees e
JOIN jobs j
ON e.job_id = j.job_id;

--INNER EQUI JOIN
SELECT *   
FROM employees e
JOIN jobs j
USING (job_id);

-- INNER THETA JOIN (NON-EQUI JOIN)
SELECT e.employee_id, e.first_name, e.last_name, e.salary, d.department_id
FROM employees e
JOIN departments d
ON e.department_id BETWEEN 10 AND 60; 

-- CROSS JOIN: Produces a CARTESIAN PRODUCT
SELECT *
FROM employees
CROSS JOIN departments;

-- ACCIDENTAL CROSS JOIN
SELECT *
FROM employees, departments;

--SELF JOIN
SELECT e.employee_id, e.first_name, e.last_name, e.salary, e.job_id
FROM employees e
JOIN employees em
ON e.manager_id = em.employee_id
ORDER BY employee_id;


