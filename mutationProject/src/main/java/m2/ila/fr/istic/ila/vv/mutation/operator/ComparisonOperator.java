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
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;
import m2.ila.fr.istic.ila.vv.mutation.loader.PropertiesLoader;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.target.Target;

public class ComparisonOperator implements MutationOperator {

	private List<Mutation> mutations;
	private Properties properties = new Properties();

	public ComparisonOperator() throws IOException {
		PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
    	this.properties=propertiesLoader.getProperties();
		mutations = new ArrayList<Mutation>();
	}
	
	@Override
	public void doMutate(Mutation mutation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkMutate(CtMethod method)
			throws NotFoundException, CannotCompileException, IOException, BadBytecode, MavenInvocationException {

		CtClass classMethod = method.getDeclaringClass();
		if (classMethod.isFrozen()) {
			classMethod.defrost();
		}
		// On récupère les parametres
		MethodInfo methodInfo = method.getMethodInfo();
		CodeAttribute attributs = methodInfo.getCodeAttribute();

		if (attributs != null) {

			// On itère sur les paramètres
			CodeIterator iterator = attributs.iterator();
			int previousOpCode = -1;
			int lastCode = -2;
			int actualCode = -2;
			String modif = "";
			while (iterator.hasNext()) {
				
				// position de l'itérateur
				int pos = iterator.next();
				
				lastCode = actualCode;
				actualCode = iterator.byteAt(pos);
				
				//
				switch (iterator.byteAt(pos)) {
				//on remplace > par >=
				case Opcode.IFLE:
					if(lastCode == Opcode.DCMPL) {
						iterator.writeByte(Opcode.IFLT, pos);
						previousOpCode = Opcode.IFLE;
						modif = "> remplacé par >=";
					}
					break;
				//on remplace >= par >
				case Opcode.IFLT:
					if(lastCode == Opcode.DCMPL) {
						iterator.writeByte(Opcode.IFLE, pos);
						previousOpCode = Opcode.IFLT;
						modif = ">= remplacé par >";
					}
					break;
				//on remplace < par <=
				case Opcode.IFGE:
					if(lastCode == Opcode.DCMPG) {
						iterator.writeByte(Opcode.IFGT, pos);
						previousOpCode = Opcode.IFGE;
						modif = "< remplacé par <=";
					}
					break;
				//on remplace <= par <
				case Opcode.IFGT:
						if(lastCode == Opcode.DCMPG) {
						iterator.writeByte(Opcode.IFGE, pos);
						previousOpCode = Opcode.IFGT;
						modif = "<= remplacé par <";
					}
					break;
				//on remplace == par !=
				case Opcode.IFNE:
					if(lastCode == Opcode.DCMPL) {
						iterator.writeByte(Opcode.IFEQ, pos);
						previousOpCode = Opcode.IFNE;
						modif = "== remplacé par !=";
					}
					break;
				//on remplace != par ==
				case Opcode.IFEQ:
					if(lastCode == Opcode.DCMPL) {
						iterator.writeByte(Opcode.IFNE, pos);
						previousOpCode = Opcode.IFEQ;
						modif = "!= remplacé par ==";
					}
					break;
				default:
					break;
				} // Switch
				
				if(!modif.equals("")) {
					
					// On génère le nouveau .class dans le repertoire de la classe
					if (classMethod.isFrozen()) {
						classMethod.defrost();
					}
					classMethod.writeFile(properties.getProperty("TARGET_DIRECTORY"));
	
					// Lancer les tests
					InvocationRequest request = new DefaultInvocationRequest();
					request.setPomFile(new File(properties.getProperty("PROJECT_DIRECTORY") + "/pom.xml"));
					request.setGoals(Arrays.asList("test"));
	
					Invoker invoker = new DefaultInvoker();
					invoker.setMavenHome(new File("/usr/share/maven"));
					InvocationResult result = invoker.execute(request);
				
					// Remettre comme c'etait
					if (classMethod.isFrozen()) {
						classMethod.defrost();
					}
					iterator.writeByte(previousOpCode, pos);
					//classMethod.writeFile(Properties.TARGET_CLASSPATH);
					classMethod.writeFile(properties.getProperty("TARGET_DIRECTORY"));
					
					//stockage résultat
					Mutation mutation = new Mutation(classMethod, method, modif);
					if ( result.getExitCode() == 0 ) {
				        mutation.setMutationFound(false);
				    } else {
				        mutation.setMutationFound(true);;
				    }
					mutations.add(mutation);
					
					modif="";
				}//if modif
				
			} // while
	
		} // if

	}

	@Override
	public List<Mutation> getMutations() {
		// TODO Auto-generated method stub
		return null;
	}

}
