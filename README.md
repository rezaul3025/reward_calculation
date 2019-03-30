# Shop webservice

Tech-stack:

Java 1.8		

Build tool : Maven	

Packaging : jar 

Spring components:
	
Spring boot	

Spring controller for RESTful Web-Service 

Spring data  

Spring web  

Memory database: H2 (Initial data :/shop_web_service/src/main/resources/data.sql)	

Shop frontend (rendering REST service data): 
--------------------------------------------
Tech-stack:

Spring web  

Thymeleaf for templating 

AngularJs 1, Html5, Bootstrap 4 (Responsive)

Please, follow the following instructions to build, test & run the application 

Build & test: 
--------------------------
Go to project folder 'webshop'

$ mvn clean install

Test: 
---------------------------
Go to project folder 'webshop'

$ mvn test

Run: 
----------------------------
Run webservice -> Go to project folder 'webshop/shop_webservice'

$ mvn spring-boot:run

Run frontend -> Open new terminal and go to project folder 'webshop/shop_frontend'

$ mvn spring-boot:run

Access to the application UI : 
------------------------------
http://localhost:7000

Description: 
----------------------------
RESTful Web-Service: 
----------------------------
The RESTful web services are build using spring RestController.
Following are the end points for create category, item and list category, item, 

1. Create category: 
	
	Url: http://localhost:8000/api/v1/category 
	
	Method: POST
	 
	request body : 
	
	{
		"title":"Test category"
	} 
	
	response : 
	
	{
		"id":"3",
		title":"Test category"
	} 

2. Create item: 
	
	Url: http://localhost:8000/api/v1/item
	
	Method: POST
	 
	request body : 
	
	{
		"categoryId":1,
		"title":"Test title",
		"text":"Test text",
		"price":99.98
	} 
	
	response : 
	
	{
		"id":13,
		"title":"Test title",
		"text":"Test text",
		"price":99.98
	} 

3. List category: 
	
	Url : http://localhost:8000/api/v1/categories  

	Method: GET
	
	Example response:  
	
	[{
		"id":1,
		"title":"Category 1",
		"items":[{
					"id":2,
					"title":"Item title2",
					"text":"Item text 2",
					"price":67.98
				}]
	}]

4. List item: 
	
	Url: http://localhost:8000/api/v1/items
	
	Method: GET 
	
	Example response: 
	
	[{
		"id":1,
		"title":"Item title1",
		"text":"Item text 1",
		"price":98.99
	}] 

5. List item by category : 

	Url: http://localhost:8000/api/v1/items/category/3
	
	Method: GET
	 
	response: 
	
	[{
		"id":3,
		"title":"Item title1",
		"text":"Item text 1",
		"price":98.99
	}] 

Shop frontend (rendering REST service data): 
--------------------------------------------
A responsive user interface has been implemented using bootstrap4, html5, AngularJs for rendering above 
RESTful web services. How to run and access UI application has been explained above.

The UI has single page with the side bar and main content area, Categories are 
listed in the side bar and items are listed in the main content area. Below each 
list there are option to crate category & item with the pop dialog. On click of each
category corresponding items will be display.

Java script location: 
----------------------
/shop_frontend/src/main/resources/static

Java script for rendering REST:
-------------------------------
/shop_frontend/src/main/resources/static/shop/js/homeController.js

UI template location: 
---------------------  
/shop_frontend/src/main/resources/templates


