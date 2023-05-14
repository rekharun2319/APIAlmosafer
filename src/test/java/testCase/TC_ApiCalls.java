package testCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojo.Airline;
import pojo.CustPost;
import pojo.DynamicResponsePriceData;
import pojo.Leg;
import pojo.MainResponse;
import pojo.Pax;
import pojo.Stops;
import pojo.getFlightDetails;
import utilityFiles.PayLoad;
import utilityFiles.Utility;

public class TC_ApiCalls {
	
	@Test
	@Story("Getting Flight booking details through post/ Giving dynamic request body") void postCall() throws JsonMappingException, JsonProcessingException
	{
	
	//POST Method with utility w/o  pojo
		/*Utility RequestUtils = new Utility();
		
		String PostResponse = RequestUtils.withoutPojoSendReq("POST", null, PayLoad.fareDetails());
		System.out.println("From Utility" + PostResponse);	
	*/
	
	//With setter methods
		
		CustPost newFlightSearch = new CustPost();
		List<Leg> myFlightLeg = new ArrayList<Leg>();
		Leg leg1 = new Leg();
		leg1.setOriginId("MCT");
		leg1.setDestinationId("DXB");
		leg1.setDepartureFrom("2023-05-13");
		leg1.setDepartureTo("2023-05-19");
		
		Leg leg2 = new Leg();
		leg2.setOriginId("DXB");
		leg2.setDestinationId("MCT");
		leg2.setDepartureFrom("2023-05-13");
		leg2.setDepartureTo("2023-05-19");
		
		myFlightLeg.add(leg1);
		myFlightLeg.add(leg2);
		
		newFlightSearch.setLeg(myFlightLeg);		
		newFlightSearch.setCabin("Economy");
		
		
		newFlightSearch.setStops(new ArrayList<>());
		newFlightSearch.setAirline(new ArrayList<>());
		
		Pax pax = new Pax();
		pax.setAdult(2);
		pax.setChild(2);
		pax.setInfant(0);
		newFlightSearch.setPax(pax);	
		
		        String json = "{\"leg\":[{\"originId\":\"MCT\",\"destinationId\":\"DXB\","
		        		+ "\"departureFrom\":\"2023-05-14\",\"departureTo\":\"2023-05-19\"},"
		        		+ "{\"originId\":\"DXB\",\"destinationId\":\"MCT\","
		        		+ "\"departureFrom\":\"2023-05-14\",\"departureTo\":\"2023-05-19\"}],"
		        		+ "\"cabin\":\"Economy\",\"pax\":{\"adult\":1,\"child\":0,\"infant\":0},"
		        		+ "\"stops\":[],\"airline\":[],\"timeSlots\":{},"
		        		+ "\"airports\":{}}";
		        
		        ObjectMapper objectMapper = new ObjectMapper();
		        
		        Map<String, DynamicResponsePriceData> priceDataMap = objectMapper.readValue(json, new TypeReference<Map<String, DynamicResponsePriceData>>(){});
		        
		        System.out.println(priceDataMap);
		  
		
	
		//POST Method with utility  pojo
		Utility RequestUtils = new Utility();	

		Map<String, String> headers = new HashMap<>();
		headers.put("accept", "application/json");
		headers.put("content-type", "application/json");
		
		Object PostResponse = RequestUtils.sendRequest("POST", headers ,newFlightSearch , DynamicResponsePriceData.class);
		System.out.println("From Utility" + PostResponse);
		
}

	
	/*
	RestAssured.baseURI="https://www.almosafer.com";
	Response Resp1 = given().headers("content-type","application/json")
			//.headers("Connection","keep-alive")
			.headers("Accept","application/json")
			//.headers("Accept-Encoding","gzip, deflate, br")
			.body(newFlightSearch).when().post("/api/v3/flights/flight/get-fares-calender").then().extract().response();

	//.body(PayLoad.fareDetails()).when().post("/api/v3/flights/flight/get-fares-calender").then().extract().response();
	//.body(newFlightSearch).when().post("/api/v3/flights/flight/get-fares-calender").then().extract().response();

	String jsonString = Resp1.asString();
	System.out.println("Post method fare details = "+ jsonString);
	
	//JsonPath js = new JsonPath(Resp1);
	}*/

	//@Test
	@Story("Validating the response through GET call")
	public void getCall() {
	//Final GET method ------------------>  for submitting
			//GET Method ----> created pojo airline and airports				
			//GET Method utility with pojo
			
			Utility RequestUtils = new Utility();	
			Map<String, String> headers = new HashMap<>();
			headers.put("accept", "application/json");
			headers.put("content-type", "application/json");
			Object GetResponse = RequestUtils.sendRequest("GET", headers,  "", getFlightDetails.class);
			getFlightDetails flightobj = (getFlightDetails)GetResponse;
			
			System.out.println("with pojo From Utility = " + flightobj.getAirport().get(3).getCode());
			System.out.println("with pojo From Utility = " + flightobj.getAirline().get(6).getLabel().getEn());
			
			List<Airline> airlineList = flightobj.getAirline();
			System.out.println("List of Airlines searched:");
			for(int i=0;i<airlineList.size();i++)
			{
				System.out.println( airlineList.get(i).getLabel().getEn());
			}
			
		}
}