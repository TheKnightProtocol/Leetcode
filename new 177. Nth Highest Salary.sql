CREATE FUNCTION getNthHighestSalary (N INT) RETURNS INT 
BEGIN 
  DECLARE result INT;
  DECLARE offset_val INT;
  
  SET offset_val = N - 1;
  
  SET result = (
    SELECT DISTINCT salary
    FROM employee
    ORDER BY salary DESC
    LIMIT offset_val, 1
  );
  
  RETURN result;
END
