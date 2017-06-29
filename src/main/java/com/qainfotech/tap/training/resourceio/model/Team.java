package com.qainfotech.tap.training.resourceio.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.json.simple.JSONArray;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
    
    private final String name;
    private final Integer id;
    private final List<Individual> members;
    TeamsJsonReader tobj = new TeamsJsonReader();
   
    public Team(Map<String, Object> teamMap) throws FileNotFoundException, IOException
    {
        //throw new UnsupportedOperationException("Not implemented.");
    
         Map<String,Object> tmap = teamMap;
   
          name = tmap.get("name").toString();
          id = Integer.parseInt(tmap.get("id").toString());
         
          this.members=new ArrayList<>();
          
         JSONArray team_jarray = (JSONArray) tmap.get("members");
         List<Individual> ind_list = tobj.getListOfIndividuals();
         Iterator<Individual> itr=   ind_list.iterator();
         while(itr.hasNext())
         {
        	Individual ind_obj=itr.next();
        
        	for(int j=0;j<team_jarray.size();j++)
        	{
        		if(ind_obj.getId() == Integer.parseInt(team_jarray.get(j).toString() ))
        		{
        			
        		   this.members.add(ind_obj);
        		}
        	}
  }
    }
    
    /**
     * get team name
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * get team id
     * 
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /** 
     * get list of individuals that are members of this team
     * 
     * @return 
     */
    public List<Individual> getMembers(){
        return members;
    }
    
    /**
     * get a list of individuals that are members of this team and are also active
     * 
     * @return 
     */
    public List<Individual> getActiveMembers()
    {
    	
    	
    	try {
    		if(tobj.getListOfTeams()==null)
    		       tobj.getListOfTeams();
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    	
    	    List<Individual> activememberlist=new ArrayList<Individual>();
    
    	    Iterator<Individual>itr=this.members.iterator();
            while(itr.hasNext()){

                Individual individual=itr.next();
                if(individual.isActive())
                {
                	 activememberlist.add(individual);
                }
            }
           
             return  activememberlist;
            }
    /**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers(){
        //throw new UnsupportedOperationException("Not implemented.");
  /*  try {
		if(tobj.getListOfTeams()==null)
		       tobj.getListOfTeams();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	*/
    	
    	List<Individual> inactivememberlist=new ArrayList<Individual>();
    	    
 	    Iterator<Individual> itr=this.members.iterator();
         while(itr.hasNext()){

             Individual individual=itr.next();
             if(!(individual.isActive()))
             {
             	 inactivememberlist.add(individual);
             }
         }
        
          return  inactivememberlist;
    }
}
    

