package testCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import io.restassured.internal.util.IOUtils;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import pojo.Airline;
import pojo.CustPost;
import pojo.Leg;
import pojo.Pax;
import pojo.PriceField;
import pojo.Stops;
import pojo.getFlightDetails;
import utilityFiles.PayLoad;
import utilityFiles.Utility;

public class TC_ApiCalls {
	
	@Test
	@Story("Getting Flight booking details through post/ Giving dynamic request body") void postCall() throws JsonMappingException, JsonProcessingException
	{			
	
		CustPost newFlightSearch = new CustPost();
		List<Leg> myFlightLeg = new ArrayList<Leg>();
		Leg leg1 = new Leg();
		leg1.setOriginId("MCT");
		leg1.setDestinationId("DXB");
		leg1.setDepartureFrom("2023-05-14");
		leg1.setDepartureTo("2023-05-19");
		
		Leg leg2 = new Leg();
		leg2.setOriginId("DXB");
		leg2.setDestinationId("MCT");
		leg2.setDepartureFrom("2023-05-14");
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

		
		Utility RequestUtils = new Utility();	

		Map<String, String> headers = new HashMap<>();
		
		headers.put("accept", "application/json");
		headers.put("content-type", "application/json");
		
		Object PostResponse = RequestUtils.sendRequest("POST", headers ,newFlightSearch, null);
		System.out.println("From Utility" + PostResponse);
		Map<String, Object> ResMap = (HashMap) PostResponse;
		ResMap.keySet();
		
		for(String Key : ResMap.keySet() )
		{
			System.out.println(ResMap.get(Key));
			Map<String, Object> childMap = (HashMap)ResMap.get(Key);
			System.out.println(childMap.get("price"));
			System.out.println(childMap.get("updatedAt"));
		}

	}

	@Test
	@Story("Validating the response through GET call")
	public void getCall() throws JsonMappingException, JsonProcessingException {
			
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