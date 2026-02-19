# Write your MySQL query statement below
SELECT 
  d.name as Department,
  e.name as Employee,
  e.salary as Salary
FROM Employee e
JOIN Department d on e.departmentId = d.id
WHERE (e.DepartmentId, e.salary ) IN (
   SELECT departmentId, MAX(salary)
   FROM Employee
   GROUP BY departmentId


);
