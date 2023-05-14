package utilityFiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DynamicReqBody {
	
	public void dynamicPostBody()
	
	{	
	JsonObject requestBody = new JsonObject();

    // Create the "leg" array
    JsonArray legArray = new JsonArray();

    // Create the first leg object
    JsonObject leg1 = new JsonObject();
    leg1.addProperty("originId", "MCT");
    leg1.addProperty("destinationId", "DXB");
    leg1.addProperty("departureFrom", formatDate(LocalDate.now()));
    leg1.addProperty("departureTo", formatDate(LocalDate.now().plusDays(6)));
    legArray.add(leg1);
    
 // Create the second leg object
    JsonObject leg2 = new JsonObject();
    leg2.addProperty("originId", "DXB");
    leg2.addProperty("destinationId", "MCT");
    leg2.addProperty("departureFrom", formatDate(LocalDate.now().plusDays(4)));
    leg2.addProperty("departureTo", formatDate(LocalDate.now().plusDays(10)));
    legArray.add(leg2);

    // Add the "leg" array to the request body
    requestBody.add("leg", legArray);
    
 // Add the remaining fields to the request body
    requestBody.addProperty("cabin", "Economy");
    JsonObject paxObject = new JsonObject();
    paxObject.addProperty("adult", 1);
    paxObject.addProperty("child", 0);
    paxObject.addProperty("infant", 0);
    requestBody.add("pax", paxObject);
    requestBody.add("stops", new JsonArray());
    requestBody.add("airline", new JsonArray());
    requestBody.add("timeSlots", new JsonObject());
    requestBody.add("airports", new JsonObject());

    System.out.println(requestBody.toString());
	
}


private static String formatDate(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return date.format(formatter);
}
}
