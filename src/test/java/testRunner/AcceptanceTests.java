package testRunner;

import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = "pretty", features="src/test/resources/features",
glue = "stepDefinitions", monochrome = true)

public class AcceptanceTests {

}
