package com.groupid.firstmavenproject;

import java.io.IOException;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TestLinkIntegration {

	private TestLinkIntegration() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @author Indrapal Singh To configure testlink with selenium framework
	 */

	public static final String TESTLINK_PROJECT_NAME = "CCM-API";
	public static final String TESTLINK_PLAN_NAME = "Test Plan for CCM API";
	//public static final String TESTLINK_CASE_NAME = "VLC-1";
	public static final String BUILD_NAME = "Build 1";

	public static void updateResult(String testCaseName, String exception, String results)
			throws TestLinkAPIException, IOException {
		TestLinkAPIClient testlink = new TestLinkAPIClient("82645b3a7b1d98b7c51cd6bae07555ff","http://192.168.10.57/lib/api/xmlrpc/v1/xmlrpc.php");
		testlink.reportTestCaseResult(TESTLINK_PROJECT_NAME, TESTLINK_PLAN_NAME, testCaseName, BUILD_NAME, exception,
				results);
	}

}
