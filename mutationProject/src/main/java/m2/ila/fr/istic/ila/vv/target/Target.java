package m2.ila.fr.istic.ila.vv.target;

import m2.ila.fr.istic.ila.vv.test.Test;

public class Target {

	// Une classe cible, une classe de test
	private String name;
	private Test test;
	private String path;

	public Target(String name, Test test, String path) {
		this.name = name;
		this.test = test;
		this.path = path;
	}

	public Test getTests() {
		return test;
	}
	
	public void setTest(Test test) {
		this.test = test;
	}

	public String getName() {
		return name;
	}

}
