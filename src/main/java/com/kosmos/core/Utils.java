package com.kosmos.core;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	public static HttpResponse createRequestAndPassUrl(String restURL) throws ClientProtocolException, IOException {
		// create request and pass the url
		HttpUriRequest request = new HttpGet(restURL);

		// send the response or execute the request
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		return response;
	}

	public static int getStatusCode(String restURL) throws ClientProtocolException, IOException

	{
		HttpResponse httpResponse = createRequestAndPassUrl(restURL);

		// get the status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		return statusCode;
	}

	public static String getTheResponseType(String restURL) throws ClientProtocolException, IOException {

		HttpResponse httpResponse = createRequestAndPassUrl(restURL);

		// get the response type
		String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
		return mimeType;
	}

	public static String fetchJSONElement(JSONObject jsonObject, String elementToFetch) throws JSONException {
		// fetch the element
		JSONObject jsonObj = jsonObject;
		String element = jsonObj.getString(elementToFetch);
		return element;

	}

	public static JSONObject getJsonResponseObject(String restURL)
			throws ClientProtocolException, IOException, JSONException {
		HttpResponse httpResponse = createRequestAndPassUrl(restURL);

		// convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());

		// create jsonObject
		JSONObject jsonObj = new JSONObject(result);
		return jsonObj;
	}

}
