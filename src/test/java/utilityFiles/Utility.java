 package utilityFiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import pojo.CustPost;
import pojo.getFlightDetails;

public class Utility {
	
	RequestSpecification req;
	Object ResponseBody = null;
	
	
	public Object sendRequest(String ReqMethod, Map<String, String> headers, Object ReqPayload, Class Response) throws JsonMappingException, JsonProcessingException
	{
		
	req = RestAssured.given().baseUri("https://www.almosafer.com")
			.headers(headers);
	
	req.headers(headers);
	
	//.headers("accept","application/json")
	//.headers("content-type","application/json");	
	
	 if(ReqMethod.equals("GET"))
		 
	 	{
		 ResponseBody = req.when().get("/api/flight/resource/codes/MCT,"
		 		+ "DXB,SHJ,DOH,AUH,XNB,BAH,ADD,G9,QR,3L,EY,WY,FZ,EK,GF,ET")
				 .then().extract().response().as(Response);
		 System.out.println("with pojo Inside GET " + ResponseBody);
	 	}
	 
	 else if(ReqMethod.equals("POST"))
		 
		{
		 String ResponseContent;
		 ResponseContent = req.body(ReqPayload).when().post("/api/v3/flights/flight/get-fares-calender").then()
				 .extract().response().asString();
		 ObjectMapper objectmapper = new ObjectMapper();
		 ResponseBody = objectmapper.readValue(ResponseContent, HashMap.class);
		 
				 
		 System.out.println("Inside POST " +ResponseBody);
		 }
	 
 return ResponseBody;
	}

}



//without pojo
/*
	public String withoutPojoSendReq(String ReqMethod, Map<String, String> headers, String ReqPayload)
		{
	
	req = RestAssured.given().baseUri("https://www.almosafer.com").headers(headers);
	req.headers(headers);
	
		//.headers("accept","application/json")
		//.headers("content-type","application/json");

 if(ReqMethod.equals("GET"))
 {
	 ResponseBody = req.when().get("/api/flight/resource/codes/MCT,"
	 		+ "DXB,SHJ,DOH,AUH,XNB,BAH,ADD,G9,QR,3L,EY,WY,FZ,EK,GF,ET")
			 .then().assertThat().statusCode(200).extract().response().asString();
	 System.out.println("w/o pojo Inside GET " + ResponseBody);
 }
 else if(ReqMethod.equals("POST"))
		 {
	 ResponseBody = req.body(ReqPayload).when().post("/api/v3/flights/flight/get-fares-calender")
			 .then().extract().response().asString();
	 System.out.println("w/o pojo Inside POST " +ResponseBody);
		 }
return ResponseBody;

	}
}	*/



