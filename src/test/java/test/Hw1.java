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
        response.print();
        String body = response.body().asString();

        Assert.assertTrue(body.contains("Ankara"));
// diğer çözüm
//        Response response =RestAssured.given().contentType(ContentType.ANY).get("https://restcountries.com/v3.1/name/turkey");
//        String expectedResponse="[{\"name\":{\"common\":\"Turkey\",\"official\":\"Republic of Turkey\",\"nativeName\":{\"tur\":{\"official\":\"Türkiye Cumhuriyeti\",\"common\":\"Türkiye\"}}},\"tld\":[\".tr\"],\"cca2\":\"TR\",\"ccn3\":\"792\",\"cca3\":\"TUR\",\"cioc\":\"TUR\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"TRY\":{\"name\":\"Turkish lira\",\"symbol\":\"₺\"}},\"idd\":{\"root\":\"+9\",\"suffixes\":[\"0\"]},\"capital\":[\"Ankara\"],\"altSpellings\":[\"TR\",\"Turkiye\",\"Republic of Turkey\",\"Türkiye Cumhuriyeti\"],\"region\":\"Asia\",\"subregion\":\"Western Asia\",\"languages\":{\"tur\":\"Turkish\"},\"translations\":{\"ara\":{\"official\":\"الجمهورية التركية\",\"common\":\"تركيا\"},\"bre\":{\"official\":\"Republik Turkia\",\"common\":\"Turkia\"},\"ces\":{\"official\":\"Turecká republika\",\"common\":\"Turecko\"},\"cym\":{\"official\":\"Republic of Turkey\",\"common\":\"Turkey\"},\"deu\":{\"official\":\"Republik Türkei\",\"common\":\"Türkei\"},\"est\":{\"official\":\"Türgi Vabariik\",\"common\":\"Türgi\"},\"fin\":{\"official\":\"Turkin tasavalta\",\"common\":\"Turkki\"},\"fra\":{\"official\":\"République de Turquie\",\"common\":\"Turquie\"},\"hrv\":{\"official\":\"Republika Turska\",\"common\":\"Turska\"},\"hun\":{\"official\":\"Török Köztársaság\",\"common\":\"Törökország\"},\"ita\":{\"official\":\"Repubblica di Turchia\",\"common\":\"Turchia\"},\"jpn\":{\"official\":\"トルコ共和国\",\"common\":\"トルコ\"},\"kor\":{\"official\":\"터키 공화국\",\"common\":\"터키\"},\"nld\":{\"official\":\"Republiek Turkije\",\"common\":\"Turkije\"},\"per\":{\"official\":\"جمهوری ترکیه\",\"common\":\"ترکیه\"},\"pol\":{\"official\":\"Republika Turcji\",\"common\":\"Turcja\"},\"por\":{\"official\":\"República da Turquia\",\"common\":\"Turquia\"},\"rus\":{\"official\":\"Республика Турции\",\"common\":\"Турция\"},\"slk\":{\"official\":\"Turecká republika\",\"common\":\"Turecko\"},\"spa\":{\"official\":\"República de Turquía\",\"common\":\"Turquía\"},\"srp\":{\"official\":\"Турска Република\",\"common\":\"Турска\"},\"swe\":{\"official\":\"Republiken Turkiet\",\"common\":\"Turkiet\"},\"tur\":{\"official\":\"Türkiye Cumhuriyeti\",\"common\":\"Türkiye\"},\"urd\":{\"official\":\"جمہوریہ ترکی\",\"common\":\"ترکی\"},\"zho\":{\"official\":\"土耳其共和国\",\"common\":\"土耳其\"}},\"latlng\":[39.0,35.0],\"landlocked\":false,\"borders\":[\"ARM\",\"AZE\",\"BGR\",\"GEO\",\"GRC\",\"IRN\",\"IRQ\",\"SYR\"],\"area\":783562.0,\"demonyms\":{\"eng\":{\"f\":\"Turkish\",\"m\":\"Turkish\"},\"fra\":{\"f\":\"Turque\",\"m\":\"Turc\"}},\"flag\":\"\\uD83C\\uDDF9\\uD83C\\uDDF7\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/dXFFraiUDfcB6Quk6\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/174737\"},\"population\":84339067,\"gini\":{\"2019\":41.9},\"fifa\":\"TUR\",\"car\":{\"signs\":[\"TR\"],\"side\":\"right\"},\"timezones\":[\"UTC+03:00\"],\"continents\":[\"Asia\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/tr.png\",\"svg\":\"https://flagcdn.com/tr.svg\",\"alt\":\"The flag of Turkey has a red field bearing a large fly-side facing white crescent and a smaller five-pointed white star placed just outside the crescent opening. The white crescent and star are offset slightly towards the hoist side of center.\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/tr.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/tr.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[39.93,32.87]},\"postalCode\":{\"format\":\"#####\",\"regex\":\"^(\\\\d{5})$\"}}]";
//        Assert.assertEquals(expectedResponse, response.body().print());


    }
    @Test
    public void questionTwo(){
    //  https://restful-booker.herokuapp.com/booking/175
        Response response = RestAssured.given().contentType(ContentType.ANY).pathParam("id", 175).get("https://restful-booker.herokuapp.com/booking/{id}");
        response.prettyPrint();
        String body =response.body().asString();

        //Assert.assertTrue(body.contains("Smith"));
        Assert.assertTrue(body.contains("Josh"));




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
