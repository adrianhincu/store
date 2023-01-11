# STORE MANAGEMENT API

## Project Description

The purpose of this demo project is to illustrate the basic CRUD operations and basic authentication

## Technologies used

- Java 17
- Spring Boot 3.0.0
- Maven
- H2 Database
- OpenApi

## Build project

Building, packaging and compiling is done by using Maven tool.

1. Install Eclipse/IDEA IntelliJ
2. Install Maven tool from https://maven.apache.org/download.cgi
3. Install OpenJDK 17 from https://openjdk.org/, Oracle or whatever suits to obtain OpenJDK17
4. Under the root directory , executes in a terminal the following command to build locally the
   project, or use IDEA to build it

  ```
   mvn clean install -DskipTests
   ```

## Testing the application

You can run the tests for this project by going to the root of the project and execute

 ```
   mvn test
   ```

### Start the application

You can start the application directly from your IDE,
or after you build the project go to generated target folder and run

 ```
   java -jar store-0.0.1-SNAPSHOT.jar
   ```

The Application will start on port `8080`

## Security

Basic authentication is required in order to access the API.

- There are 2 roles configured in the application `USER` and `ADMIN`
    - Users with `ADMIN` role have the authority to create/delete categories or products , change the price of a
      product and select products and categories
    - Users with `USER` role can select a products or a list of products

## Functionalities

In summary the application can:

### GET

- Retrieve a paginated list of products
- Retrieve a paginated list of categories with subcategories if present
- Retrieve a product by id
- Retrieve a category by id with subcategories if present

### POST

- Add category with subcategories
- Add a product to a category

### PUT

- Change the price of a product

### DELETE

- Delete a category ( all subcategories will be deleted along with the products)
- Delete a product by id

## Documentation

To view the API Swagger documentation start the application and go to :

- http://localhost:8080/store/api/swagger-ui/index.html
- http://localhost:8080/store/api/v3/api-docs




