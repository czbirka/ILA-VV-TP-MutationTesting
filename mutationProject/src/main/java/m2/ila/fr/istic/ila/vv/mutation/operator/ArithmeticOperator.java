package m2.ila.fr.istic.ila.vv.mutation.operator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
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
import m2.ila.fr.istic.ila.vv.Constants;
import m2.ila.fr.istic.ila.vv.Properties;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation;
import m2.ila.fr.istic.ila.vv.mutation.mutation.Mutation1;
import m2.ila.fr.istic.ila.vv.target.Target;

public class ArithmeticOperator implements MutationOperator {

	private List<Mutation> mutations;

	public ArithmeticOperator() {
		mutations = new ArrayList<Mutation>();
	}

	public void doMutate(Mutation mutation) {
		// TODO Auto-generated method stub

	}

	public void checkMutate(Target target, CtMethod method)
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
			while (iterator.hasNext()) {
				// position de l'itérateur
				int pos = iterator.next();

				// Suivant le code de l'opération arihmétique on remplace par son "opposé"
				switch (iterator.byteAt(pos)) {
				case Opcode.IADD:
					iterator.writeByte(Opcode.ISUB, pos);
					previousOpCode = Opcode.IADD;
					break;
				case Opcode.FADD:
					iterator.writeByte(Opcode.FSUB, pos);
					previousOpCode = Opcode.FADD;
					break;
				case Opcode.LADD:
					iterator.writeByte(Opcode.LSUB, pos);
					previousOpCode = Opcode.LADD;
					break;
				case Opcode.DADD:
					iterator.writeByte(Opcode.DSUB, pos);
					previousOpCode = Opcode.DADD;
					break;
				default:
					break;
				} // Switch

				// On génère le nouveau .class dans le repertoire de la classe
				if (classMethod.isFrozen()) {
					classMethod.defrost();
				}
				classMethod.writeFile(Properties.TARGET_CLASSPATH);

				// Lancer les tests
				InvocationRequest request = new DefaultInvocationRequest();
				request.setPomFile(new File(Properties.TARGET_DIRECTORY + "/pom.xml"));
				request.setGoals(Arrays.asList("test"));

				Invoker invoker = new DefaultInvoker();
				invoker.setMavenHome(new File("/usr/share/maven"));
				invoker.execute(request);

				// Editer le rapport

				// Remettre comme c'etait
				if (classMethod.isFrozen()) {
					classMethod.defrost();
				}
				iterator.writeByte(previousOpCode, pos);
				classMethod.writeFile(Properties.TARGET_CLASSPATH);
			} // while
		} // if

//		CtClass returnType = method.getReturnType();
//		if (returnType.equals(CtClass.doubleType)) {
//			System.out.println("name: " + method.getName() + Constants.DOUBLE_TYPE_METHOD);
//			Mutation mutation = new Mutation1(target, method);
//			mutations.add(mutation);
//		}
	}

	public List<Mutation> getMutations() {
		return this.mutations;
	}

}