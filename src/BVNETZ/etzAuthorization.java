package BVNETZ;


import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payLoad;
import googlePlace.Reuseable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class etzAuthorization {
	
	
	
	@Test
	public void authorize() {
		
	RestAssured.baseURI = "https://demo.etranzact.com";	
		
	String response =	given().auth().basic("IMTO_CLINENT", "secret").header("Content-Type","application/x-www-form-urlencoded").log().all()
		.formParam("username", "imto_user")
		.formParam("password", "secret")
		.formParam("grant_type", "password")
		.when().post("/auth-server/oauth/token").then().assertThat().statusCode(200).extract().response().asString();
		
	
	
	
	JsonPath JP = Reuseable.rawToJson(response);
	String token = JP.getString("access_token");
	
	System.out.println(token);
	
		//Verify Single BVN
	
		RestAssured.baseURI = "https://www.etranzactng.net";
		
		String VS = given().log().all().auth().oauth2(token).header("Content-Type","application/json")
		.header("ClientId","0001")
		.header("ProductCode","0001")
		.body(payLoad.VerifySingleBvn())
		.when().post("/bvn/api/v1/VerifySingleBVN")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	
	
	//Verify Multiple BVN
		
		given().log().all().auth().oauth2(token).headers("Content-Type","application/json","ClientId","0001","ProductCode","0001")
		.body(payLoad.VerifyMultipleBvn())
		.when().post("/bvn/api/v1/VerifyMultipleBVN")
		.then().log().all().assertThat().statusCode(200);
		
		
		JsonPath vs = Reuseable.rawToJson(VS);
		String ResponseCode = vs.getString("data.ResponseCode");
		
		Assert.assertEquals(ResponseCode,"00");
		
		
		
		
		
	}

}
