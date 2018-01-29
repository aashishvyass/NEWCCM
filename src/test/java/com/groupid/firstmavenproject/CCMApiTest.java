package com.groupid.firstmavenproject;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class CCMApiTest {

	Response response;

	public String tokenAuth;
	public String responseAuth;

	@BeforeMethod()
	public void baseUrl() {
		RestAssured.baseURI = "http://52.163.247.87/api/v1";
	}

	@Test(priority = 1)

	public void brandType() throws TestLinkAPIException, IOException {
		try {

			RequestSpecification httpRequest = RestAssured.given();

			Response response = httpRequest.request(Method.GET, "/brand/types");

			String responseBody = response.getBody().asString();
			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Brand Type: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-15", null, TestLinkAPIResults.TEST_PASSED);
			
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Brand Type API body  : " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-15", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 2)
	public void getcountry() throws TestLinkAPIException, IOException {
		try {

			RequestSpecification httpRequest = RestAssured.given();

			Response response = httpRequest.request(Method.GET, "/countries");

			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Get Country: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-1", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Get Country API body: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-1", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 3)
	public void categoryType() throws TestLinkAPIException, IOException

	{
		try {

			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET, "/category/types");
			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("CATEGORY TYPE: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-14", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Category type: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-14", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
throw (e);
		}
	}

	@Test(priority = 4)
	public void submitFeedback() throws JSONException, TestLinkAPIException, IOException

	{
		try {
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();
			requestParams.put("feedbackTypeId", "ew");
			requestParams.put("email", "anil.anjana+994@ranosys.com");
			requestParams.put("description", "test 4");

			request.body(requestParams.toString());
			request.header(new Header("Content-Type", "application/json"));
			Response response = request.post("/feedback/submit");

			int statusCode = response.getStatusCode();

			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Submit Feedback: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-22", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Submit Feedback: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-22", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 5)
	public void registerUser() throws JSONException, TestLinkAPIException, IOException {

		try {

			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("email", "anil.anjana+124@ranosys.com");
			requestParams.put("password", "Password@123");
			requestParams.put("mobileCountryId", "1");
			requestParams.put("mobileNumber", "9898989898");
			requestParams.put("fullName", "Anil Anjana");
			requestParams.put("isPrivacyPolicyAgreed", "1");
			requestParams.put("isTermsConditionAgreed", "1");
			requestParams.put("isCcmShareholder", "0");
			requestParams.put("companyName", "");
			requestParams.put("isContractor", "0");
			requestParams.put("contractorCompanyName", "");

			request.body(requestParams.toString());

			request.header(new Header("Content-Type", "application/json"));
			response = request.post("/user/register");

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			String responseBody = response.getBody().asString();

			System.out.println("Register User: " + responseBody);
			System.out.println(statusCode);
			TestLinkIntegration.updateResult("CCMAPI-2", null, TestLinkAPIResults.TEST_PASSED);
			
		} catch (AssertionError e) {

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("registerUser : " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-2", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 6)

	public void ccmLogin() throws JSONException, TestLinkAPIException, IOException {
		try {

			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = new JSONObject();

			requestParams.put("username", "varsha.gupta+1@ranosys.com");
			requestParams.put("password", "Pass@123");

			request.body(requestParams.toString());

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };

			request.headers(new Headers(headers));
			Response response = request.post("/login");

			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			/*
			 * String responseBody1 = response.getBody().asString();
			 * System.out.println("Token is =" + responseBody1);
			 */

			JsonPath jsonPathEvaluator = response.jsonPath();

			tokenAuth = jsonPathEvaluator.get("token");
			System.out.println(tokenAuth);

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Login API " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-3", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Loging API : " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-3", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 7)
	public void myRewards() throws JSONException, TestLinkAPIException, IOException {
		try {
			// RestAssured.baseURI = "http://52.163.247.87/api/v1/appuser/mypoints";
			RequestSpecification request = RestAssured.given();

			// JSONObject requestParams = new JSONObject();

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),

					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };

			request.headers(new Headers(headers));
			Response response = request.get("/appuser/mypoints");

			String responseBody = response.getBody().asString();
			System.out.println("My Rewards" + responseBody);

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);

			// String responseBody1 = response.getBody().asString();
			// System.out.println("Token is =" + responseBody1);

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("My Rewards" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-25", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e)

		{
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("My Reward API failed with body: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-25", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 32)
	public void logOut() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),

					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));

			Response response = request.post("/appuser/logout");

			int statusCode = response.getStatusCode();
			System.out.println(statusCode);

			String responseBody = response.getBody().asString();

			System.out.println("Logout " + responseBody);

			Assert.assertEquals(statusCode, 200);
			TestLinkIntegration.updateResult("CCMAPI-4", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Logout API failed with body: " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-4", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 8)

	public void updatedevicedetails() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));

			Response response = request.post("/updatedevicedetails");

			int statusCode = response.getStatusCode();

			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Update device detail " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-5", null, TestLinkAPIResults.TEST_PASSED);
			

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update device detail API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-5", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 9)
	public void forgotpassword() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("email", "anil.anjana+124@ranosys.com");

			request.body(requestParams.toString());

			request.header(new Header("Content-Type", "application/json"));
			response = request.post("/forgotpassword");

			int statusCode = response.getStatusCode();
			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Foggot Passwod" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-6", null, TestLinkAPIResults.TEST_PASSED);

			
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update device detail API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-6", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 10)

	public void myprofile() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Authorization", "Bearer " + tokenAuth),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };

			request.headers(new Headers(headers));
			Response response = request.get("/appuser/profile/view");

			String responseBody = response.getBody().asString();

			int statusCode = response.getStatusCode();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("My Profile " + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-10", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update device detail API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-10",  e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 11)
	public void updateprofile() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("userId", "144");
			requestParams.put("mobileCountryId", "anil.anjana@ranosys.com");
			requestParams.put("mobileCountryId", "1");
			requestParams.put("mobileNumber", "9898989898");
			requestParams.put("verificationCode", "9898");
			requestParams.put("fullName", "Anil Anjana");
			requestParams.put("companyName", "Ranosys");
			requestParams.put("address1", "1");
			requestParams.put("address2", "0");
			requestParams.put("city", "");
			requestParams.put("postalCode", "0");
			requestParams.put("countryId", "");

			request.body(requestParams.toString());

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Authorization", "Bearer " + tokenAuth),
					new Header("X-Requested-With", "XMLHttpRequest"), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.headers(new Headers(headers));

			response = request.post("/appuser/profile/update");

			int statusCode = response.getStatusCode();
			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("Register user" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-11", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Update Profile API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-11", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 12)
	public void changepassword() throws JSONException, TestLinkAPIException, IOException {
		try {
			// RestAssured.baseURI = "http://52.163.247.87/api/v1/user/register";
			RequestSpecification request = RestAssured.given();
			JSONObject requestParams = new JSONObject();

			requestParams.put("currentPassword", "Pass@1234");
			requestParams.put("newPassword", "Pass@123");

			request.body(requestParams.toString());

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.post("appuser/changepassword");

			int statusCode = response.getStatusCode();

			String responseBody = response.getBody().asString();

			Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);
			System.out.println(statusCode);
			System.out.println("changepassword" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-12", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Change Password API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-12", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 13)
	public void sendverificationlink() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			// JSONObject requestparams = new JSONObject();
			// request.body(requestParams.toString());
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.post("appuser/sendverificationlink");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);

			System.out.println(statusCode);
			System.out.println("sendverificationlink" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-13", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Send verification link API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-13", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 14)
	public void productlist() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			/*
			 * requstparams.put("first", "0"); requstparams.put("rows", "10");
			 */

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "branchCode");
			firstFilterJsonObject.put("value", new String[] { "sha", "mon" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "categoryCode");
			secondFilterJsonObject.put("value", new String[] { "Ap", "Bp" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "productCode");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "brandCode");
			firstextraFilters.put("value", new String[] {});
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "brandCode");
			secondextraFilters.put("value", new String[] {});
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("product/types");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Product list" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-16", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Product list API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-16", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);		
		}

	}

	@Test(priority = 15)
	public void productimage() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			/*
			 * JSONObject requstparams = new JSONObject(); requstparams.put("productCode",
			 * "ZZZ");
			 */

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/product/image/list?productCode=ZCARD");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Product Image" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-17", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Product image API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-17", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 16)
	public void basicpages() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/pages");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Basic pages" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-20", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Basic Pages API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-20", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 17)

	public void getfeedbacktype() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/feedback/types");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Get feedback type" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-21", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Get Feedback API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-21", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 18)
	public void giftdetail() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("appuser/rewards/giftdetail");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Gift detail" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-25", null, TestLinkAPIResults.TEST_PASSED);

			

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Gift Detail API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-25", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 19)
	public void mypoints() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/appuser/mypoints");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();
			System.out.println(statusCode);
			System.out.println("My point" + statusBody);
			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("My point" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-26", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("My Point API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-26", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 20)
	public void mygifts() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("/appuser/mygift");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();
			System.out.println(statusCode);
			System.out.println("My gifts" + statusBody);
			Assert.assertEquals(statusCode, 200);
			TestLinkIntegration.updateResult("CCMAPI-28", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("My Gift API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-28", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 21)
	public void advertisementsdetail() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.get("advertisements/detail?id=166");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement detail " + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-35", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisements detail API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-35", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 22)
	public void advertisementsdelete() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			response = request.delete("/admin/advertisements/166");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement delete" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-37", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisements detail API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-37",e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 23)
	public void promotionlist() throws JSONException, TestLinkAPIException, IOException {
		try {

			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "promoCode");
			firstFilterJsonObject.put("value", new String[] { "sha", "mon" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "description");
			secondFilterJsonObject.put("value", new String[] { "Ap", "Bp" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "promoCode");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "promoCode");
			firstextraFilters.put("value", new String[] {});
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "brandCode");
			secondextraFilters.put("value", new String[] {});
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/promotion/list");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Promotion List" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-24", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Promotion list API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-24", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 24)
	public void promotionproductlist() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "productCode");
			firstFilterJsonObject.put("value", new String[] { "sha", "mon" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "brandDescription");
			secondFilterJsonObject.put("value", new String[] { "Ap", "Bp" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", new String[] {});
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", new String[] {});
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/promotions/product/list?promoCode=123");
			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Promotion Product list" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-16", null, TestLinkAPIResults.TEST_PASSED);


		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Promotion product list  API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-16", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 25)
	public void userpoint() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "");
			firstFilterJsonObject.put("value", "");
			firstFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "");
			secondFilterJsonObject.put("value", "");
			secondFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "createdAt");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", "");
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", "");
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/appuser/userpoint/list");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("My point transaction history is" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-27", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("User Point  API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-27", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 26)
	public void usergiftlist() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "");
			firstFilterJsonObject.put("value", "");
			firstFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "");
			secondFilterJsonObject.put("value", "");
			secondFilterJsonObject.put("matchMode", "");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "createdAt");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", "");
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", "");
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/appuser/usergift/list");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("User Gift list" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-29", null, TestLinkAPIResults.TEST_PASSED);

			
		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("User Point  API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-29", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 27)
	public void productstore() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "name");
			firstFilterJsonObject.put("value", "");
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "address");
			secondFilterJsonObject.put("value", "");
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "name");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", "");
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", "");
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/store/types");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Store Type API Body" + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-18", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Product Store API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-18", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

	@Test(priority = 28)
	public void advertisementgetproduct() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "productName");
			firstFilterJsonObject.put("value", "a");
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/product/list");

		int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement Get Product Body " + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-31", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisement get product API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-31", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 29)
	public void randomadvertisement() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			requstparams.put("postLoginFlag", "0");
			requstparams.put("excludeAdvertisementId", "");

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/advertisements/random");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement Get Product Body " + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-30", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Random advertisement API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-30", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}
	}

	@Test(priority = 30)
	public void advertisementlist() throws JSONException, TestLinkAPIException, IOException {
		try {
			RequestSpecification request = RestAssured.given();
			JSONObject requstparams = new JSONObject();

			JSONArray filterJsonArray = new JSONArray();
			JSONObject firstFilterJsonObject = new JSONObject();
			firstFilterJsonObject.put("fieldName", "name");
			firstFilterJsonObject.put("value", new String[] { "ad" });
			firstFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(firstFilterJsonObject);

			JSONObject secondFilterJsonObject = new JSONObject();
			secondFilterJsonObject.put("fieldName", "address");
			secondFilterJsonObject.put("value", new String[] { "Ap" });
			secondFilterJsonObject.put("matchMode", "like");
			filterJsonArray.put(secondFilterJsonObject);

			JSONArray multisort = new JSONArray();
			JSONObject firstmultisort = new JSONObject();
			firstmultisort.put("field", "name");
			firstmultisort.put("order", "-1");
			multisort.put(firstmultisort);

			JSONArray extrafilters = new JSONArray();
			JSONObject firstextraFilters = new JSONObject();
			firstextraFilters.put("fieldName", "");
			firstextraFilters.put("value", new String[] { "" });
			extrafilters.put(firstextraFilters);

			JSONObject secondextraFilters = new JSONObject();
			secondextraFilters.put("fieldName", "");
			secondextraFilters.put("value", new String[] { "" });
			extrafilters.put(secondextraFilters);

			requstparams.put("first", "0");
			requstparams.put("rows", "10");

			requstparams.put("filters", filterJsonArray);
			requstparams.put("multiSortMetas", multisort);
			requstparams.put("extraFilters", extrafilters);
			requstparams.put("search", "");

			System.out.println("Json-----");
			System.out.println(requstparams);

			Header[] headers = new Header[] { new Header("Content-Type", "application/json"),
					new Header("X-Requested-With", "XMLHttpRequest"),
					new Header("X-Authorization", "Bearer " + tokenAuth), new Header("Cache-Control", "no-cache") };
			request.headers(new Headers(headers));
			request.body(requstparams.toString());

			response = request.post("/advertisements/list");

			int statusCode = response.getStatusCode();
			String statusBody = response.getBody().asString();

			Assert.assertEquals(statusCode, 200);
			System.out.println(statusCode);
			System.out.println("Advertisement list " + statusBody);
			TestLinkIntegration.updateResult("CCMAPI-34", null, TestLinkAPIResults.TEST_PASSED);

		} catch (AssertionError e) {
			int statusCode = response.getStatusCode();
			System.out.println(statusCode);
			String responseBody = response.getBody().asString();
			System.out.println("Advertisement list API failed with body:" + responseBody);
			TestLinkIntegration.updateResult("CCMAPI-34", e.getMessage(), TestLinkAPIResults.TEST_FAILED);
			throw (e);
		}

	}

}
