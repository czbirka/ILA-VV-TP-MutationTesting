package m2.ila.fr.istic.ila.vv;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class MyAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(null);
    }
}

// java -javaagent:myagent.jar application.Main