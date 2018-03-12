SELECT * 
FROM products
WHERE
 (
   product_id < 5
   OR (product_name = 'Amoxicillin' AND price > 10)
 )
 AND
 (
   price <= 10
   OR expiration_date = DATE '2018-04-30'
 );
 
SELECT name, monthly_budget
FROM department
WHERE monthly_budget >= 20000;

SELECT name, phone, birthdate
FROM employee
WHERE birthdate < DATE '1990-01-01';


SELECT * 
FROM employee
WHERE
 (
   department_id = 1 
   OR department_id = 2
 )
 AND
 (
   monthly_salary < 3000 
   OR birthdate < DATE '1985-01-01'
 );
 
 
SELECT DISTINCT department_id
FROM employee;

SELECT UNIQUE department_id
FROM employee;

SELECT DISTINCT department_id, name
FROM employee;

SELECT * 
FROM employee
ORDER BY birthdate ASC;

SELECT * 
FROM employee
ORDER BY department_id, monthly_salary DESC;

SELECT id, name, department_id, monthly_salary
FROM employee
ORDER BY    
    CASE department_id
        WHEN 1
            THEN monthly_salary
        ELSE id
END;

ALTER TABLE products
ADD alternate_name VARCHAR2(50);

UPDATE products
SET alternate_name = SUBSTR(product_name, 1, 3)
WHERE product_id < 4;

SELECT COUNT (product_id), SUM(price), COUNT(alternate_name)
FROM products;

SELECT COUNT (*) /* the same speed as below in current oracle versions */
FROM products;

SELECT COUNT(1) /* used to be faster, now they're the same */
FROM products;

SELECT COUNT(*) AS employee_count,
    SUM(monthly_salary) as salary_sum,
    AVG(monthly_salary) AS avg_salary,
    SUM(monthly_salary) / COUNT(*) AS avg_salary_manual
FROM employee
WHERE department_id = 1;

SELECT department_id, COUNT(*)
FROM employee
GROUP BY department_id;