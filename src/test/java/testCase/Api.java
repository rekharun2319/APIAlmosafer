package testCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Properties;
import org.testng.annotations.Test;
import utilityFiles.PayLoad;
import utilityFiles.Utility;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojo.Airline;
import pojo.getFlightDetails;

public class Api {
	
	

	public static void main(String[] args) {
		
	//POST Method
		//utility RequestUtils = new utility();
		
		//String PostResponse = RequestUtils.sendRequest("POST", null, payLoad.postPayload());
	//System.out.println("From Utility" + PostResponse);

//Post call -----> returning html response
		/*
	RestAssured.baseURI="https://www.almosafer.com";
  String Resp = given().headers("content-type","application/json")
				//.headers("Connection","keep-alive")
				.headers("Accept","application/json")
				//.headers("Accept-Encoding","gzip, deflate, br")
		.body(utilityFiles.payLoad.postPayload()).when().post("/api/cms/page").then().extract().body().as()
		//.when().get("/api/flight/resource/codes/MCT,DXB,SHJ,DOH,AUH,XNB,BAH,ADD,G9,QR,3L,EY,WY,FZ,EK,GF,ET").then().extract().body().as(getFlightDetails.class);
				//.when().get("/api/flight/resource/codes/MCT,DXB,SHJ,DOH,AUH,XNB,BAH,ADD,G9,QR,3L,EY,WY,FZ,EK,GF,ET").then().extract().body().toString();

	
		//System.out.println("Post Response = " + Resp.getCampaigns().get(0).getBookingStartDate());
		
		JsonPath js = new JsonPath(Resp);
		String responseValidation = js.getString("campaigns[0].campaign_name_en");
		System.out.println(Resp);
		
	*/	
		
	//POST call get fares calender ---> Finalized
		
	/*	
		RestAssured.baseURI="https://www.almosafer.com";
		String response = given().header("content-type","application/json")
		.body(payLoad.fareDetails())
		.when().post("/api/v3/flights/flight/get-fares-calender")
		.then().assertThat().statusCode(200)//body("price",equalTo(" 434.96"))//
		.extract().asString();
		
		System.out.println(response);
		JsonPath js= new JsonPath(response);
		String res = js.getString("2023-05-12_2023-05-15.price");
		System.out.println(res);
		
	*/
	//Final GET method ------------------>  for submitting
		//GET Method ----> created pojo airline and airports		
			
		//GET Method utility with pojo
		
		Utility RequestUtils = new Utility();		
		Object GetResponse = RequestUtils.sendRequest("GET", null,  "", getFlightDetails.class);
		getFlightDetails flightobj = (getFlightDetails)GetResponse;
		System.out.println("with pojo From Utility = " + flightobj.getAirport().get(3).getCode());
		System.out.println("with pojo From Utility = " + flightobj.getAirline().get(6).getLabel().getEn());
		
		List<Airline> airlineList = flightobj.getAirline();
		System.out.println("List of Airlines searched:");
		for(int i=0;i<airlineList.size();i++)
		{
			System.out.println( airlineList.get(i).getLabel().getEn());
		}
		
		
		//without pojo
		
		/*utility RequestUtils = new utility();		
		String GetResponse = RequestUtils.withoutPojoSendReq("GET", null,"");
		System.out.println("From Utility" + GetResponse);
		*/
		
		/*
		RestAssured.baseURI="https://www.almosafer.com";
		getFlightDetails GETResponse = given().when().get("/api/flight/resource/codes/MCT,DXB,SHJ,DOH,AUH,XNB,BAH,ADD,G9,QR,3L,EY,WY,FZ,EK,GF,ET")
		.then().assertThat().statusCode(200).extract().as(getFlightDetails.class);
		
		System.out.println("Get Airport Response1 = " + GETResponse.getAirport().get(2).getTz());
		System.out.println("Get Airline Response1 = " + GETResponse.getAirline().get(2).getLabel().getEn());
		System.out.println("");
		System.out.println("Get country = " + GETResponse.getAirport().get(5).getCountry().getLabel().getEn());
		System.out.println("Get Airline = " + GETResponse.getAirline().get(5).getLabel().getEn());
		
		List<Airline> airlineList = GETResponse.getAirline();
		System.out.println("List of Airlines searched:");
		for(int i=0;i<airlineList.size();i++)
		{
			System.out.println( airlineList.get(i).getLabel().getEn());
		}
		
		//JsonPath js1= new JsonPath(GETResponse);
		//String extractedRes = js1.getString("airline.code.label.en");
		//System.out.println(extractedRes);
	

		/*
		
		//GET flight details
		
		/*RestAssured.baseURI="https://www.almosafer.com";
		Response response1 = RestAssured.given()
			.queryParam("query","MCT-DXB/2023-05-11/2023-05-13/Economy/1Adult")
			.when().get("/api/v3/flights/flight/search");
		
		int statuscode =response1.getStatusCode();
		String responseBody = response1.getBody().asString();
		
		System.out.println("Status Code : " +statuscode );
		System.out.println("Response Body : " + responseBody );
		
		JsonPath js1 = new JsonPath(responseBody);
		double ID = js1.getDouble("next.nid");
		System.out.println(ID);
		String KeyValue = js1.get("next.hashedKey");
		System.out.println(KeyValue);
*/
	} 	
	}

