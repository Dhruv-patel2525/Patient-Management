import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {
    @BeforeAll
    static void setup()
    {
        RestAssured.baseURI="http://localhost:4004";
    }
    @Test
    public void shouldReturnPatientsWithValidToken()
    {
        String loginPayload= """
               {
                    "email":"testuser@test.com",
                    "password":"password123"
               }
        """;
        String token=given()   //Arrange
                .log().all()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login") //act
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("token");
        System.out.println("Generated token "+token);

        Response response = given()
            .header("Authorization","Bearer "+token)
            .when()
            .get("/api/patients/");

        System.out.println("Response status: " + response.statusCode());
        System.out.println("Response body: " + response.asString());


        response
            .then()
            .statusCode(200)
            .body("patients",notNullValue());

    }
}
