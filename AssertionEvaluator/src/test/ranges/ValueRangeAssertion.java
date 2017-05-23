package test.ranges;

import test.core.Assertion;

/**
 * Created by Rene Sommerfeld on 22.05.2017.
 */
public abstract class ValueRangeAssertion extends Assertion {

    private static final String FROM_TAG = "[from]";
    private static final String TO_TAG = "[to]";
    private static final String TO_STRING_FORMAT = "Test:\t[name]\nFrom:\t[from]\nTo :\t[to]\nAct:\t[act]\nStat:\t[stat]";

    /**
     * Constructor of Assertion.
     * Every assertion has to have at least a name
     *
     * @param name the name of the assertion
     */
    public ValueRangeAssertion(String name) {
        super(name);
    }

    public String buildSummaryString(String from, String to, String actual) {
        String nameTag = (getName() != null) ? getName() : "NO NAME";
        String fromTag = (from != null) ? from : "NO FROM VALUE";
        String toTag = (to != null) ? to : "NO TO VALUE";
        String actualTag = (actual != null) ? actual : "NO ACTUAL VALUE";
        String statusTag = (getStatus() != null) ? getStatus() : "NO STATUS";
        return TO_STRING_FORMAT
                .replace(NAME_TAG, nameTag)
                .replace(FROM_TAG, fromTag)
                .replace(TO_TAG, toTag)
                .replace(ACTUAL_TAG, actualTag)
                .replace(STATUS_TAG, statusTag);
    }

}
