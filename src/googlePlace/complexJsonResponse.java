package googlePlace;

import Files.payLoad;
import io.restassured.path.json.JsonPath;

public class complexJsonResponse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JsonPath JD = new JsonPath(payLoad.CoursePrice());
		int courseSize = JD.getInt("courses.size()");
		System.out.println(courseSize);
		
		
		int amount = JD.getInt("dashboard.purchaseAmount");
		System.out.println(amount);	
		
		
		for(int i = 0;i<courseSize ;i++ ) {
			
			String Title = JD.getString("courses["+i+"].title");
			System.out.println(Title);	
			
			int Price = JD.getInt("courses["+i+"].price");
			System.out.println(Price);	
			
		}
		
		
		/*String Title1 = JD.getString("courses[0].title");
		System.out.println(Title1);	
		
		String Title2 = JD.getString("courses[1].title");
		System.out.println(Title2);	
		
		String Title3 = JD.getString("courses[2].title");
		System.out.println(Title3);	

		
		int Price1 = JD.getInt("courses[0].price");
		System.out.println(Price1);	
		
		int Price2 = JD.getInt("courses[1].price");
		System.out.println(Price2);	
		
		int Price3 = JD.getInt("courses[2].price");
		System.out.println(Price3);	
		
		
		
		int copiesRPA = JD.getInt("courses[2].copies");
		System.out.println(copiesRPA);	*/
	
                for(int e = 0; e < courseSize; e++ ) {
                	
                	String Tittle = JD.getString("courses["+e+"].title");
			
			if(Tittle.equalsIgnoreCase("RPA")) {
			
			
			int copies = JD.getInt("courses["+e+"].copies");
			System.out.println(copies);
			
			break;
			}
			
			
			
		}
		
	
	}
	
	
	

}
