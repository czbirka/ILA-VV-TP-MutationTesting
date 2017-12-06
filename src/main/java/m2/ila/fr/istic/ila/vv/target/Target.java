package m2.ila.fr.istic.ila.vv.target;

import java.util.List;

import m2.ila.fr.istic.ila.vv.test.Test;

public class Target {

	private String name;
	private List<Test> tests;
	
	public Target(String name) {
		this.name = name;
	}
	
	public void addTest(Test test)
	{
		this.tests.add(test);
	}
	
}
