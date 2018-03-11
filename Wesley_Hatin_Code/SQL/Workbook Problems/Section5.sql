--5.1 Transactions
--Task – Create a transaction that, given a invoiceId, will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE delete_invoiceid(id IN number)

--Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
