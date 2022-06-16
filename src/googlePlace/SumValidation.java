package googlePlace;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	
	@Test
	public void getAllCoursePrices() {
		
		
		JsonPath JD = new JsonPath(payLoad.CoursePrice());
		
		int courseSize = JD.getInt("courses.size()");
		
		int sum =0;
		
		for(int i = 0; i<courseSize;i++) {
			
		 int price = JD.getInt("courses["+i+"].price");
		 
		 int copies = JD.getInt("courses["+i+"].copies");
		 
		 int amount = copies * price;	
		 
		 sum = sum + amount;
		 
		 System.out.println(sum);
		}
		
		
		int purchasedAmount = JD.getInt("dashboard.purchaseAmount");
		
		if(sum == purchasedAmount ) {
					
		System.out.println("Validation passed, Purchase Amount is equal  "+purchasedAmount);
		
		}
		
		else {
			
			System.out.println("Validation Failed");
		};
		
		
		
	}

}
