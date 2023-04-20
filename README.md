# Online Library Rest API

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

**Api Credentials:**
- UserName - onlinebookstore
- Password - onlinebookstore


- Git hub url -----
https://github.com/ShajahanSha/OnlineLibrary

**Api Specifications:**
- AddBook to library POST - onlinebookshop/api/v1/books
    - Request - {
        "name": "tyty",
        "description": "test",
        "author": "Shajahan",
        "classification": "comic",
        "price": 300,
        "isbn": "3434"
    }
- Fetch Book GET - onlinebookshop/api/v1/books
- Update book to the library PUT - onlinebookshop/api/v1/books
    - Request - {
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
    - Request - {
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

**Docker Setup:**
- Docker hub url -----
https://hub.docker.com/r/shajahanshaik/online-library-api

- Docker File -------
	FROM openjdk:8-jdk-alpine
	EXPOSE 8080
	ARG JAR_FILE=target/online-library-api-0.0.1-SNAPSHOT.jar
	COPY ${JAR_FILE} app.jar
	ENTRYPOINT ["java","-jar","/app.jar"]


- Building Docker Image ----
docker build --platform linux/amd64 -t online-library-api .

- Run docker container -----
docker run -p 8080:8080 -t online-library-api

- Docker pull image -----
docker pull shajahanshaik/online-library-api:latest


