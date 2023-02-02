package com.example.finance;

import com.example.finance.dto.OperationAddDtoRq;
import com.example.finance.dto.OperationAddDtoRs;
import com.example.finance.dto.OperationTransferDtoRq;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BalanceControllerIntegrationTest {

    @LocalServerPort
    private int port;


    @BeforeEach
    public void init() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    @Sql("/script/BalanceControllerTest/getMoneyByNumberBalance/Before.sql")
    @Sql(scripts = "/script/BalanceControllerTest/getMoneyByNumberBalance/After.sql"
            , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Проверка получения денег на счету по номеру баланса")
    public void getMoneyBalanceByNumberTest() {
        BigDecimal actual = given()
                .accept(ContentType.JSON)
                .when()
                .get("/balance/1234")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .as(new TypeRef<>() {
                });
        Assertions.assertEquals(new BigDecimal("10000.00"), actual);
    }

    @Test
    @Sql("/script/BalanceControllerTest/transferFromBalanceToBalance/Before.sql")
    @Sql(scripts = "/script/BalanceControllerTest/transferFromBalanceToBalance/After.sql"
            , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Проверка перевода денег с одного счета на другой")
    public void transferFromBalanceToBalance() {
        String actual = given()
                .contentType(ContentType.JSON)
                .body(OperationTransferDtoRq
                        .builder()
                        .nameFromBalance("1234")
                        .nameToBalance("5566")
                        .sum(new BigDecimal("1000"))
                        .build())
                .when()
                .post("/balance/transfer")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();
        Assertions.assertEquals("Операция прошла успешно", actual);
    }

    @Test
    @Sql("/script/BalanceControllerTest/getOperationByNumberBalance/Before.sql")
    @Sql(scripts = "/script/BalanceControllerTest/getOperationByNumberBalance/After.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Проверка получения операций по номеру баланса")
    public void getOperationByNumberBalanceTest() {
        List<OperationAddDtoRs> actual = given()
                .accept(ContentType.JSON)
                .when()
                .get("/balance/operation/1234")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .as(new TypeRef<>() {
                });
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    @Sql("/script/BalanceControllerTest/addOperationByBalance/Before.sql")
    @Sql(scripts = "/script/BalanceControllerTest/addOperationByBalance/After.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Проверка добавления операций по номеру баланса")
    public void addOperationByBalanceTest() {
        BigDecimal actual = given()
                .contentType(ContentType.JSON)
                .body(OperationAddDtoRq.builder()
                        .nameOperation("перевод")
                        .sumOperation(new BigDecimal("1500"))
                        .numberBalance("1234")
                        .build())
                .when()
                .post("/balance/operation")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(new TypeRef<>() {
                });
        Assertions.assertEquals(new BigDecimal("11500.00"), actual);
    }
}
