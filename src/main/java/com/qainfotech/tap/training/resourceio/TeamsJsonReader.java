package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     */
	JSONParser parser = new JSONParser();
	JSONObject obj = null; 
	
     	
    	List<Individual> individualList = new ArrayList<Individual>();
    	List<Individual> List_inactive = new ArrayList<Individual>();
    	List<Individual> List_active = new ArrayList<Individual>();
        List<Team> teamList = new ArrayList<Team>();

    	public List<Individual> getListOfIndividuals() throws FileNotFoundException, IOException {
   		
    		try 
    		{
    			individualList.clear();
    			obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
    		} 
    		catch (org.json.simple.parser.ParseException e) 
    		{
    		}

    		JSONObject jsonObject = (JSONObject) obj;	
    		JSONArray j_array = (JSONArray) jsonObject.get("individuals");
           // Individual obj1;
    		JSONObject myobj;
    		Map<String, Object> individualMap;
    		for (int i = 0; i < j_array.size(); i++) 
    		{
    			myobj = (JSONObject) j_array.get(i);
                individualMap=(Map<String, Object>) myobj.clone();
                Individual ind=new Individual(individualMap);
                individualList.add(ind);
    		}
    			return individualList;
    			

}
   
     /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException
    {
    	try {
			if(this.getListOfIndividuals()==null)
				this.getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for (int i = 0; i < individualList.size(); i++) 
    	{
			if (individualList.get(i).getId() == (int) id)
			{
            return individualList.get(i);

			}
		}
		 throw new ObjectNotFoundException(null, null, null);
}
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
        //throw new UnsupportedOperationException("Not implemented.");
      
    	try {
			if(this.getListOfIndividuals()==null)
				this.getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	for (int i = 0; i < individualList.size(); i++) {

			
			if (individualList.get(i).getName().equals(name)) {

					return individualList.get(i);

			}

		}
	throw new ObjectNotFoundException(null, null, null)	;
    }
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public List<Individual> getListOfInactiveIndividuals() {
       //throw new UnsupportedOperationException("Not implemented.");
    	
    	try {
			if(this.getListOfIndividuals()==null)
			 this.getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    for(int i=0;i<individualList.size();i++)
    {
    	   if(individualList.get(i).isActive()==false)
    	{
    	    	List_inactive.add(individualList.get(i));
    	}
     }
    return(List<Individual>) List_inactive;
    }   	
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
     //   throw new UnsupportedOperationException("Not implemented.");
    	
    	try {
			if(this.getListOfIndividuals()==null)
				this.getListOfIndividuals();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for(int i=0;i<individualList.size();i++)
        {
        	if(individualList.get(i).isActive()==true)
        	{
        		List_active.add(individualList.get(i));
        	}
         }
                return(List<Individual>) List_active;
        	
    
  }
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ObjectNotFoundException 
     */
    public List<Team> getListOfTeams() throws FileNotFoundException, IOException, ObjectNotFoundException{
       //throw new UnsupportedOperationException("Not implemented.");
   		
     	
    	JSONObject jo=null;
    	List<Team> listTeam=new ArrayList<Team>();
    	Team teamObject = null;
    	Map<String,Object> map=new HashMap<String, Object>();
    	JSONParser parser=new JSONParser();
    	 Object obj = null;
		try {
			obj = parser.parse(new FileReader("C:/Users/priyagautam/git/assignment-resource-io/src/main/resources/db.json"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 JSONObject jsonObject = (JSONObject) obj;
    	 JSONArray jsonarray = (JSONArray) jsonObject.get("teams");
    	 TeamsJsonReader reader = new TeamsJsonReader();
    	 
    	 for (int i=0; i<jsonarray.size(); i++)
    	 {
    		 List<Individual> individualList = new ArrayList<>();
    		 jo=(JSONObject) jsonarray.get(i);
    		 map.put("name", jo.get("name"));
    		 map.put("id", ((Long) jo.get("id")).intValue());
    		 
    		 JSONArray memberArray = (JSONArray) jo.get("members");
    		 for (int index = 0; index < memberArray.size(); index++) {

					individualList.add(reader.getIndividualById(((Long) memberArray.get(index)).intValue()));

				}
    		 
    		 
    		 map.put("members", individualList);
    		teamObject= new Team(map);
				listTeam.add(teamObject);
					 
    	 }
    	 return listTeam;
    		}
    		
    	    
    	

public static void main(String...arg)
{
	
}


}
