
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ltudor on 5/8/15.
 */
public class DummyRunnable implements Runnable {
    private static final AtomicInteger CNT = new AtomicInteger(1);

    @Override
    public void run() {
        System.out.println("******************************************");
        System.out.println("****************** RUNNING " + CNT.getAndIncrement() + "***************");
        System.out.println("******************************************");

    }
}
