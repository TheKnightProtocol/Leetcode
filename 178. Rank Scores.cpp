# Write your MySQL query statement below
SELECT 
  score,
  DENSE_RANK() OVER (ORDER BY score DESC) As 'rank'
 
FROM Scores
ORDER BY score DESC;
