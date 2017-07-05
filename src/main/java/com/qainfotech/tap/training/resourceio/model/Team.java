package com.qainfotech.tap.training.resourceio.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.json.simple.JSONArray;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;
import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
    
	 private final String name;
	    private final Integer id;
	    private final List<Individual> members;
	    TeamsJsonReader tobj=new TeamsJsonReader();
	    
	    public Team(Map<String, Object> teamMap){
	    	
	    	
	    	Integer id=null;
	    	String name=null;
	    	List<Individual> members=null;
	    	
	    	for(Map.Entry<String, Object> entry : teamMap.entrySet()){
	    		
	    		if (entry.getKey() == "id") {
	                id = (Integer) entry.getValue();
	            }
	    		if (entry.getKey() == "name") {
	               name =  entry.getValue().toString();
	            }
	    		if (entry.getKey() == "members") {
	               members = (List<Individual>) entry.getValue();
	            }
	    	}
	    		
	    		this.id=id;
	    		this.name=name;
	    		this.members=members;
	    
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
     * @throws ObjectNotFoundException 
     */
    public List<Individual> getActiveMembers() throws ObjectNotFoundException
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
    

