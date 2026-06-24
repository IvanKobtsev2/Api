import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest {

    @Test
    public void getUsersTest() {

        Response response =
                given()
                        .when()
                        .get("https://dummyjson.com/users");

        assertThat(response.statusCode()).isEqualTo(200);

        Integer total = response.jsonPath().getInt("total");

        assertThat(total).isGreaterThan(0);
    }

    @Test
    public void getUserByIdTest() {

        Response response =
                given()
                        .when()
                        .get("https://dummyjson.com/users/1");

        assertThat(response.statusCode()).isEqualTo(200);

        Integer id = response.jsonPath().getInt("id");
        String firstName = response.jsonPath().getString("firstName");

        assertThat(id).isEqualTo(1);
        assertThat(firstName).isNotBlank();
    }

    @Test
    public void userNotFoundTest() {

        Response response =
                given()
                        .when()
                        .get("https://dummyjson.com/users/999999");

        assertThat(response.statusCode()).isEqualTo(404);
    }
}