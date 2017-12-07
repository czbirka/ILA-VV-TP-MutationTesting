package m2.ila.fr.istic.ila.vv.target;

import java.util.ArrayList;
import java.util.List;

import m2.ila.fr.istic.ila.vv.test.Test;

public class Target {

	private String name;
	private List<Test> tests;
	
	public Target(String name) {
		this.name = name;
		this.tests = new ArrayList<Test>();
	}
	
	

	public List<Test> getTests() {
		return tests;
	}



	public String getName() {
		return name;
	}


}
