package test;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Hw1 {


    @Before
    public void setUp(){ RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        //her fail verildiğinde loglama işlemi yaptırır.
    }
    @Test
    public void questionOne () {
        //https://restcountries.com/v3.1/name/turkey
//        Response response = RestAssured.given().contentType(ContentType.ANY).pathParam("country", "turkey").get("https://restcountries.com/v3.1/name/{country}");
//        response.print();
//        String body = response.body().asString();
//
//        Assert.assertTrue(body.contains("Ankara"));
// diğer çözüm
        Response response =RestAssured.given().contentType(ContentType.ANY).get("https://restcountries.com/v3.1/name/turkey");
        String expectedResponse="[{\"name\":{\"common\":\"Turkey\",\"official\":\"Republic of Turkey\",\"nativeName\":{\"tur\":{\"official\":\"Türkiye Cumhuriyeti\",\"common\":\"Türkiye\"}}},\"tld\":[\".tr\"],\"cca2\":\"TR\",\"ccn3\":\"792\",\"cca3\":\"TUR\",\"cioc\":\"TUR\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"TRY\":{\"name\":\"Turkish lira\",\"symbol\":\"₺\"}},\"idd\":{\"root\":\"+9\",\"suffixes\":[\"0\"]},\"capital\":[\"Ankara\"],\"altSpellings\":[\"TR\",\"Turkiye\",\"Republic of Turkey\",\"Türkiye Cumhuriyeti\"],\"region\":\"Asia\",\"subregion\":\"Western Asia\",\"languages\":{\"tur\":\"Turkish\"},\"translations\":{\"ara\":{\"official\":\"الجمهورية التركية\",\"common\":\"تركيا\"},\"bre\":{\"official\":\"Republik Turkia\",\"common\":\"Turkia\"},\"ces\":{\"official\":\"Turecká republika\",\"common\":\"Turecko\"},\"cym\":{\"official\":\"Republic of Turkey\",\"common\":\"Turkey\"},\"deu\":{\"official\":\"Republik Türkei\",\"common\":\"Türkei\"},\"est\":{\"official\":\"Türgi Vabariik\",\"common\":\"Türgi\"},\"fin\":{\"official\":\"Turkin tasavalta\",\"common\":\"Turkki\"},\"fra\":{\"official\":\"République de Turquie\",\"common\":\"Turquie\"},\"hrv\":{\"official\":\"Republika Turska\",\"common\":\"Turska\"},\"hun\":{\"official\":\"Török Köztársaság\",\"common\":\"Törökország\"},\"ita\":{\"official\":\"Repubblica di Turchia\",\"common\":\"Turchia\"},\"jpn\":{\"official\":\"トルコ共和国\",\"common\":\"トルコ\"},\"kor\":{\"official\":\"터키 공화국\",\"common\":\"터키\"},\"nld\":{\"official\":\"Republiek Turkije\",\"common\":\"Turkije\"},\"per\":{\"official\":\"جمهوری ترکیه\",\"common\":\"ترکیه\"},\"pol\":{\"official\":\"Republika Turcji\",\"common\":\"Turcja\"},\"por\":{\"official\":\"República da Turquia\",\"common\":\"Turquia\"},\"rus\":{\"official\":\"Республика Турции\",\"common\":\"Турция\"},\"slk\":{\"official\":\"Turecká republika\",\"common\":\"Turecko\"},\"spa\":{\"official\":\"República de Turquía\",\"common\":\"Turquía\"},\"srp\":{\"official\":\"Турска Република\",\"common\":\"Турска\"},\"swe\":{\"official\":\"Republiken Turkiet\",\"common\":\"Turkiet\"},\"tur\":{\"official\":\"Türkiye Cumhuriyeti\",\"common\":\"Türkiye\"},\"urd\":{\"official\":\"جمہوریہ ترکی\",\"common\":\"ترکی\"},\"zho\":{\"official\":\"土耳其共和国\",\"common\":\"土耳其\"}},\"latlng\":[39.0,35.0],\"landlocked\":false,\"borders\":[\"ARM\",\"AZE\",\"BGR\",\"GEO\",\"GRC\",\"IRN\",\"IRQ\",\"SYR\"],\"area\":783562.0,\"demonyms\":{\"eng\":{\"f\":\"Turkish\",\"m\":\"Turkish\"},\"fra\":{\"f\":\"Turque\",\"m\":\"Turc\"}},\"flag\":\"\\uD83C\\uDDF9\\uD83C\\uDDF7\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/dXFFraiUDfcB6Quk6\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/174737\"},\"population\":84339067,\"gini\":{\"2019\":41.9},\"fifa\":\"TUR\",\"car\":{\"signs\":[\"TR\"],\"side\":\"right\"},\"timezones\":[\"UTC+03:00\"],\"continents\":[\"Asia\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/tr.png\",\"svg\":\"https://flagcdn.com/tr.svg\",\"alt\":\"The flag of Turkey has a red field bearing a large fly-side facing white crescent and a smaller five-pointed white star placed just outside the crescent opening. The white crescent and star are offset slightly towards the hoist side of center.\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/tr.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/tr.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[39.93,32.87]},\"postalCode\":{\"format\":\"#####\",\"regex\":\"^(\\\\d{5})$\"}}]";
        Assert.assertEquals(expectedResponse, response.body().print());


    }
    @Test
    public void questionTwo(){
    //  https://restful-booker.herokuapp.com/booking/175
        Response response = RestAssured.given().contentType(ContentType.ANY).pathParam("id", 175).get("https://restful-booker.herokuapp.com/booking/{id}");
        response.prettyPrint();
        String body =response.body().asString();

        //Assert.assertFalse(body.contains(""));
        //Assert.assertTrue();
        Assert.assertTrue(body.contains("Smith"));
        }

    @Test
    public void questionTree(){
        //http://www.boredapi.com/api/activity?key=9324336
        Response response =RestAssured.given().contentType(ContentType.ANY).queryParam("key","9324336").get("http://www.boredapi.com/api/activity");
        response.print();
        String body = response.body().asString();

        Assert.assertTrue(body.contains("Clean out your refrigerator"));

    }

    @Test
    public void makeBasicGetRequestAndAssert(){
        /**
         * base url = "https://restcountries.com/v3.1/name";
         * use GET method
         * use /turkey endpoint
         * assert status code is 200 and content-type is application/json
         * get response "date" from headers
         */

        // make request
        RestAssured.baseURI = "https://restcountries.com/v3.1/name";

        Response response = RestAssured.get("turkey");//burada baseURI ile çalıştığımızda sadece end pointi yazarak kullanabiliriz
        response.prettyPrint();

        // assert

        assertEquals(200, response.statusCode());//status code kont
        assertEquals("application/json", response.contentType());//content type kont

        boolean doesDateExist = response.getHeaders().hasHeaderWithName("Date");//has header with name metodu içinde date var mı diye baktı

        if(doesDateExist) {
            String date = response.getHeader("Date");
            assertTrue(date.contains("2023"));//eğer date true dönerse çalışacak
        }
    }
    @Test
    public void randomNumberTest(){
        /**
         * url = "http://numbersapi.com/random/math";
         * use GET method
         * assert status code is 200 and content-type is "text/plain; charset=utf-8"
         * assert values of headers' "date" and "pragma"
         */
        // request random number
        Response response = RestAssured.get("http://numbersapi.com/random/math");
        // verify status code
        assertEquals(200, response.statusCode());
        // verify content type
        assertEquals("text/plain; charset=utf-8", response.getContentType());
        // verify headers' values
        assertTrue(response.getHeader("date").contains("2023"));
        assertEquals("no-cache", response.getHeader("pragma"));
    }

    @Test
    public  void apiapiLimitCounterDecreasesWhenARequestIsMade(){

        /**
         * url = "http://dummy.restapiexample.com/api/v1/employees";
         * use GET method
         * assert "X-RateLimit-Limit" is 60
         * assert "X-RateLimit-Remaining" updates in next request
         */


        //make call
        String url ="http://dummy.restapiexample.com/api/v1/employees";
        Response response=RestAssured.get(url);

        assertEquals("60",response.getHeader("X-RateLimit-Limit"));
        int initialLimit = Integer.parseInt(response.getHeader("X-RateLimit-Remaining"));

        //make second call and assert
        String updatedLimit=RestAssured.get(url).getHeader("X-RateLimit-Remaining");

        assertEquals((initialLimit-1),Integer.parseInt(updatedLimit));

        System.out.println("initialLimit = " + initialLimit);
        System.out.println("updatedLimit = " + updatedLimit);

    }
    @Test

    public void turkeyHasBordersWithEightCountries(){

        /**
         * base url = "https://restcountries.com/v3.1";
         * endpoint "/name/turkey" (use path param in two ways)
         * use GET method
         * assert status code is 200 and border countries are true -> "ARM", "AZE", "BGR", "GEO", "GRC", "IRN", "IRQ", "SYR"
         */

        String country = "turkey";

        // 1. using variable
//        Response response = RestAssured.get("https://restcountries.com/v3.1/name/" + country);
        // 2. using Restassured library
        Response response =RestAssured.given().pathParam("countryName","turkey").get("https://restcountries.com/v3.1/name/{countryName}");

        //assert
        assertEquals(200,response.statusCode());
        String responseBody=response.asString();
        assertTrue(responseBody.contains("ARM"));
        assertTrue(responseBody.contains("AZE"));
        assertTrue(responseBody.contains("BGR"));
        assertTrue(responseBody.contains("GEO"));
        assertTrue(responseBody.contains("GRC"));

    }

    @Test
    public void useRestAssuredPathMethodToReadData(){
        /**
         * base url: https://jsonplaceholder.typicode.com
         * endpoint /comments/90 method: GET
         * make several assertions
         */

        // make call
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = RestAssured.given().pathParam("comment_id", "90")
                .get("comments/{comment_id}");

        // print
        response.prettyPrint();

        // assert
        assertEquals(200, response.statusCode());
        System.out.println("response.path(name) = " + response.path("name")); //gpath çok önemli istediğimiz elemanı yazdırabiliriz


        System.out.println((int) response.path("postId"));
        System.out.println((int) response.path("id"));
        System.out.println((String) response.path("body"));

    }
    @Test
    public void usePathMethodForComplexJsonStructure(){
        /**
         * base url: https://jsonplaceholder.typicode.com
         * endpoint /users/8 method: GET
         * assert companyName: "Abernathy Group" and longtitude: "-120.7677"
         */

        // make call
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users/";

        Response response = RestAssured.given().pathParam("user_id", "8")
                .get("{user_id}");

        // print
        response.prettyPrint();

        // assert
        assertEquals("Abernathy Group", response.path("company.name"));//iç içe jsonu aldık
        assertEquals("-120.7677", response.path("address.geo.lng"));
    }

    @Test
    public void usePathMethodForArrayJson() {//burada list içinde bulunan jsonu aldık . koyarak bir altına indik .
        /**
         * base url: https://jsonplaceholder.typicode.com
         * endpoint /todos method: GET
         * assert _todo with title:"et porro tempora" and completed:true
         */

        // make call
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured.get("todos");

        // assert
        System.out.println(response.path("[4].id").toString());
        assertEquals(4, (int) response.path("[3].id"));
        assertEquals(1, (int) response.path("[3].userId"));
        assertEquals(true,(boolean) response.path("[3].completed"));
    }
    @Test
    public void useGherkinMethodsAndBuiltInAssertion() {
        /**
         * base url: https://restcountries.com/v3.1/name
         * endpoint /turkey method: GET
         */

        RestAssured.given().pathParam("country", "turkey")//burada given and when sırasıyla kullandık.Bu sıra önemli hata verebilir
                .and().baseUri("https://restcountries.com/v3.1")//sonrasında thenle birlikte assert that kullandık.
                .when().contentType("application/json")
                .and().get("name/{country}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().header("Server", "Apache/2.4.38 (Debian)");
    }

    @Test
    public void useMatchersLibraryForAssertions(){
        /**
         * base url: https://restcountries.com/v3.1/name
         * endpoint /turkey method: GET//burada matchersları kullandık
         */

        RestAssured.given().pathParam("country", "turkey")
                .and().baseUri("https://restcountries.com/v3.1")
                .when().contentType("application/json")
                .and().get("name/{country}")
                .then().assertThat().statusCode(Matchers.instanceOf(Integer.class))//gelen değer ınteger olsun
                .and().assertThat().statusCode(Matchers.greaterThan(199))//gelen değer 199 dan büyük olmasınn
                .and().assertThat().statusCode(Matchers.lessThan(300))//gelen değer 300 den küçük olsun
                .and().assertThat().statusCode(Matchers.anyOf(Matchers.equalTo(200), Matchers.equalTo(201)))//200 e veya 201 e eşit olsun
                .and().assertThat().contentType(Matchers.is("application/json"))//content type buna eşit olsun
                .and().assertThat().contentType(Matchers.instanceOf(String.class))
                .and().assertThat().contentType(Matchers.notNullValue())//content type boş olmasın
                .and().assertThat().contentType(Matchers.not(Matchers.is("text")))//contenttype text olmasın
                .and().assertThat().header("Server", Matchers.not(Matchers.emptyOrNullString()))//server headarı boş veya boş string olmasın
                .and().assertThat().header("Server", Matchers.containsString("Debian"));//server headarı debian içersin
    }
    @Test
    public void useRestAssuredLogging() {//burada verilen degerleri loglama işlemi yapılıyor yani yapılan işlem doğru mu yazdırıyoruz

        RestAssured.given().pathParam("country", "turkey").log().uri()//
                .and().baseUri("https://restcountries.com/v3.1")
                .when().contentType("application/json").log().body().log().method()
                .and().get("name/{country}")
                .then().log().ifError();




    }
    @Test
    public void setupLoggingAtBefore(){//burada herhangi bir log işlemi yapmamıza rağmen before da yazılan  log metodu fail olduğunda loglama yapar
        //bu işlem bende çalışmadı sor


        RestAssured
                .given().pathParam("country", "yozgat")
                .and().baseUri("https://restcountries.com/v3.1")
                .when().contentType("application/json")
                .and().get("name/{country}")
                .then().assertThat().contentType("application/json")
                .and().assertThat().statusCode(200)
                .and().assertThat().header("Server", "Apache/2.4.38 (Debian)");
    }

    // Serialization and Deserialization

    @Test
    public void deserializeToMap(){//response u map in içine atma
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://randomuser.me/api")
                .then().assertThat().statusCode(200).extract().response();

        Map<String, Object> deserializedObject = response.body().as(Map.class);
        System.out.println(deserializedObject.toString());

    }

    @Test
    public void deserializeToList(){ //responsu list e atma
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://restcountries.com/v3.1/name/turkey")
                .then().assertThat().statusCode(200).extract().response();

        List<String> borderCountries = response.path("[0].borders");
        System.out.println(borderCountries.toString());
        System.out.println("borderCountries.get(1) = " + borderCountries.get(1));
        response.prettyPrint();

    }






}
