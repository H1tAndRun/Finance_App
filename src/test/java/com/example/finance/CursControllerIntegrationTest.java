package com.example.finance;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.math.BigDecimal;
import java.util.Map;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() {
        RestAssured.baseURI = "http://localhost:" + port;
    }


    @Test
    public void convertRublesToCurrency() {
        Map<String, BigDecimal> actual =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/curs?sum=10000&date=01.01.2012")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body()
                        .as(new TypeRef<>() {
                        });
        Assertions.assertEquals(2, actual.size());
    }
}
