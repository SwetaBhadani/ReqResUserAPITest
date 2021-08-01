package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {

    @Before
    public void initialize(Scenario scenario) {
        System.out.println(scenario.getName() + " STARTS");
    }


    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()){
            System.out.println(scenario.getName() + " FAILED!! :( ");
        }
        else {
            System.out.println(scenario.getName() + " PASSED!! :) ");
        }
        System.out.println(scenario.getName() + " ENDS");
    }
}
