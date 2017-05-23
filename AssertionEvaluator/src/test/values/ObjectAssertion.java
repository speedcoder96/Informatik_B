package test.values;

public class ObjectAssertion extends ValueAssertion {
	
	protected Object expected;
	protected Object actual;

	protected ObjectAssertion(String name, Object expected, Object actual) {
		super(name);
		this.expected = expected;
		this.actual = actual;
	}

	@Override
	public void eval() {
		if(expected != null) {
			if(expected.equals(actual)) {
				markedAsPassed();
			} else {
				markedAsFailed();
			}
		} else {
			markedAsFailed();
		}
	}

	@Override
	public String toString() {
		return buildSummaryString(String.valueOf(expected), String.valueOf(actual));
	}
	
	public static ObjectAssertion create(String name, Object expected, Object actual) {
		return new ObjectAssertion(name, expected, actual);
	}
	
	public static ObjectAssertion create(Object expected, Object actual) {
		return create(null, expected, actual);
	}


	public static ObjectAssertion createInverted(String name, Object expected, Object actual) {
		ObjectAssertion assertion = create(name, expected, actual);
		assertion.setInverted(true);
		return assertion;
	}

	public static ObjectAssertion createInverted(Object expected, Object actual) {
		return createInverted(null, expected, actual);
	}


}
