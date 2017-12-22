package m2.ila.fr.istic.ila.vv.mutation.mutation;

import javassist.CtClass;
import javassist.CtMethod;

public class Mutation{

	private CtClass classe;
	private CtMethod method;
	private String modif;
	private boolean mutationFound=false;
	
	public Mutation(CtClass classe, CtMethod method, String modif) {
		this.method = method;
		this.classe = classe;
		this.modif = modif;
	}

	public CtMethod getMethod() {
		return method;
	}

	public void setMethod(CtMethod method) {
		this.method = method;
	}

	public CtClass getClasse() {
		return classe;
	}

	public void setClasse(CtClass classe) {
		this.classe = classe;
	}

	public boolean isMutationFound() {
		return mutationFound;
	}

	public void setMutationFound(boolean mutationFound) {
		this.mutationFound = mutationFound;
	}

	
	public String getModif() {
		return modif;
	}

	public void setModif(String modif) {
		this.modif = modif;
	}

	public String toString() {
		
		String txt = "classe: "+ this.classe.getSimpleName() + "     méthode: " +this.method.getName() + "\n";
		txt += "mutation: " + this.getModif() + "\n";
		if(this.mutationFound) {
			txt += "MUTATION FOUND : ok\n";
		}
		else {
			txt += "MUTATION NOT FOUND !!!!!!! : DANGER\n";
		}
		return txt;
	}
}
