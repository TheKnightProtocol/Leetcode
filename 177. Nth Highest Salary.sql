CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
DETERMINISTIC
BEGIN
  DECLARE result INT;
  DECLARE offset_value INT;

  -- Calculate OFFSET separately to avoid syntax errors
  SET offset_value = N - 1;

  -- Select the Nth highest distinct salary
  SET result = (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    LIMIT 1 OFFSET offset_value
  );

  RETURN result;
END;
