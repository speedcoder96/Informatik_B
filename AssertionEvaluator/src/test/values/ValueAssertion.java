package test.values;

import test.core.Assertion;

public abstract class ValueAssertion extends Assertion {

	public static final String TO_STRING_FORMAT = "Test:\t[name]\nExp:\t[exp]\nAct:\t[act]\nStat:\t[stat]";
	
	public ValueAssertion(String name) {
		super(name);
	}
	
	public String buildSummaryString(String expected, String actual) {
		String nameTag = (getName() != null) ? getName() : "NO NAME";
		String expectedTag = (expected != null) ? expected : "NO EXPECTED VALUE";
		String actualTag = (actual != null) ? actual : "NO ACTUAL VALUE";
		String statusTag = (getStatus() != null) ? getStatus() : "NO STATUS";
		return TO_STRING_FORMAT
				.replace(NAME_TAG, nameTag)
				.replace(EXPECTED_TAG, expectedTag)
				.replace(ACTUAL_TAG, actualTag)
				.replace(STATUS_TAG, statusTag);
	}

}
