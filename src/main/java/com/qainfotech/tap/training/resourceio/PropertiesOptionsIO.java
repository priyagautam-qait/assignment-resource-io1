package com.qainfotech.tap.training.resourceio;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.io.InputStream;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
	
	Properties prop = new Properties();
	FileInputStream in=null;
	FileOutputStream out=null;
   
	public Object getOptionValue(String optionKey) throws IOException {
     
    
    	
 
    	
    in=new FileInputStream("C:/Users/priyagautam/git/assignment-resource-io/src/test/resources/options.properties");
    prop.load(in);
    return (Object)prop.getProperty(optionKey);
    	
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
    	
    	
    	
    	out=new FileOutputStream(new File("C:/Users/priyagautam/git/assignment-resource-io/src/test/resources/options.properties"),true);
        prop.setProperty(optionKey,(String) optionValue);            

        
    	
    	
    	
      //  throw new UnsupportedOperationException("Not implemented.");
    }
}
