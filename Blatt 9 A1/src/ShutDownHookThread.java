import java.io.File;
import java.util.Timer;

/**
 * Created by Rene Sommerfeld on 20.06.2017.
 * Only ensures that the timer is canceled when the program
 * gets terminated via CTRL-C. This thread gets added as a
 * ShutDownHook to the Runtime.
 */
public class ShutDownHookThread extends Thread {

    /**
     * the file where the FileSizeUpdateTimerTask listens to
     */
    private File file;
    /**
     * the timer that schedules the TimerTask
     */
    private Timer timer;

    /**
     * Constructor
     * @param timer the timer
     * @param file the file
     */
    public ShutDownHookThread(Timer timer, File file) {
        this.timer = timer;
        this.file = file;
    }

    @Override
    public void run() {
        timer.cancel();
        System.out.println("No longer listen to " + file.getName());
    }
}
