package com.example.store.integration;

import com.example.store.application.payload.ProductResponse;
import com.example.store.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EndToEndTest {

    @LocalServerPort
    private int port;

    @Test
    @Transactional
    void createProductAndThenFindId() {
        var createProductResponse = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).and()
                .body(TestUtils.getLaptopProductRequest())
                .when()
                .post("http://localhost:" + port + "/store/api/product")
                .then()
                .extract();
        Assertions.assertEquals(HttpStatus.CREATED.value(), createProductResponse.response().statusCode());

        var findProduct = RestAssured.given().accept(ContentType.JSON).and()
                .when()
                .get("http://localhost:" + port + "/store/api/product/1")
                .then()
                .extract();

        Assertions.assertEquals(HttpStatus.OK.value(), findProduct.response().statusCode());
    }

}
