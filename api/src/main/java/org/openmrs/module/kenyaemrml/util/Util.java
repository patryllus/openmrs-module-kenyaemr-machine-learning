package org.openmrs.module.kenyaemrml.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Util {
	
	/*public Response toResponse(Object object)
	{
	    return new Response(object);
	}

	public Map<String, String> getRequestParametersExcept(HttpServletRequest req, List<String> exceptions)
	{
	    return req.params().entrySet().stream()
	            .filter(entry -> !exceptions.contains(entry.getKey()))
	            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	}

	public boolean getRequestParamAsBoolean(Req req, String fieldName)
	{
	    Map<String, String> params = req.params();

	    if (!params.containsKey(fieldName))
	    {
	        return false;
	    }

	    String value = params.get(fieldName);

	    if (value != null)
	    {
	        value = value.toLowerCase();
	        return value.equals("true") || value.equals("1");
	    }
	    return false;
	}*/
	
	public static String fetchRequestBody(BufferedReader reader) {
		String requestBodyJsonStr = "";
		try {
			
			BufferedReader br = new BufferedReader(reader);
			String output = "";
			while ((output = reader.readLine()) != null) {
				requestBodyJsonStr += output;
			}
			
		}
		catch (IOException e) {
			
			System.out.println("IOException: " + e.getMessage());
			
		}
		return requestBodyJsonStr;
	}
	
	public static Map<String, String> extractVariablesFromRequestBody(String requestBodyString) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode tree = null;
		Map<String, String> modelParams = new HashMap<String, String>();
		try {
			tree = (ObjectNode) mapper.readTree(requestBodyString);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<Map.Entry<String, JsonNode>> it = tree.getFields();
		while (it.hasNext()) {
			Map.Entry<String, JsonNode> field = it.next();
			String keyId = field.getKey();
			String keyValue = field.getValue().getTextValue();
			modelParams.put(keyId, keyValue);
		}
		return modelParams;
	}
}
