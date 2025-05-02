SELECT
    request_at AS Day,
    ROUND(SUM(CASE
        WHEN status IN ('cancelled_by_driver', 'cancelled_by_client') THEN 1
        ELSE 0
    END) / COUNT(*), 2) AS "Cancellation Rate"
FROM
    Trips t
WHERE
    request_at BETWEEN '2013-10-01' AND '2013-10-03'
    AND client_id NOT IN (SELECT users_id FROM Users WHERE banned = 'Yes')
    AND driver_id NOT IN (SELECT users_id FROM Users WHERE banned = 'Yes')
GROUP BY
    request_at
HAVING
    COUNT(*) > 0;
