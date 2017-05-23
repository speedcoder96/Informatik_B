package test.values;

public class CharAssertion extends ValueAssertion {
	
	private char expected;
	private char actual;

	private CharAssertion(String name, char expected, char actual) {
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
	
	public static CharAssertion create(String name, char expected, char actual) {
		return new CharAssertion(name, expected, actual);
	}

	public static CharAssertion create(char expected, char actual) {
		return create(null, expected, actual);
	}

	public static CharAssertion createInverted(String name, char expected, char actual) {
		CharAssertion assertion = create(name, expected, actual);
		assertion.setInverted(true);
		return assertion;
	}

	public static CharAssertion createInverted(char expected, char actual) {
		return createInverted(null, expected, actual);
	}
}
