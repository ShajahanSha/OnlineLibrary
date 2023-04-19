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

Api specifications:
- AddBook to library POST - onlinebookshop/api/v1/books
    Request - {
        "name": "tyty",
        "description": "test",
        "author": "Shajahan",
        "classification": "comic",
        "price": 300,
        "isbn": "3434"
    }
- Fetch Book GET - onlinebookshop/api/v1/books
- Update book to the library PUT - onlinebookshop/api/v1/books
    Request - {
                  "bookId": 1,
                  "name": "tyty",
                  "description": "test",
                  "author": "Shajahan",
                  "classification": "comic",
                  "price": 300,
                  "isbn": "3434"
              }
- Delete book from library DELETE - onlinebookshop/api/v1/books/{bookId}
- CheckOut POST - onlinebookshop/api/v1/books/checkout
    Request - {
                  "serviceType": "CHECKOUT",
                  "checkoutCode": "asde",
                  "promotionCode": "PROMO10",
                  "bookCommands": [
                      {
                          "bookId": 1,
                          "name": "asde",
                          "description": "test",
                          "author": "Shajahan",
                          "classification": "comic",
                          "price": 200,
                          "isbn": "2242"
                      }
                  ]
              }