package testCase;

import io.restassured.path.json.JsonPath;
import utilityFiles.PayLoad;

public class GetResponseValidation {
	
	public static void main (String[] args) {
		 int count = 8;
		JsonPath js= new JsonPath(PayLoad.getResp());

		int countAirline = js.getInt("airline.size()");
		System.out.println("Number of Airline = "+ countAirline);
		
		int countAirport = js.getInt("airport.size()");
		System.out.println("Number of Airpoprts = " + countAirport);
		
		String airlineOne = js.get("airline[8].label.en");
		System.out.println(airlineOne);
		
		//Validating the Airline and Airports
		
		for(int i = 0;i<count;i++)
		{
			String Airlines = js.get("airline["+i+"].label.en");
			System.out.println(Airlines);
	
			}
		for(int j = 0;j<count;j++)
		{
			String Airports = js.get("airport["+j+"].label.en");
			//System.out.println(Airports);
			if(Airports.equalsIgnoreCase("Dubai International Airport"))
			{
				String arrivalCountry = js.get("airport["+j+"].country.label.en");
				System.out.println("Welcome to " + arrivalCountry);
				
				String arrivalCity = js.get("airport["+j+"].city.en");
				System.out.println("Have a wonderful vacation at " + arrivalCity);
				
				break;
			}
		}
	}

}
