package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {

	String fileName = "db.yaml";
	ClassLoader classLoader = this.getClass().getClassLoader();

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 */

	public List<Individual> getListOfIndividuals() {
		List<Individual> listofindividual = new ArrayList<>();
		try {
			InputStream inputStream = classLoader.getResourceAsStream("db.yaml");
			Yaml yaml = new Yaml();

			Map<String, Object> result = (Map<String, Object>) yaml.load(inputStream);

			ArrayList ab = (ArrayList) result.get("individuals");
			Map<String, Object> map;
			for (int i = 0; i < ab.size(); i++) {
				map = (Map<String, Object>) ab.get(i);
				Individual abc = new Individual(map);
				listofindividual.add(abc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listofindividual;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		List<Individual> listofindividual = new ArrayList<>();
		try {
			listofindividual = (new TeamsYamlReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Individual individual = null;
		int index, match = 0;
		for (index = 0; index < listofindividual.size(); index++) {
			individual = listofindividual.get(index);
			if (individual.getId().compareTo(id) == 0) {
				match = 1;
				break;
			}
		}
		if (match == 0) {
			throw new ObjectNotFoundException("individual", "id", id.toString());
		} else
			return individual;
	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		List<Individual> listofindividual = new ArrayList<>();
		int index, match = 0;
		Individual individual = null;
		try {
			listofindividual = (new TeamsYamlReader()).getListOfIndividuals();

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (index = 0; index < listofindividual.size(); index++) {
			individual = listofindividual.get(index);
			if (individual.getName().compareTo(name) == 0) {
				match = 1;
				break;
			}
		}

		if (match == 0) {
			throw new ObjectNotFoundException("Individual", "name", name);
		} else
			return individual;
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		List<Individual> lisofindividual = new ArrayList<>();
		try {
			lisofindividual = (new TeamsYamlReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();

		}
		List<Individual> listofinactivemember = new ArrayList<>();
		for (int index = 0; index < lisofindividual.size(); index++) {
			Individual individual = lisofindividual.get(index);
			if (!individual.isActive()) {
				listofinactivemember.add(individual);
			}
		}
		return listofinactivemember;
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {
		List<Individual> listofindividual = new ArrayList<>();
		try {
			listofindividual = (new TeamsYamlReader()).getListOfIndividuals();
		} catch (Exception e) {
			e.printStackTrace();

		}
		List<Individual> listofactivemember = new ArrayList<>();
		for (int index = 0; index < listofindividual.size(); index++) {
			Individual individual = listofindividual.get(index);
			if (individual.isActive()) {
				listofactivemember.add(individual);
			}
		}
		return listofactivemember;
	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ObjectNotFoundException 
	 */
	public List<Team> getListOfTeams() throws FileNotFoundException, IOException, ObjectNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();		
    	File file = new File(classLoader.getResource("db.yaml").getFile());
    			InputStream is = new FileInputStream(file);		
    			Yaml yaml = new Yaml();			
    		Map yamlParsers = (Map) yaml.load(is);	
    		
    		ArrayList teams = (ArrayList) yamlParsers.get("teams");		
    		List<Team> ind=new ArrayList<Team>();
    		TeamsYamlReader reader = new TeamsYamlReader();
    		Team teamObject=null;
    		for(int i=0;i<teams.size();i++){
    			 List<Individual> individualList = new ArrayList<>();
    			Map map1=(Map)teams.get(i);
    		Map<String,Object>map=new HashMap<String,Object>();
       		 map.put("name", map1.get("name"));
       		 map.put("id", ((Integer) map1.get("id")).intValue());
       		 
       		 ArrayList members=(ArrayList)map1.get("members");
       		 
       		for (int index = 0; index < members.size(); index++) {

				individualList.add(reader.getIndividualById(((Integer) members.get(index)).intValue()));

			}
		 
       		 
       		 map.put("members",individualList);
    			
    			teamObject=new Team(map);
    			ind.add(teamObject);
    		}
    		
    		return ind;
	}
}
