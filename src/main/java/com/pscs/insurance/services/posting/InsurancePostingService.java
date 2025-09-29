package com.pscs.insurance.services.posting;

import java.util.ResourceBundle;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class InsurancePostingService {
	private ResourceBundle bundle = ResourceBundle.getBundle("insurance");
	     
	public JSONObject sendGetRequest(String jsonBody, String urlType, String params) {
		JSONObject responseJson = new JSONObject();
		try {
			String apiKey = bundle.getString("API_KEY");
			String url = bundle.getString(urlType).concat(params);

			System.out.println("EKYC URL" + url + "\n Api Key" + apiKey + "\n Request " + jsonBody);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", bundle.getString("SECRET_KEY"));
			headers.set("AppId", apiKey);
			HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			// response status code
			HttpStatusCode statusCode = response.getStatusCode();
			System.out.println(statusCode);
			int value = statusCode.value();
			if (value == 200) {
				System.out.println(response.getBody());
				responseJson.put("respcode", value + "");
				responseJson.put("respmsg", "SUCCESS");
				responseJson.put("data", response.getBody());

			} else {
				responseJson.put("respcode", value + "");
				responseJson.put("respmsg", "Failed");
				responseJson.put("data", response.getBody());
			}

		} catch (HttpClientErrorException.NotFound e) {
			System.err.println("Error: Resource not found - " + e.getResponseBodyAsString());
			responseJson.put("respcode", "404");
			responseJson.put("respmsg", "Resource not found");
			responseJson.put("data", e.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("respcode", "500");
			responseJson.put("respmsg", "Internal Server Error");
			responseJson.put("data", e.getMessage());
		}

		return responseJson;

	}

}
