import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rene Sommerfeld on 20.06.2017.
 * SizeUpdateTimerTaskCreator is the main class that is able
 * to start a timer task on the console
 */
public class SizeUpdateTimerTaskCreator {

    public static void main(String[] args) {
        System.out.println(args[0]);
        //ensures that only a program start containing one argument
        //can execute a FileSizeUpdateTimerTask
        if(args.length == 1) {
            File file = new File(args[0]);
            if(file.exists()) {
                //creating timer task and the corresponding timer to schedule the
                //timer task
                TimerTask task = new FileSizeUpdateTimerTask(file);
                Timer timer = new Timer();
                //ensure that the timer gets canceled when the program is terminated
                Runtime.getRuntime().addShutdownHook(new ShutDownHookThread(timer, file));
                //schedule the task on delay 0ms and period 1000ms
                timer.schedule(task, 0, 1000);
            } else {
                //error case
                System.err.println("File doesn't exist");
                System.out.println("Use java SizeUpdateTimerTaskCreator File/Directory");
            }
        } else {
            //error case
            System.err.println("Program needs a path for a file object to listen to!");
            System.out.println("Use java SizeUpdateTimerTaskCreator File/Directory");
        }
    }

}
