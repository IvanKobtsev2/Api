import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    @Test
    public void loginTest() {

        String body = """
                {
                    "username":"emilys",
                    "password":"emilyspass"
                }
                """;

        Response response =
                given()
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post("https://dummyjson.com/auth/login");

        assertThat(response.statusCode()).isEqualTo(200);

        String token = response.jsonPath().getString("accessToken");

        assertThat(token).isNotBlank();
    }
}
