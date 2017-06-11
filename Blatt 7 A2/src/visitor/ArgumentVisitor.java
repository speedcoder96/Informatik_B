package visitor;

/**
 * Created by Rene Sommerfeld on 11.06.2017.
 */
public interface ArgumentVisitor {

    public static enum Result {
        NEXT, IGNORE, EXIT
    }

    public void visitArgumentType(boolean option);
    public ArgumentVisitor.Result visitArgument(String argument);
    public void onVisitAllArguments();

}
