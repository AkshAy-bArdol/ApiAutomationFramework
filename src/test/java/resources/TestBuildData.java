package resources;

import java.util.ArrayList;
import java.util.List;

import pojoclasses.AddPlace;
import pojoclasses.Location;

public class TestBuildData {
	
	public AddPlace addPlacePayload(String name,String language,String address)
	{
		AddPlace Ap=new AddPlace();
		Ap.setAccuracy(50);
		Ap.setName(name);
		Ap.setPhone_number("(+91) 983 893 3937");
		Ap.setAddress(address);
		Ap.setWebsite("http://google.com");    
		Ap.setLanguage(language);
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		Ap.setTypes(mylist);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		Ap.setLocation(l);
		return Ap;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
