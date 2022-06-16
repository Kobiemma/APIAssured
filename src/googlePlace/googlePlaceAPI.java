package googlePlace;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;


public class googlePlaceAPI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//Give -All input API
		//When -Submit details
		//Then -Validate Response
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		//POST
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(/*payLoad.AddPlace()*/new String(Files.readAllBytes(Paths.get("c:")))).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
	System.out.println(Response);
	
	JsonPath JP = Reuseable.rawToJson(Response);
	String placeID = JP.get("place_id");
	
	System.out.println(placeID);
	
	
	
	//GET
	
		String responseGet = given().log().all().queryParams("place_id", placeID,"key", "qaclick123").when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(responseGet);
	
	
	//PUT
	
	String responseUpdate = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
	body("{\r\n"
			+ "\"place_id\":\""+placeID+"\",\r\n"
			+ "\"address\":\"70 winter walk, USA\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
	
	JsonPath J1 = Reuseable.rawToJson(responseUpdate);
	String message = J1.getString("msg");
	System.out.println(message);
	
	Assert.assertEquals(message, "Address successfully updated");
	}

}
