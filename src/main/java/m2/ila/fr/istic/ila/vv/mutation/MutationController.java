package m2.ila.fr.istic.ila.vv.mutation;

import java.util.List;

import m2.ila.fr.istic.ila.vv.BinOpTest;
import m2.ila.fr.istic.ila.vv.mutation.operator.MutationOperator;
import m2.ila.fr.istic.ila.vv.target.Target;
import m2.ila.fr.istic.ila.vv.test.Test;

public class MutationController {

	private String targetPath;
	private String testPath;
	
	private List<Target> targets;
	private List<MutationOperator> mutators;
	private ClassLoader classLoader;
	
	public MutationController(String targetPath, String testPath) {
		this.targetPath = targetPath;
		this.testPath = testPath;
		
		//pour test avant mise en place du loader
		Test test1 = new Test("BinOpTest");
		Test test2 = new Test("BinOpTest2");
		Target target1 = new Target("BinOp");
		target1.addTest(test1);
		target1.addTest(test2);
		
		targets.add(target1);
		
		
		
	}
}
