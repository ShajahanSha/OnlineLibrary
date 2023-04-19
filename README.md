# Online Book Store Rest API

Develop Rest API’s for – 
- CRUD operations on Books. 
- Checkout operation for single or multiple books which will return the total payable amount.
	
Considerations: 
- You can use either Spring Boot or Quarkus 
- Book object should have the following attributes: 
	- Name 
	- Description 
	- Author 
	- Type/Classification 
	- Price ISBN 
- Checkout method will take list of books as parameters plus optional promotion code and return total price after discount (if applicable). 
- Promotion/Discounts is variant according to book type/classification, ex: fiction books may have 10% discount while comic books have 0% discount.	

