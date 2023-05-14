package pojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import org.testng.annotations.Test;

//@Test 
public class MainResponse {
	
    public static void main(String[] args) throws Exception {
        String json = "{\n" +
                "    \"2023-05-15_2023-05-19\": {\n" +
                "        \"price\": 434.96,\n" +
                "        \"updatedAt\": \"2023-05-10T12:28:24.914Z\"\n" +
                "    },\n" +
                "    \"2023-05-14_2023-05-17\": {\n" +
                "        \"price\": 410.36,\n" +
                "        \"updatedAt\": \"2023-05-02T10:34:14.013Z\"\n" +
                "    },\n" +
                "    \"2023-05-14_2023-05-15\": {\n" +
                "        \"price\": 442.4,\n" +
                "        \"updatedAt\": \"2023-05-13T06:22:59.966Z\"\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        DynamicResponsePriceData priceData = mapper.readValue(json, DynamicResponsePriceData.class);

        System.out.println(priceData.getPrices());
    }
}






	

