--SUBQUERIES
SELECT *
FROM employee
WHERE monthly_salary =
    (
        SELECT MAX(monthly_salary) --SCALAR SUBQUERY! ALWAYS RETURN ONE AND ONLY ONE VALUE!
        FROM employee
    );    
    
SELECT *
FROM employee
WHERE department_id IN
    (
        SELECT id
        FROM department
        WHERE monthly_budget > 20000
    );
    
SELECT *
FROM employee
WHERE department_id IN
    (
        SELECT department_id    --is referenced from the main query and makes this a flawed query
                                -- CORRELATED SUBQUERY
        FROM department
        WHERE monthly_budget > 25000
    );
  
SELECT *
FROM department d
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

