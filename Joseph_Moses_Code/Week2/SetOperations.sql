--SET OPERATIONS

/* UNION is an operator keyword in SQL that is used to combine the
results from queries, and then removes the duplicate rows from
the final result set. */

SELECT id
FROM department
UNION
SELECT department_id
FROM employee;

--WRONG!
SELECT name
FROM department
UNION
SELECT department_id
FROM employee;

/* UNION things to know!
1) The result set of each SELECT statment MUST have the same # of columns
2) The columns in each result set MUST have compatible datatypes */

/* UNION ALL 
Keeps duplicates*/
SELECT id
FROM department
UNION ALL
SELECT department_id
FROM employee;

/* INTERSECT
Returns only the rows which are returned by both queries.
So, in order for a row to be included in the final result set,
it must be included in the first AND the second queries. Also,
after calculating the intersection of the results from the component
queries, duplicate rows are eliminated.
*/

SELECT id
FROM department
WHERE monthly_budget > 15000
INTERSECT
SELECT department_id
FROM employee
WHERE monthly_salary BETWEEN 2000 AND 2500;

/*
The company wants to send a congratulations letter to employees who
were born in the months of May or June, and they also want to send
a letter to employees who were hired by the company in those months.

Write a compound query to generate the list of employees that should
get the two letters. Every employee needs to appear only once in this
report. Columns in the result set should be pertinent to the task.
*/

SELECT id, name, email, birthdate, hire_date
FROM employee
WHERE TO_CHAR(birthdate, 'MM') IN ('05','06')
INTERSECT
SELECT id, name, email, birthdate, hire_date
FROM employee
WHERE TO_CHAR(hire_date, 'MM') IN ('05','06');
