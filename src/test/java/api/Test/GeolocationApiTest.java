package api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import api.utils.BaseTest;

public class GeolocationApiTest extends BaseTest {
    
    private static final String API_KEY = "AIzaSyCXsk_n3oPS0a-8iVe_SsqeeyTBaZAW6Qw"; 
  
    @Test
    public void testGeolocationApi_Positive() {
        ExtentTest test = extent.createTest("Positive Test for Geolocation API");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": 310,\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(200)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_Negative() {
        ExtentTest test = extent.createTest("Negative Test for Geolocation API");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": \"invalid\",\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(400)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_EdgeCases() {
        ExtentTest test = extent.createTest("Edge Cases Test for Geolocation API");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": 999,\n" +
                "  \"homeMobileNetworkCode\": 999,\n" +
                "  \"radioType\": \"\",\n" +
                "  \"carrier\": \"\",\n" +
                "  \"considerIp\": false\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(200)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_MissingApiKey() {
        ExtentTest test = extent.createTest("Test with Missing API Key");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": 310,\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(403)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 403);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_InvalidApiKey() {
        ExtentTest test = extent.createTest("Test with Invalid API Key");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": 310,\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", "INVALID_API_KEY")
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(403)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 403);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_MissingRequiredFields() {
        ExtentTest test = extent.createTest("Test with Missing Required Fields");

        String requestBody = "{\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(400)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_InvalidDataTypes() {
        ExtentTest test = extent.createTest("Test with Invalid Data Types");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": \"invalid\",\n" +
                "  \"homeMobileNetworkCode\": \"invalid\",\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(400)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_EmptyRequestBody() {
        ExtentTest test = extent.createTest("Test with Empty Request Body");

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body("{}")
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(400)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_LargePayload() {
        ExtentTest test = extent.createTest("Test with Large Payload");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": 310,\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\",\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true,\n" +
                "  \"extraField1\": \"extraValue1\",\n" +
                "  \"extraField2\": \"extraValue2\",\n" +
                "  \"extraField3\": \"extraValue3\",\n" +
                "  \"extraField4\": \"extraValue4\",\n" +
                "  \"extraField5\": \"extraValue5\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(200)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        test.info("Response: " + response.asString());
    }

    @Test
    public void testGeolocationApi_InvalidJsonFormat() {
        ExtentTest test = extent.createTest("Test with Invalid JSON Format");

        String requestBody = "{\n" +
                "  \"homeMobileCountryCode\": 310,\n" +
                "  \"homeMobileNetworkCode\": 410,\n" +
                "  \"radioType\": \"gsm\"\n" +
                "  \"carrier\": \"Vodafone\",\n" +
                "  \"considerIp\": true\n";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .body(requestBody)
                .when()
                .post("/geolocation/v1/geolocate")
                .then()
                .statusCode(400)
                .extract()
                .response();

        test.pass("API responded with status code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);

        test.info("Response: " + response.asString());
    }

    
    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}