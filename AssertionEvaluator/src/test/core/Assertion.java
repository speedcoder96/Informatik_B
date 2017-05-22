package test.core;

/**
 * 
 * @author Rene Sommerfeld
 * @author Tim Adam
 * @version 1.0 08.05.2017
 * 
 * This abstract class defines every attribute and method
 * an assertion should have, in order to get evaluated by
 * AssertionEvaluator class.
 */
public abstract class Assertion {
	
	public static final String NAME_TAG = "[name]";
	public static final String EXPECTED_TAG = "[exp]";
	public static final String ACTUAL_TAG = "[act]";
	public static final String STATUS_TAG = "[stat]";
	
	/**
	 * Status for a passed assertion
	 */
	public static final String STATUS_PASSED = "PASSED";
	
	/**
	 * Status for a failed assertion
	 */
	public static final String STATUS_FAILED = "FAILED";

	/**
	 * the name of the assertion
	 */
	private String name;
	
	/**
	 * the status of an assertion whether or not it has passed
	 * or failed
	 */
	private boolean passed;

	/**
	 * inverts the result of an assertion
	 */
	private boolean inverted;
	
	/**
	 * holds the state of whether or not an assertion is evaluated
	 */
	private boolean evaluated;
	
	/**
	 * the string representation of the assertion status
	 */
	private String status;
	
	/**
	 * Constructor of Assertion.
	 * Every assertion has to have at least a name
	 * @param name the name of the assertion
	 */
	public Assertion(String name) {
		this.name = name;
		this.evaluated = false;
		this.inverted = false;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public boolean isInverted() {
		return inverted;
	}
	
	/**
	 * Sets the name of the assertion
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the assertion
	 * @return the name of the assertion
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the status of the assertion
	 * @return the status of the assertion
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Returns whether or not an assertion
	 * has passed or failed
	 * @return true if passed, false otherwise
	 */
	public boolean hasPassed() {
		return passed;
	}
	
	/**
	 * Returns whether or not an assertion
	 * is evaluated
	 * @return true if assertion is evaluated, otherwise false
	 */
	public boolean isEvaluated() {
		return evaluated; 
	}
	
	/**
	 * Marks an assertion as passed
	 */
	public void markedAsPassed() {
		passed = true;
		status = Assertion.STATUS_PASSED;
		evaluated = true;
	}
	
	/**
	 * Marks an assertion as failed
	 */
	public void markedAsFailed() {
		passed = false;
		status = Assertion.STATUS_FAILED;
		evaluated = true;
	}
	
	/**
	 * Abstract methods all assertion have to implement
	 */
	public abstract void evaluate();
	public abstract String toString();
	
}
