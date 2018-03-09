SELECT product_id, product_name, price
FROM products
WHERE price < 10;

SELECT *
FROM products
WHERE EXPIRATION_DATE < DATE '2019-04-01';

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

SELECT NAME, MONTHLY_BUDGET
FROM DEPARTMENT
WHERE MONTHLY_BUDGET >= 20000;

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

SELECT department_id
FROM employee;

SELECT DISTINCT department_id
FROM employee;

SELECT DISTINCT department_id, name
FROM employee;

SELECT *
FROM employee
ORDER BY birthdate;

SELECT *
FROM employee
ORDER BY department_id, monthly_salary DESC;

SELECT *
FROM employee
ORDER BY 6, 5 DESC;

SELECT id, name, department_id, monthly_salary
FROM employee
ORDER BY
    CASE department_id
        WHEN 1
            THEN monthly_salary
        ELSE id
END;

SELECT MIN(price), MAX(price), SUM(price), COUNT(product_id)
FROM products;

ALTER TABLE products
ADD alternate_name VARCHAR2(50);

UPDATE products
SET alternate_name = SUBSTR(product_name, 1, 3)
WHERE product_id < 4;

SELECT COUNT(product_id), SUM(price), COUNT(alternate_name)
FROM products;

SELECT COUNT(*)
FROM products;

SELECT COUNT(1)
FROM products;

SELECT COUNT(*) AS employee_count,
    SUM(monthly_salary) AS salary_sum,
    AVG(monthly_salary) AS avg_salary,
    SUM(monthly_salary) / count(*) AS avg_salary_manual
FROM employee
WHERE department_id = 1;

SELECT department_id, COUNT(*) AS emp_count
FROM employee
GROUP BY department_id
ORDER BY emp_count;

