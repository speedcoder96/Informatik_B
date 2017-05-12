package test.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rene Sommerfeld
 * @author Tim Adam
 * @version 1.0 08.05.2017
 * 
 * The AssertionEvaluator class is a singleton.  
 * If all assertions are registered to this class the call of
 * the printSummary method provides the information about whether 
 * or not a test has passed and how many have passed. 
 */
public class AssertionEvaluator {

	/**
	 * the singleton instance AssertionEvaluator
	 */
	private static AssertionEvaluator instance;
	
	/**
	 * the list of all registered assertions
	 */
	private List<Assertion> assertions;
	
	/**
	 * private constructor in order to fulfill all
	 * standards of singleton pattern
	 */
	private AssertionEvaluator() {
		assertions = new ArrayList<Assertion>();
	}
	
	/**
	 * Returns the singleton instance of this class
	 * @return the singleton instance
	 */
	public static AssertionEvaluator getInstance() {
		if(instance == null) {
			instance = new AssertionEvaluator();
		}
		return instance;
	}
	
	/**
	 * Evaluates an assertion and registers it to the
	 * assertion list.
	 * @param assertion
	 */
	public void evaluate(Assertion assertion) {
		if(assertion != null) {
			//if assertion is not labeled with a name, a name is set
			//with prefix Test and the ordinal number at which it appears
			//in the list of assertions
			if(assertion.getName() == null) {
				assertion.setName("Test " + (assertions.size() + 1));
			}
			//evaluates the assertion and registers it to the list
			assertion.evaluate();
			assertions.add(assertion);
		}
	}
	
	/**
	 * Prints a summary of all registered assertions
	 * of the assertion list.
	 */
	public void printSummary() {
		if(assertions.size() != 0) {
			for(Assertion assertion : assertions) {
				//calls the toString method of assertion
				System.out.println(assertion);
				System.out.println("#########");
			}
		} else {
			System.err.println("There is no test to evaluate!");
		}
	}
	
	/**
	 * Returns the number of assertions registered
	 * @return the number of assertions 
	 */
	public int assertionCount() {
		return assertions.size();
	}
	
}
