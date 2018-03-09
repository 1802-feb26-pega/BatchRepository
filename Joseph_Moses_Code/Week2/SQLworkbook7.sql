--7.0 JOINS 5
--In this section you will be working with combing various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.

--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT customer.firstname, customer.lastname, invoice.invoiceId
FROM customer
INNER JOIN invoice ON customer.customerId = invoice.customerId
ORDER BY customer.customerId;

--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT customer.customerId, customer.firstname, customer.lastname, invoice.invoiceId, invoice.total
FROM customer
FULL OUTER JOIN invoice on customer.customerId = invoice.customerId
ORDER BY customer.customerId;

--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT artist.name, album.title
FROM album
RIGHT JOIN artist 
ON artist.artistId = album.artistId;

--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM album
CROSS JOIN artist
ORDER BY artist.name;

--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT A.employeeID, A.firstName, A.lastName, B.employeeID AS supervisorId, 
        B.firstName AS supervisorFN, b.lastName AS supervisorLN
FROM employee A
LEFT JOIN employee B
ON A.reportsto = B.employeeId
ORDER BY employeeId;

