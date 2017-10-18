package m2.ila.fr.istic.ila.vv;

import java.lang.Throwable;
import javassist.*;

public class App 
{
    public static void main( String[] args )
    {
    	
        try {
        	ClassPool pool = ClassPool.getDefault();
        	CtClass modifClass = pool.get("m2.ila.fr.istic.ila.vv.BinOp");
        	
//        	modifClass.defrost();
//        	
//        	CtMethod m = modifClass.getDeclaredMethod("addition");
//        	m.insertBefore("{ System.out.println(\"Hello\"); }");
//        	modifClass.writeFile();
        	
        	
        	CtMethod m = CtNewMethod.make(
        	                 "public void hello() { System.out.println(\"Hello\"); }",
        	                 modifClass);
        	modifClass.addMethod(m);
        	modifClass.writeFile();
        	
        }

        catch(Throwable exc) {
            System.out.println("Oh, no! Something went wrong.");
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    	
    }
}
