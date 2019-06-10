              
Deliverables
------------

(1) Java program that can be run from command line
	
	Provided
   
(2) Source Code for the Java program

    Provided

(3) MySQL schema used for the log data

    find schema.sql file in root directory

(4) SQL queries for SQL test   
 	
 	find below
 	
 	
 	
SQL
---

(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.

            SELECT l.ip FROM log l 
                WHERE l.date BETWEEN '2017-01-01.13:00:00' AND '2017-01-01.13:00:00' + INTERVAL 1 HOUR
                GROUP BY l.ip HAVING count(l.ip) >= 100;

(2) Write MySQL query to find requests made by a given IP.

            SELECT l.* from log l
                WHERE ip = '192.168.1.6';