package test.values;

public class DoubleAssertion extends ValueAssertion {

	private double expected;
	private double actual;
	
	private DoubleAssertion(String name, double expected, double actual) {
		super(name);
		this.expected = expected;
		this.actual = actual;
	}

	@Override
	public void eval() {
		if(expected == actual) {
			markedAsPassed();
		} else {
			markedAsFailed();
		}
	}
	
	@Override
	public String toString() {
		return buildSummaryString(String.valueOf(expected), String.valueOf(actual));
	}
	
	public static DoubleAssertion create(String name, double expected, double actual) {
		return new DoubleAssertion(name, expected, actual);
	}
	
	public static DoubleAssertion create(double expected, double actual) {
		return create(null, expected, actual);
	}

	public static DoubleAssertion createInverted(String name, double expected, double actual) {
		DoubleAssertion assertion = create(name, expected, actual);
		assertion.setInverted(true);
		return assertion;
	}

	public static DoubleAssertion createInverted(double expected, double actual) {
		return createInverted(null, expected, actual);
	}
	

}
