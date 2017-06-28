package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
     */
    public List<Team> getListOfTeams() throws FileNotFoundException, IOException{
       //throw new UnsupportedOperationException("Not implemented.");
   		try {
						teamList.clear();
				obj = (JSONObject) parser.parse(new FileReader("src/main/resources/db.json"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			JSONObject jsonObject1 = (JSONObject) obj;	
    		JSONArray teamj_array = (JSONArray) jsonObject1.get("teams");
    		
    		JSONObject myobj1;
    		Map<String, Object> team_map;
    		for (int i = 0; i < teamj_array.size(); i++) 
    		{
    			myobj1= (JSONObject) teamj_array.get(i);
                team_map=(Map<String, Object>) myobj1.clone();
                Team ind=new Team(team_map);
                 teamList.add(ind);
    		}
    			return teamList;
    		
  }

public static void main(String...arg)
{
	
}

}
