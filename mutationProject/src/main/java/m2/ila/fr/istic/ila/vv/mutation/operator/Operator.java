package m2.ila.fr.istic.ila.vv.mutation.operator;

public enum Operator {
	
	ARITHMETIC_OPERATOR("ARITHMETIC_OPERATOR"), 
	BOOLEAN_OPERATOR("BOOLEAN_OPERATOR"), 
	VOID_OPERATOR("VOID_OPERATOR"), 
	DOUBLE_OPERATOR("DOUBLE_OPERATOR"), 
	COMPARISON_OPERATOR("COMPARISON_OPERATOR");

	private String name = "";

	Operator(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
	
}
