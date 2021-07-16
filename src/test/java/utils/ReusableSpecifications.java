package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpec;
	
	public static ResponseSpecBuilder respec;
	public static ResponseSpecification responseSpec;
	
	public static RequestSpecification genericRequestSpec() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./serenity.properties");
		prop.load(fis);
		String endpoint = prop.getProperty("endpoint");
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true));
		rspec = new RequestSpecBuilder();
		rspec.setBaseUri(endpoint).setAccept("String")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log));
		requestSpec = rspec.build();
		return requestSpec;
		
	}

	public static ResponseSpecification genericResponseSpec()
	{
		respec = new ResponseSpecBuilder();
		respec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS)
		.expectContentType("application/json");
		responseSpec = respec.build();
		return responseSpec;
		
	}
}
