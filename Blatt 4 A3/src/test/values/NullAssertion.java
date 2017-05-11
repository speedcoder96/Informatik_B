package test.values;

public class NullAssertion extends ValueAssertion {

	private Object actual;
	
	private NullAssertion(String name, Object actual) {
		super(name);
		this.actual = actual;
	}

	@Override
	public void evaluate() {
		if(actual == null) {
			markedAsPassed();
		} else {
			markedAsFailed();
		}
	}

	@Override
	public String toString() {
		return buildSummaryString("null", String.valueOf(actual));
	}
	
	public static NullAssertion create(String name, Object actual) {
		return new NullAssertion(name, actual);
	}
	 
	public static NullAssertion create(Object actual) {
		return create(null, actual);
	}
	
}
