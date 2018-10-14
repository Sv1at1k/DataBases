--Завд 1
	
SELECT * FROM printer
WHERE  color LIKE 'y'
ORDER BY price DESC
	
--Завд 2
	
SELECT * FROM ships
WHERE  name LIKE '%e%e%'
	
--Завд 3
	
SELECT product.maker, printer.model  FROM  printer,product 
WHERE printer.price >300 AND printer.model = product.model 
	
--Завд 4
IF   
(SELECT COUNT(*) FROM classes WHERE country LIKE 'USA' ) > 0
SELECT country,c.class 
FROM ships AS s,classes AS c
WHERE s.class = c.class AND c.country = 'USA'  
ELSE SELECT class,country FROM classes ; 
	
--Завд 5
SELECT maker FROM product,pc
WHERE pc.speed >= 750 AND pc.model = product.model
	
	
--Завд 6
SELECT ship,battle,
CASE result
	WHEN 'OK' THEN 'Впорядку'
	WHEN 'sunk' THEN  'Затонув'
	WHEN 'damaged' THEN 'Пошкоджено'
END AS result
FROM outcomes
	
--Завд 7
SELECT maker,price FROM printer ,product
WHERE  price < 300 AND printer.model = product.model 
												
--Завд 8										
SELECT maker, MAX(pc.price) AS max_price
FROM product, pc
WHERE product.model = pc.model
GROUP BY product.maker 


--Завд 9
SELECT point, date, SUM(sum_out), SUM(sum_inc)
FROM
(SELECT point, date, SUM(inc) as sum_inc, null AS sum_out 
FROM Income 
GROUP BY point, date
UNION
SELECT point, date, null as sum_inc, SUM(out) AS sum_out 
FROM Outcome 
GROUP BY point, date ) as T
GROUP BY point, 
DATE ORDER BY point


--Завд 10
SELECT  product.model,MAX(pc.price) AS max_price,product.type
FROM product,pc
WHERE product.model = pc.model
GROUP BY product.model,product.type
UNION 
SELECT product.model,MAX(laptop.price),product.type
FROM product,laptop
WHERE laptop.model = product.model
GROUP BY product.model,product.type
UNION 
SELECT product.model,MAX(printer.price) , product.type
FROM product,printer
WHERE product.model = printer.model
GROUP BY product.model,product.type