package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import m2.ila.fr.istic.ila.vv.mutation.loader.PropertiesLoader;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;

public class BooleanOperator implements MutationOperator {

	private List<Mutation> mutations;
	private CtMethod original;
	private CtMethod modified;
	private Properties properties = new Properties();

	public BooleanOperator() throws IOException {
		PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
    	this.properties=propertiesLoader.getProperties();
		mutations = new ArrayList<Mutation>();
	}
	
	public void checkMutate(CtMethod method) 
			throws NotFoundException, CannotCompileException, IOException, MavenInvocationException {
		
		CtClass returnType = method.getReturnType();
		if (returnType.equals(CtClass.booleanType)) {
			
			CtClass classMethod = method.getDeclaringClass();
			if (classMethod.isFrozen()) {
				classMethod.defrost();
			}
			
			modified = method;
			original = CtNewMethod.copy(method, method.getDeclaringClass(), null);
			
			method.setBody("{return true;}");
			
			classMethod.writeFile(properties.getProperty("TARGET_DIRECTORY"));
			
			// Lancer les tests
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File(properties.getProperty("PROJECT_DIRECTORY") + "/pom.xml"));
			request.setGoals(Arrays.asList("test"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File("/usr/share/maven"));
			InvocationResult result = invoker.execute(request);		
			
			//stockage résultat
			Mutation mutation = new Mutation(classMethod, method, "{return true;}");
			if ( result.getExitCode() == 0 ) {
		        mutation.setMutationFound(false);
		    } else {
		        mutation.setMutationFound(true);
		    }
			mutations.add(mutation);
			
			if (classMethod.isFrozen()) {
				classMethod.defrost();
			}
			
			method.setBody("{return false;}");
			
			classMethod.writeFile(properties.getProperty("TARGET_DIRECTORY"));
			
			// Lancer les tests
			request = new DefaultInvocationRequest();
			request.setPomFile(new File(properties.getProperty("PROJECT_DIRECTORY") + "/pom.xml"));
			request.setGoals(Arrays.asList("test"));

			invoker = new DefaultInvoker();
			invoker.setMavenHome(new File("/usr/share/maven"));
			result = invoker.execute(request);
			
			//stockage résultat
			mutation = new Mutation(classMethod, method, "{return false;}");
			if ( result.getExitCode() == 0 ) {
		        mutation.setMutationFound(false);
		    } else {
		        mutation.setMutationFound(true);;
		    }
			mutations.add(mutation);
			
			if(modified.getDeclaringClass().isFrozen()) {
				modified.getDeclaringClass().defrost();
			}
	            
	       
	        modified.setBody(original, null);
	        
	        classMethod = modified.getDeclaringClass();
	classMethod.writeFile(properties.getProperty("TARGET_DIRECTORY"));
		}
	}

	public List<Mutation> getMutations() {
		return mutations;
	}

}
