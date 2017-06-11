package visitor;


import java.io.File;

/**
 * Created by Rene Sommerfeld on 11.06.2017.
 */
public class List implements ArgumentVisitor {


    /**
     * defines whether or not the subdirectories should be visited
     */
    private boolean recursiveFlag;

    /**
     * defines whether the current visit argument is an option or
     * an argument
     */
    private boolean option;

    /**
     * defines the path
     */
    private String path;

    public static final String RECURSIVE_OPTION = "-r";

    public List() {
        recursiveFlag = false;
        path = null;
    }

    public static void main(String[] args) {
        ArgumentInterpreter interpreter = new ArgumentInterpreter(args);
        interpreter.accept(new List());
    }

    @Override
    public void visitArgumentType(boolean option) {
        //defines that the current visited argument is an option
        this.option = option;
    }

    @Override
    public Result visitArgument(String argument) {
        if(option) {
            option = false;
            switch(argument) {
                case RECURSIVE_OPTION:
                    recursiveFlag = true;
                    return Result.NEXT;
                default:
                    return Result.IGNORE;
            }
        } else {
            path = argument;
            return Result.EXIT;
        }

    }

    @Override
    public void onVisitAllArguments() {
        path = (path == null) ? "." : path;
        File file = new File(path);
        if(file.exists()) {
            System.out.println("Listening the directory " + file + " ...");
            RootFileSystem rfs = new RootFileSystem(file);
            rfs.accept(new ListFileVisitor(file, recursiveFlag));
        } else {
            System.out.println(file + "doesn't exist!");
        }
    }

    /**
     * Interprets the arguments passed via program params.
     */
    public static class ArgumentInterpreter {
        /**
         * References the argument array
         */
        private String[] arguments;

        /**
         * Receives the arguments array from main.
         * @param arguments the arguments array
         */
        public ArgumentInterpreter(String[] arguments) {
            this.arguments = arguments;
        }

        /**
         * Accepts an argument visitor.
         * @param visitor an argument visitor
         */
        public void accept(ArgumentVisitor visitor) {
            if(visitor != null) {
                for(String argument : arguments) {
                    if(argument.matches("-.")){
                        visitor.visitArgumentType(true);
                    } else {
                        visitor.visitArgumentType(false);
                    }
                    ArgumentVisitor.Result result = visitor.visitArgument(argument);
                    switch(result) {
                        case NEXT: case IGNORE:
                            continue;
                        case EXIT:
                            break;
                    }
                }
                visitor.onVisitAllArguments();
            }
        }
    }

}
