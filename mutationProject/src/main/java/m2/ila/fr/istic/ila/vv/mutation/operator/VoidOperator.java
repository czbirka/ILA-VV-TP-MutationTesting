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
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;
import m2.ila.fr.istic.ila.vv.Constants;
import m2.ila.fr.istic.ila.vv.mutation.loader.PropertiesLoader;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation1;
import m2.ila.fr.istic.ila.vv.target.Target;

public class VoidOperator implements MutationOperator {

	private List<Mutation> mutations;
	private CtMethod original;
	private CtMethod modified;
	private Properties properties = new Properties();

	public VoidOperator() throws IOException {
		PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
    	this.properties=propertiesLoader.getProperties();
		mutations = new ArrayList<Mutation>();
	}
	
	public void doMutate(Mutation mutation) {
		// TODO Auto-generated method stub

	}

	public void checkMutate(Target target, CtMethod method) throws NotFoundException, CannotCompileException, IOException, MavenInvocationException {
		
		CtClass returnType = method.getReturnType();
		if (returnType.equals(CtClass.voidType)) {
			
			CtClass classMethod = method.getDeclaringClass();
			if (classMethod.isFrozen()) {
				classMethod.defrost();
			}
			
			modified = method;
			original = CtNewMethod.copy(method, method.getDeclaringClass(), null);
			
			method.setBody("{}");
			
			classMethod.writeFile(properties.getProperty("TARGET_DIRECTORY"));
			
			//test
			System.out.println("pouet >");
			// Lancer les tests
			InvocationRequest request = new DefaultInvocationRequest();
			//request.setPomFile(new File(Properties.TARGET_DIRECTORY + "/pom.xml"));
			request.setPomFile(new File(properties.getProperty("PROJECT_DIRECTORY") + "/pom.xml"));
			request.setGoals(Arrays.asList("test"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File("/usr/share/maven"));
			InvocationResult result = invoker.execute(request);			
			
			
			
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
