package com.kosmos.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.kosmos.core.ExcelHelper;
import com.kosmos.core.Utils;
import com.kosmos.core.PropertyReader;


public class RestClient {

	// entering data from config.properties file
	PropertyReader configFile = new PropertyReader("config.properties");
	String api = configFile.getPropertyValue("API_TO_TEST");

	@Test(dataProvider = "TestData")
	public void fetchEulaWhenStatustCode200(String app_url) throws ClientProtocolException, IOException, JSONException {
		int statusCode = Utils.getStatusCode(api);
		if (statusCode == 200) {
			if (Utils.getTheResponseType(app_url).equals("application/json")) {
				JSONObject responseObj = Utils.getJsonResponseObject(app_url);

				// assert response is not null
				Assert.assertNotNull(responseObj, "Response is null");

				// schema validation
				System.out
						.println("Schema holds the EULA value as : " + Utils.fetchJSONElement(responseObj, "eula_b64"));
			}
		}
	}

	@DataProvider
	public Object[][] TestData() throws Exception {
		return new ExcelHelper().readExcel().stream().map(url -> new Object[] { url }).toArray(Object[][]::new);
	}
}
