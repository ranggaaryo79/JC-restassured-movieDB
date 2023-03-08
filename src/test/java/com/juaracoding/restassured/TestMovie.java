package com.juaracoding.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestMovie {
    String API = "api_key=da74bf72afb731584393933c921295d4";
    String ApiV4 = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYTc0YmY3MmFmYjczMTU4NDM5MzkzM2M5MjEyOTVkNCIsInN1YiI6IjY0MDcyZDhiOTcxNWFlMDBkOGY1YjE1ZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.D5vpMNzmWZZQfK5JKU32ij-YRVuMo1VybJILwSAgaYk";
    String endpointNowPlay = "https://api.themoviedb.org/3/movie/now_playing?" + API + "&language=en-US&page=1";
    String endpointPopular = "https://api.themoviedb.org/3/movie/popular?" + API + "&language=en-US&page=1";

//
    @Test
    public void testGetMovieNowPlaying() {
        Response response = RestAssured.get(endpointNowPlay);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetMovieNowPopular() {
        Response response = RestAssured.get(endpointPopular);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testPostRate() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("value", 8.5);
        System.out.println(requestBody.toJSONString());
        given()
                .header("Authorization", ApiV4)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post("https://api.themoviedb.org/3/movie/631842/rating")
                .then()
                .statusCode(201)
                .body("status_code", equalTo(12))
                .log().body();
    }
}

