package m2.ila.fr.istic.ila.vv.target;

import java.util.ArrayList;
import java.util.List;

import m2.ila.fr.istic.ila.vv.test.Test;

public class Target {

	// Une classe cible, une classe de test
	private String name;
	private List<Test> tests;
	private String path;
	
	public Target(String name) {
		this.name = name;
		this.tests = new ArrayList<Test>();
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean contientTest(String name) {
		
		for(Test t : tests) {
			if(t.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
}
