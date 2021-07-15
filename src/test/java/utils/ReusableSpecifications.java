package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpec;
	
	public static ResponseSpecBuilder respec;
	public static ResponseSpecification responseSpec;
	
	public static RequestSpecification genericRequestSpec() throws FileNotFoundException
	{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		rspec = new RequestSpecBuilder();
		rspec.setBaseUri("https://api.github.com").setAccept("String")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log));
		requestSpec = rspec.build();
		return requestSpec;
		
	}

	public static ResponseSpecification genericResponseSpec()
	{
		respec = new ResponseSpecBuilder();
		respec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		responseSpec = respec.build();
		return responseSpec;
		
	}
}
