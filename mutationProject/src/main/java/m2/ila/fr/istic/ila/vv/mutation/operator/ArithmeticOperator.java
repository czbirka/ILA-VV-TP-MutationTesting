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
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.target.Target;

public class ArithmeticOperator implements MutationOperator {

	private List<Mutation> mutations;
	private Properties properties = new Properties();

	public ArithmeticOperator() throws IOException {
		PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
    	this.properties=propertiesLoader.getProperties();
		mutations = new ArrayList<Mutation>();
	}

	public void doMutate(Mutation mutation) {
		// TODO Auto-generated method stub

	}

	public void checkMutate(CtMethod method)
			throws NotFoundException, CannotCompileException, IOException, 
			BadBytecode, MavenInvocationException {

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
			String modif = "";
			while (iterator.hasNext()) {
				// position de l'itérateur
				int pos = iterator.next();

				// Suivant le code de l'opération arihmétique on remplace par son "opposé"
				switch (iterator.byteAt(pos)) {
				case Opcode.IADD:
					iterator.writeByte(Opcode.ISUB, pos);
					previousOpCode = Opcode.IADD;
					modif = "+ remplacé par - (int)";
					break;
				case Opcode.ISUB:
					iterator.writeByte(Opcode.IADD, pos);
					previousOpCode = Opcode.ISUB;
					modif = "- remplacé par + (int)";
					break;
				case Opcode.FADD:
					iterator.writeByte(Opcode.FSUB, pos);
					previousOpCode = Opcode.FADD;
					modif = "+ remplacé par - (float)";
					break;
				case Opcode.FSUB:
					iterator.writeByte(Opcode.FADD, pos);
					previousOpCode = Opcode.FSUB;
					modif = "- remplacé par + (float)";
					break;
				case Opcode.LADD:
					iterator.writeByte(Opcode.LSUB, pos);
					previousOpCode = Opcode.LADD;
					modif = "+ remplacé par - (long)";
					break;
				case Opcode.LSUB:
					iterator.writeByte(Opcode.LADD, pos);
					previousOpCode = Opcode.LSUB;
					modif = "- remplacé par + (long)";
					break;
				case Opcode.DADD:
					iterator.writeByte(Opcode.DSUB, pos);
					previousOpCode = Opcode.DADD;
					modif = "+ remplacé par - (double)";
					break;
				case Opcode.DSUB:
					iterator.writeByte(Opcode.DADD, pos);
					previousOpCode = Opcode.DSUB;
					modif = "- remplacé par + (double)";
					break;
				case Opcode.IMUL:
					iterator.writeByte(Opcode.IDIV, pos);
					previousOpCode = Opcode.IMUL;
					modif = "* remplacé par / (int)";
					break;
				case Opcode.IDIV:
					iterator.writeByte(Opcode.IMUL, pos);
					previousOpCode = Opcode.IDIV;
					modif = "/ remplacé par * (int)";
					break;
				case Opcode.FMUL:
					iterator.writeByte(Opcode.FDIV, pos);
					previousOpCode = Opcode.FMUL;
					modif = "* remplacé par / (float)";
					break;
				case Opcode.FDIV:
					iterator.writeByte(Opcode.FMUL, pos);
					previousOpCode = Opcode.FDIV;
					modif = "/ remplacé par * (float)";
					break;
				case Opcode.LMUL:
					iterator.writeByte(Opcode.LDIV, pos);
					previousOpCode = Opcode.LMUL;
					modif = "* remplacé par / (long)";
					break;
				case Opcode.LDIV:
					iterator.writeByte(Opcode.LMUL, pos);
					previousOpCode = Opcode.LDIV;
					modif = "/ remplacé par * (long)";
					break;
				case Opcode.DMUL:
					iterator.writeByte(Opcode.DDIV, pos);
					previousOpCode = Opcode.DMUL;
					modif = "* remplacé par / (double)";
					break;
				case Opcode.DDIV:
					iterator.writeByte(Opcode.DMUL, pos);
					previousOpCode = Opcode.DDIV;
					modif = "/ remplacé par * (double)";
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

	public List<Mutation> getMutations() {
		return this.mutations;
	}

}
