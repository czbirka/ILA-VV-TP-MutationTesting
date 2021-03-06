package m2.ila.fr.istic.ila.vv.mutation.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import m2.ila.fr.istic.ila.vv.mutation.operator.ArithmeticOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.BooleanOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.ComparisonOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.MutationOperator;
import m2.ila.fr.istic.ila.vv.mutation.operator.Operator;
import m2.ila.fr.istic.ila.vv.mutation.operator.VoidOperator;

public class OperatorsLoader {

	private Properties properties = new Properties();
	
	
	public OperatorsLoader() throws IOException {
		PropertiesLoader propertiesLoader;
		propertiesLoader = PropertiesLoader.getInstance();
    	this.properties=propertiesLoader.getProperties();
	}
	
	public List<MutationOperator> getOperators() throws IOException{
		
		List<MutationOperator> listOperators = new ArrayList<MutationOperator>();
		
		List<String> listName = new ArrayList<String>(Arrays.asList(properties.getProperty("OPERATORS").split(",")));
		
		for(String name : listName) {
			if (name.equals(Operator.ARITHMETIC_OPERATOR.toString())) {
				listOperators.add(new ArithmeticOperator());
			}
			else if (name.equals(Operator.BOOLEAN_OPERATOR.toString())) {
				listOperators.add(new BooleanOperator());
			}
			else if (name.equals(Operator.VOID_OPERATOR.toString())) {
				listOperators.add(new VoidOperator());
			}
			else if (name.equals(Operator.COMPARISON_OPERATOR.toString())) {
				listOperators.add(new ComparisonOperator());
			}
		}
		
		return listOperators;
	}
	
}
