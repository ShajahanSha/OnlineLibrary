# Online Library Rest API

**Develop Rest API’s for**
 
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

**Git hub url**

    - https://github.com/ShajahanSha/OnlineLibrary

**How to Setup**
    
    -Download the onlinelibrary from https://github.com/ShajahanSha/OnlineLibrary
    -import it as maven project any IDE
    -Run Clean install from 
	    mvn clean install
    -If build is success, run the spring boot java application
		OnlineLibraryApplication.java
		    
**Api Security:**
    
    - Basic Auth 
	    - UserName - onlinebookstore
        - Password - onlinebookstore


**Api Specifications:**

    - AddBook to library POST - http://localhost:8080/onlinelibrary/api/v1/books
        - Request - {
            "name": "The Last Kids on Earth: The Monster Box",
            "description": "The Last Kids on Earth: The Monster Box",
            "author": "Jack Sullivan",
            "classification": "comic",
            "price": 300,
            "isbn": "0451481089"
        }
    - Fetch Book GET - http://localhost:8080/onlinelibrary/api/v1/books
    - Update book to the library PUT - http://localhost:8080/onlinelibrary/api/v1/books
        - Request - {
                      "bookId": 1,
                      "name": "The Last Kids on Earth: The Monster Box",
                      "description": "The Last Kids on Earth: The Monster Box",
                      "author": "Jack Sullivan",
                      "classification": "comic",
                      "price": 300,
                      "isbn": "0451481089"
                  }
    - Delete book from library DELETE - http://localhost:8080/onlinelibrary/api/v1/books/{bookId}
    - CheckOut POST - http://localhost:8080/onlinelibrary/api/v1/checkout/orders
        - Request - {
                      "serviceType": "CHECKOUT",
                      "checkoutCode": "asde",
                      "promotionCode": "PROMO10",
                      "bookCommands": [
                          {
                              "bookId": 1,
                              "name": "The Last Kids on Earth: The Monster Box",
                              "description": "The Last Kids on Earth: The Monster Box",
                              "author": "Jack Sullivan",
                              "classification": "comic",
                              "price": 300,
                              "isbn": "0451481089"
                          }
                      ]
                  }

**Docker Setup:**

    - Docker hub url - https://hub.docker.com/r/shajahanshaik/online-library-api1

    - Docker File -------
            FROM openjdk:8-jdk-alpine
            EXPOSE 8080
            ARG JAR_FILE=target/online-library-api-0.0.1-SNAPSHOT.jar
            COPY ${JAR_FILE} app.jar
            ENTRYPOINT ["java","-jar","/app.jar"]

    - Building Docker Image - docker build --platform linux/amd64 -t online-library-api1 .

    - Run docker container - docker run -p 8080:8080 -t online-library-api1

    - Docker pull image - docker pull shajahanshaik/online-library-api1:latest
    
    - Docker log - docker logs online-library-api1


