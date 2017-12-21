package m2.ila.fr.istic.ila.vv.mutation.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import m2.ila.fr.istic.ila.vv.target.Target;
import m2.ila.fr.istic.ila.vv.test.Test;

public class TargetsLoader {

	private Properties properties = new Properties();
	
	public TargetsLoader() throws IOException {
		PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
    	this.properties=propertiesLoader.getProperties();
	}
	
	public List<Target> getTargets(){
		
		List<Target> listTargets = new ArrayList<Target>();
		
		List<String> listName = new ArrayList<String>(Arrays.asList(properties.getProperty("TARGETS").split(",")));
		
		for(String name : listName) {
			
			int o=2;
			o=5;
			
			List<String> listTest = new ArrayList<String>(Arrays.asList(properties.getProperty(name).split(",")));
			
			for(String test : listTest) {
				Test tst = new Test(test, properties.getProperty("TEST_DIRECTORY"));
				Target trgt = new Target(name, tst, properties.getProperty("TARGET_DIRECTORY"));
				listTargets.add(trgt);
			}
		}
		
		return listTargets;
	}
}
