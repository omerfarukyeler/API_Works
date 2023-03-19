package test;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Hw1 {


    @Before
    public void setUp(){


    }

    @Test
    public void questionOne () {
        //https://restcountries.com/v3.1/name/turkey
        Response response = RestAssured.given().contentType(ContentType.ANY).pathParam("country", "turkey").get("https://restcountries.com/v3.1/name/{country}");
        response.prettyPrint();
        String body = response.body().asString();

        Assert.assertTrue(body.contains("Ankara"));
    }
    @Test
    public void questionTwo(){
    //  https://restful-booker.herokuapp.com/booking/175
        Response response = RestAssured.given().contentType(ContentType.ANY).pathParam("id", 175).get("https://restful-booker.herokuapp.com/booking/{id}");
        response.prettyPrint();
        String body =response.body().asString();

        Assert.assertTrue(body.contains("Smith"));
    }

    @Test
    public void questionTree(){
        //http://www.boredapi.com/api/activity?key=9324336
        Response response =RestAssured.given().contentType(ContentType.ANY).queryParam("key","9324336").get("http://www.boredapi.com/api/activity");
        response.prettyPrint();
        String body = response.body().asString();

        Assert.assertTrue(body.contains("Clean out your refrigerator"));







    }


}
