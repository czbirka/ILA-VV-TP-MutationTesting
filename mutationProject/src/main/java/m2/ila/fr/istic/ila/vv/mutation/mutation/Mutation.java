package m2.ila.fr.istic.ila.vv.mutation.mutation;

import javassist.CtMethod;
import m2.ila.fr.istic.ila.vv.target.Target;

public interface Mutation {

	public String toString();
	public Target getTarget();
	public CtMethod getMethod();
	public String getMutateCode();
	
}
