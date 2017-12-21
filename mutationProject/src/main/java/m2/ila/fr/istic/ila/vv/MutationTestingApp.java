package m2.ila.fr.istic.ila.vv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import m2.ila.fr.istic.ila.vv.mutation.MutationController;
import m2.ila.fr.istic.ila.vv.mutation.loader.PropertiesLoader;

public class MutationTestingApp {

	public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {

		PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
		Properties properties = new Properties();
    	properties=propertiesLoader.getProperties();
    	
		MutationController mutationController = new MutationController(properties.getProperty("TARGET_DIRECTORY"), 
				properties.getProperty("TEST_DIRECTORY"));

		//mutationController.checkMutations();

		//mutationController.doMutations();

	}

}
