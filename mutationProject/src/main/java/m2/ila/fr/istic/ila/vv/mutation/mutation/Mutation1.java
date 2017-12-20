package m2.ila.fr.istic.ila.vv.mutation.mutation;

import javassist.CtMethod;
import m2.ila.fr.istic.ila.vv.target.Target;

public class Mutation1 implements Mutation {

	private Target target;
	private CtMethod method;
	
	public Mutation1(Target target, CtMethod method) {
		this.method = method;
		this.target = target;
	}
}
