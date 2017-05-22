package test.values;

public class BooleanAssertion extends ValueAssertion {
	
	private boolean expected;
	private boolean actual;
	
	private BooleanAssertion(String name, boolean expected, boolean actual) {
		super(name);
		this.expected = expected;
		this.actual = actual;
	}

	@Override
	public void evaluate() {
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

	public static BooleanAssertion create(boolean expected, boolean actual) {
		return create(null, expected, actual);
	}

	public static BooleanAssertion create(String name, boolean expected, boolean actual) {
		return new BooleanAssertion(name, expected, actual);
	}

	public static BooleanAssertion createInverted(String name, boolean expected, boolean actual) {
		BooleanAssertion bAssertion = create(name, expected, actual);
		bAssertion.setInverted(true);
		return bAssertion;
	}

	public static BooleanAssertion createInverted(boolean expected, boolean actual) {
		return createInverted(null, expected, actual);
	}


	
	
}
