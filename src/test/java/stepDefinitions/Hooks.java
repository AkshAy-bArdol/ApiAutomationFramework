package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinations m = new StepDefinations();
		if(StepDefinations.place_Id==null)
		{
			m.add_place_payload("advani","french","Asia");
			m.user_calls_with_http_request("AddPlaceAPI","POST");
			m.verify_place_id_created_maps_to_using("advani","getPlaceAPI");
		}

	}
	

}
