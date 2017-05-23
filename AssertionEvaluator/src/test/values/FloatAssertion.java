package test.values;

public class FloatAssertion extends ValueAssertion {
	
	private float expected;
	private float actual;

	private FloatAssertion(String name, float expected, float actual) {
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
	
	public static FloatAssertion create(String name, float expected, float actual) {
		return new FloatAssertion(name, expected, actual);
	}
	
	public static FloatAssertion create(float expected, float actual) {
		return create(null, expected, actual);
	}

	public static FloatAssertion createInverted(String name, float expected, float actual) {
		FloatAssertion assertion = create(name, expected, actual);
		assertion.setInverted(true);
		return assertion;
	}

	public static FloatAssertion createInverted(float expected, float actual) {
		return createInverted(null, expected, actual);
	}


}
