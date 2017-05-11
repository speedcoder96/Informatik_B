package test.values;

public class LongAssertion extends ValueAssertion {
	
	private long expected;
	private long actual;

	private LongAssertion(String name, long expected, long actual) {
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
	
	public static LongAssertion create(String name, long expected, long actual) {
		return new LongAssertion(name, expected, actual);
	}
	
	public static LongAssertion create(long expected, long actual) {
		return create(null, expected, actual);
	}


}
