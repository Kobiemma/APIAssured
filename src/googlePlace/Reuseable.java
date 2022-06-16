package googlePlace;

import io.restassured.path.json.JsonPath;

public class Reuseable {
	
	
	public static JsonPath rawToJson(String ResponseData) {
		
		
		JsonPath JD = new JsonPath(ResponseData);
				
		return JD; 
	}

}
