-- Subqueries
SELECT *
FROM employee
where MONTHLY_SALARY = 
    (
        select max(monthly_salary)  -- Scaler Subquery.  Always returns
        from employee               -- one and only one value!
    );

select *
from employee
where department_id in
    (
        select id
        from department
        where monthly_budget > 20000
    );

select *
from employee
where department_id IN
    (
        select department_id        -- Correlated Subquery
        from department             -- This one gives the incorrect results
        where monthly_budget > 25000
    );

-- Correlated Subquery slower...has to run subquery multiple times
select *
from department d
where 3 <
    (
        select count(*)             -- Subquery is untestable
        from employee               -- alias in main query
        where department_id = d.id
    );

select name, (
    select max(monthly_salary)
    from employee
    where department_id = d.id) AS max_sal_in_dept
From department d;

-- Same as above, different implementation
select name, (
    select max(monthly_salary)
    from employee
    group by department_id
    having department_id = d.id) AS max_sal_in_dept
From department d;

-- Set Operators
-- UNION is an operator keyword in SQL that is used to combine the results from queries,
-- and then remove the duplicate rows from the final result set.

select id
from department
union
select department_id
from employee;

-- UNION things to know
-- 1) The result set of each SELECT statement MUST have the same number of columns
-- 2) The columns in each result set MUST have compatible datatypes

select id
from department
union all
select department_id
from employee;

/* INTERSECT returns only the rows which are returned by both queries.
    So, in order for a row to be included in the final results set,
    it must be included in the first AND the second queries.  Also,
    after calculating the intersection of the results from the component
    queries, duplicate rows are eliminated
*/

select id
from department
where monthly_budget > 15000
intersect
select department_id
from employee
where monthly_salary between 2000 and 2500;

-- Reverse is MINUS
select id
from department
where monthly_budget > 15000
minus
select department_id
from employee
where monthly_salary between 2000 and 2500;

-- Challenge
select id, name, birthdate, hire_date, email
from employee
where to_char(birthdate, 'MM') IN ('05', '06')
intersect
select id, name, birthdate, hire_date, email
from employee
where to_char(hire_date, 'MM') IN ('05', '06');

select id, name, birthdate, hire_date, email
from employee
where to_char(birthdate, 'MM') IN ('05', '06')
minus
select id, name, birthdate, hire_date, email
from employee
where to_char(hire_date, 'MM') IN ('05', '06');

-- JOINS
-- OUTER JOIN
-- INNER JOIN (implicitly used if only JOIN is used)
-- CROSS JOIN
-- SELF JOIN
-- EQUI JOINS
-- THETA JOINS (NON-EQUI JOINS)

select *
from employees e
join jobs j
on e.job_id = j.job_id;

-- USING or = means it is an equi join
select e.first_name, e.last_name, j.job_id, j.min_salary
from employees e
join jobs j
on e.job_id = j.job_id;

select e.first_name, e.last_name, job_id, j.min_salary
from employees e
join jobs j
using (job_id);

-- BETWEEN/AND or <, >, <=, and >= is a theta join
select e.employee_id, e.first_name, e.last_name, e.salary, d.department_id
from employees e
join departments d
on e.department_id between 10 and 60;

-- CROSS JOIN: Produces a Cartesian Product (all the rows from one table combined with all the rows of the second table)
select *
from employees
cross join departments;

-- Accidental CROSS JOIN
select *
from employees, departments;

-- SELF JOIN
select e.employee_id, e.first_name, e.last_name, e.salary, e.job_id
from employees e
join employees em
on e.manager_id = em.employee_id
order by employee_id;

select e.employee_id, e.first_name, e.last_name, e.salary
from employees e
order by employee_id;

/* 
PL/SQL = Procedural Language extension of SQL
    Stored Functions
      - Definition: used to compute some value
      - must have a return value
      - allowed parameters: INPUT
      - error handling is not possible
      - DML & DDL statements are not supported
      - cannot fire triggers
      - can be used in SELECT, WHERE, and HAVING clauses
      - cannot call stored procedure, but can (maybe?) call other functions
      - cannot use the TIMESTAMP datatype
      - cannot use print commands
    
    Stored Procedures
      - Definition: used to perform a specific task
      - may or may not return a value
      - allowed parameters: INPUT, OUTPUT, INPUT/OUTPUT
      - error handling is possible using try/catch block(s)
      - DML & DDL statements are supported
      - can fire triggers
      - cannot be used in SELECT, WHERE, and HAVING clauses
      - can call stored functions
      - can use ALL datatypes
      - can use print commands
    
    Triggers
      - Definition: stored program in PL/SQL which is automatically executed upon the occurrence
            of some event (such as an INSERT into a table).
*/

-- Statements for use in JBDC demo
drop table test;

truncate table test;

select *
from test;

-- Procedure for JBDC demo
CREATE OR REPLACE PROCEDURE insertTest(
  testid IN NUMBER,
  testname IN VARCHAR2,
  testvalue IN NUMBER)
IS
BEGIN
  INSERT INTO test VALUES(testid, testname, testvalue);
END;
/

-- Sequence example
CREATE SEQUENCE artist_seq
MINVALUE 1
MAXVALUE 999999999999
INCREMENT BY 1
START WITH 276;
/

-- Trigger example
CREATE OR REPLACE TRIGGER artist_trigger
BEFORE INSERT ON artist
FOR EACH ROW

BEGIN
    SELECT artist_seq.NEXTVAL
    INTO :new.artistid
    FROM dual;
END;
/

INSERT INTO artist(name) VALUES('Casting Crowns');