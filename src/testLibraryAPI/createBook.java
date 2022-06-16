package testLibraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payLoad;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class createBook {
	
	
	
	@Test(dataProvider="BookData")
	
	public void AddBook(String isbn,String aisle) {
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		
	String addBookResponse =	given().header("Content-Type","application/json")
		.body(payLoad.addBookRequest(isbn,aisle))
		.when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(addBookResponse);
		
		
	}
	
	
	@DataProvider(name = "BookData")
	
	public Object[][] getData() {
		
		
		return new Object[][] {{"obi","123"},{"Ada","456"},{"Red","098"},{"Blue","567"}} ;
		
		
		
	}

}
