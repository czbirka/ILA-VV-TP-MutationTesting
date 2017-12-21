package m2.ila.fr.istic.ila.vv.mutation.mutation;

import javassist.CtMethod;
import m2.ila.fr.istic.ila.vv.target.Target;

public class Mutation1 implements Mutation {

	private Target target;
	private CtMethod method;
	private String mutateCode;
	
	public Mutation1(Target target, CtMethod method, String mutateCode) {
		this.method = method;
		this.target = target;
		this.mutateCode = mutateCode;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public CtMethod getMethod() {
		return method;
	}

	public void setMethod(CtMethod method) {
		this.method = method;
	}

	public String getMutateCode() {
		return mutateCode;
	}

	public void setMutateCode(String mutateCode) {
		this.mutateCode = mutateCode;
	}
	
	public String toString() {
		
		return this.target.getName()+" : "+this.method.getName()+" : "+this.mutateCode;
		
	}
}
