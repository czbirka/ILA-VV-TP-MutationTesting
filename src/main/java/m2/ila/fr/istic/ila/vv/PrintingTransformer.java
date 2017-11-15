package m2.ila.fr.istic.ila.vv;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

//un exemple de tranformer
public class PrintingTransformer implements ClassFileTransformer {
	
	public byte[] transform(ClassLoader loader, String fullyQualifiedClassName, Class classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classofileBuffer) throws IllegalClassFormatException {
		
		String className = fullyQualifiedClassName.replaceAll(".*/", "");
		String packaging = fullyQualifiedClassName.replaceAll("/[a-zA-Z$0-9_]*$", "");
		System.out.printf("Class: %s in: %sn", className, packaging);
		return null;
	}
}
