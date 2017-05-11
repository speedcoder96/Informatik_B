package test.values;

public class IntAssertion extends ValueAssertion {

	private int expected;
	private int actual;
	
	private IntAssertion(String name, int expected, int actual) {
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
	
	public static IntAssertion create(String name, int expected, int actual) {
		return new IntAssertion(name, expected, actual);
	}
	
	public static IntAssertion create(int expected, int actual) {
		return create(null, expected, actual);
	}



}
