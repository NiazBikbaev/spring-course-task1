package com.epam.controller;

import com.epam.domain.Data;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Default comment.
 **/
public class ControllerTest {
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        RestAssured.basePath = "/";

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void postTest() {
        Data data = new Data();
        data.setData("Test");
        given()
                .when()
                .body(data)
                .post("/form")
                .then()
                .statusCode(200);
    }

    @Test
    public void getTest() {
        given()
                .when()
                .get("/form")
                .then()
                .statusCode(200);
    }

    @Test
    public void putTest() {
        Data data = new Data();
        data.setData("Test");
        given()
                .when()
                .body(data)
                .put("/form")
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteTest() {
        given()
                .when()
                .body(new Data())
                .delete("/form")
                .then()
                .statusCode(404);
    }


}
