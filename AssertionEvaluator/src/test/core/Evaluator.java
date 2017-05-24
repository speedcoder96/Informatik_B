package test.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rene Sommerfeld
 * @author Tim Adam
 * @version 1.0 08.05.2017
 * 
 * Evaluator
 */
public class Evaluator {

	/**
	 * the singleton instance Evaluator
	 */
	private static Evaluator instance;
	
	/**
	 * the list of all registered assertions
	 */
	private List<Assertion> assertions;
	
	/**
	 * private constructor in order to fulfill all
	 * standards of singleton pattern
	 */
	private Evaluator() {
		assertions = new ArrayList<Assertion>();
	}
	
	/**
	 * Returns the singleton instance of this class
	 * @return the singleton instance
	 */
	public static Evaluator getInstance() {
		if(instance == null) {
			instance = new Evaluator();
		}
		return instance;
	}
	
	/**
	 * Evaluates an assertion and registers it to the
	 * assertion list.
	 * @param assertion
	 */
	public void eval(Assertion assertion) {
		if(assertion != null) {
			//if assertion is not labeled with a name, a name is set
			//with prefix Test and the ordinal number at which it appears
			//in the list of assertions
			if(assertion.getName() == null) {
				assertion.setName("Test " + (assertions.size() + 1));
			}
			//evaluates the assertion and registers it to the list
			assertion.eval();
			assertions.add(assertion);
		}
	}

    /**
     * Prints stats of all registered assertions
     */
	public void printNumericStats() {
		int failCount = 0;
		if(assertions.size() != 0) {
			for(Assertion assertion : assertions) {
				if(assertion.isEvaluated()) {
					if(!assertion.hasPassed()) {
						failCount++;
					}
				}
			}
		}
		int count = assertionCount();
		float percentage = ((count - failCount) * 100.0f) / count;
		System.out.printf("Tests eval.:\t%d", count);
		System.out.println();
		System.out.printf("Tests pass.:\t%d / %d", count - failCount, count);
		System.out.println();
		System.out.printf("Tests fail.:\t%d / %d", failCount, count);
        System.out.println();
        System.out.printf("Tests perc.:\t%f", percentage);
		System.out.println();
	}
	
	/**
	 * Prints a summary of all registered assertions
	 * of the assertion list.
	 */
	public void printSummary() {
		if(assertions.size() != 0) {
			for(Assertion assertion : assertions) {
                System.out.println(assertion);
                System.out.println("#########");
			}
		} else {
			System.err.println("There is no test to eval!");
		}
		printNumericStats();
	}

    /**
     * Prints only the passed registered assertions.
     */
	public void printOnlyPassed() {
	    if(assertions.size() != 0) {
            for(Assertion assertion : assertions) {
                if(assertion.hasPassed()) {
                    System.out.println(assertion);
                    System.out.println("#########");
                }
            }
        } else {
            System.err.println("There is not test to print!");
        }
    }

    /**
     * Prints only the failed registered assertions.
     */
    public void printOnlyFailed() {
        if(assertions.size() != 0) {
            for(Assertion assertion : assertions) {
                if(!assertion.hasPassed()) {
                    System.err.println(assertion);
                    System.err.println("#########");
                }
            }
        } else {
            System.err.println("There is not test to print!");
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
