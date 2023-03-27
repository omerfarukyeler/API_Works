package test;

import booking.Bookingdates;
import booking.GetBookingResponse;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import post_booking_response.PostResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Works2 {

    @Before
    public void setUp() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();//burayı her failde loglamak için kullanırız
    }


    @Test
    public void makeJsonSchemaValidationForBody() {
        /**
         * base url = "http://www.boredapi.com/api/";
         * use GET method
         * use /activity endpoint
         * validate response body
         *
         */

        // manual method
//        Response response = RestAssured
//                .given().baseUri("http://www.boredapi.com/api")
//                .when().accept(ContentType.JSON)
//                .and().get("activity")
//                .then().assertThat().contentType(ContentType.JSON)
//                .and().assertThat().statusCode(200).log().body()
//                .and().assertThat().body("activity", Matchers.instanceOf(String.class))//activity string olsun
//                .and().assertThat().body("type", Matchers.instanceOf(String.class))//type string olsun
//                .and().assertThat().body("participants", Matchers.instanceOf(Integer.class))//participants ınteger olsun
//                .and().assertThat().body("price", Matchers.anyOf(Matchers.instanceOf(Integer.class), Matchers.instanceOf(Float.class)))//price hem ınteger hem float olabilir
//                .extract().response();

        // using JsonValidator (Burada ayrı bir class oluşturduk(activity-schema.json ) ve bunun içinde jsonvalidator kullandık .)

        Response response = RestAssured
                .given().baseUri("http://www.boredapi.com/api")
                .when().accept(ContentType.JSON)
                .and().get("activity")
                .then().assertThat().contentType(ContentType.JSON)
                .and().assertThat().statusCode(200).log().body()
                .and().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("activity-schema.json"))
                .extract().response();
    }
    @Test
    public void anotherJsonSchemaValidation() {  //burada random bi sayı ile activity schemayı kontrol ettik .
        /**
         * base url = "https://jsonplaceholder.typicode.com/comments/90";
         * use GET method
         * use /comments/randomNumber endpoint
         * validate response body
         */


        int num = new Random().nextInt(501);//random sayı oluşturma .

        Response response = RestAssured
                .given().baseUri("https://jsonplaceholder.typicode.com")
                .and().pathParam("randomNumber", num)
                .when().accept(ContentType.JSON)
                .and().get("comments/{randomNumber}")
                .then().assertThat().statusCode(200).log().body()
                .and().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("comments-schema.json"))
                .extract().response();
    }
    @Test
    public void getbooking900() {//burada get metodunu otomatize ettik

        RestAssured
                .given().accept(ContentType.ANY)
                .and().baseUri("https://restful-booker.herokuapp.com")
                .and().log().method().log().uri()
                .and().pathParam("id", "900")
                .when().get("/booking/{id}")
                .then().assertThat().statusCode(200).log().body();
    }

    public Response getbooking(int id ) {

        return RestAssured
                .given().accept(ContentType.ANY)
                .and().baseUri("https://restful-booker.herokuapp.com")
                .and().log().method().log().uri()
                .and().pathParam("bookingId", id)
                .when().get("/booking/{bookingId}")
                .then().assertThat().statusCode(200).log().body()
                .extract().response();
    }

    @Test
    public void test1() {//burada yukarda oluşturduğumuz return lü metodu kullanarak otomatize edildi ve istediğimiz booking çağırıldı.
        Response response = getbooking(13);
        System.out.println("response.path(\"price\") = " + response.path("totalprice"));
    }
    @Test
    public void getABookingAndDeserializeToJavaObj() {
//        Response getbooking = getbooking(45);

        Map<String, Object> javaObject = getbooking(45).body().as(Map.class);//responsu map in içne attık yukardaki get bookin g kullanıldı

        assertEquals("John", javaObject.get("firstname"));
        assertEquals("Smith", javaObject.get("lastname"));
        assertEquals(111, javaObject.get("totalprice"));
        assertEquals(true, javaObject.get("depositpaid"));

        Map<String, String> bookingdatesJavaObject = (Map<String, String>) javaObject.get("bookingdates");//booking date i getirdik
        System.out.println("bookingdatesJavaObject.get(\"checkin\") = " + bookingdatesJavaObject.get("checkin"));


        // how to use Gson bu jsonu objeye,objeyi jsona çevirir
        Gson gson = new Gson();

        String serializedJson = gson.toJson(javaObject);
        System.out.println("serializedJson = " + serializedJson);



    }
    @Test
    public void getBookingAndDeserializetoPOJO() {//getbookingresponsedan çekilip assert yapıldı

        GetBookingResponse pojoResponse = getbooking(156).body().as(GetBookingResponse.class);

        assertEquals(111, pojoResponse.getTotalprice());

        assertEquals("2018-01-01", pojoResponse.getBookingdates().getCheckin());
    }

    @Test
    public void postABookingUsingStringJson() {//asagıdaki postBooking metodunu kullandık yeni bir post oluşturduk

        String randomBookingData = "{\n" +
                "    \"firstname\" : \"test-1\",\n" +
                "    \"lastname\" : \"user\",\n" +
                "    \"totalprice\" : 120,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2022-07-20\",\n" +
                "        \"checkout\" : \"2022-08-05\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = postBooking(randomBookingData);

        PostResponse pojoObj = response.body().as(PostResponse.class);
        System.out.println("pojoObj.getBookingid() = " + pojoObj.getBookingid());
    }

    public Response postBooking(String requestBodyJson) {//post için oluşturuldu bir üstte kullanıldı

        return RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com")
                .and().contentType(ContentType.JSON)
                .and().accept(ContentType.ANY)
                .body(requestBodyJson)
                .when().post("booking")
                .then().statusCode(200).extract().response();
    }

    public Response postBooking(GetBookingResponse pojoForPost) {//207.satırda kullanıldı

        return RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com")
                .and().contentType(ContentType.JSON)
                .and().accept(ContentType.ANY)
                .body(pojoForPost).log().all()
                .when().post("booking")
                .then().statusCode(200)
                .and().log().ifError()
                .extract().response();
    }

    public int postBookingUsingPOJO() {
        // create and fill pojo
        GetBookingResponse postObj = new GetBookingResponse();
        postObj.setAdditionalneeds("Parking Area");
        postObj.setFirstname("Mustafa");
        postObj.setLastname("Yilmaz");

        Bookingdates dateObj = new Bookingdates();
        dateObj.setCheckin("01.05.2023");
        dateObj.setCheckout("2023.04.15");

        postObj.setBookingdates(dateObj);
        postObj.setTotalprice(2000);
        postObj.setDepositpaid(true);

        // make request and print response
        Response response = postBooking(postObj);

        String createdBodyString = response.body().asString();
        System.out.println("createdBodyString = " + createdBodyString);

        return (int )response.path("bookingid");
    }
    public Map<String, String> generateBodyForAuthToken() {//token oluşturuldu
        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", "admin");
        authBody.put("password", "password123");

        return authBody;
    }

    public String getAuthToken() {//token i alıyoruz
        Map<String, String> body = generateBodyForAuthToken();

        Response response = RestAssured
                .given().baseUri("https://restful-booker.herokuapp.com/auth")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(body).log().uri().log().body()
                .when().post()
                .then().statusCode(200)
                .extract().response();

        return (String) response.path("token");
    }

    @Test
    public void getAuthTokenTest() {

        System.out.println(getAuthToken());
    }

    @Test
    public void useAuthAndPutMethod() {//put işlemi yapıldı
        // create token
        String authToken = getAuthToken();

        // create a booking
        int bookingId = postBookingUsingPOJO();

        // update the booking
        GetBookingResponse updatedbody = new GetBookingResponse();
        updatedbody.setAdditionalneeds("Updated Parking Area");
        updatedbody.setFirstname("Updated Mustafa");
        updatedbody.setLastname("Updated Yilmaz");

        Bookingdates dateObj = new Bookingdates();
        dateObj.setCheckin("01.05.2023");
        dateObj.setCheckout("2023.04.15");

        updatedbody.setBookingdates(dateObj);
        updatedbody.setTotalprice(5000);
        updatedbody.setDepositpaid(true);

        Response putResponse = RestAssured.given().contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com")
                .pathParam("id", bookingId)
                .header("Cookie", "token=" + authToken)
                .body(updatedbody)
                .when().put("booking/{id}")
                .then().statusCode(200)
                .extract().response();

        String updatedBodyString = putResponse.body().asString();

        System.out.println("updatedbody = " + updatedBodyString);

        // assert that booking is updated

        Response response = getbooking(bookingId);

        GetBookingResponse pojoObj = response.body().as(GetBookingResponse.class);

        assertEquals("Updated Mustafa", pojoObj.getFirstname());
        assertEquals(5000, pojoObj.getTotalprice());
        assertEquals("Updated Parking Area", pojoObj.getAdditionalneeds());

    }









}
