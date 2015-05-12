import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory to give the threads in a pool a name.
 * The names generated follow the format: basename.NNN where NNN is an incrementing counter.
 * Also a different format can be specified.
 * TODO: Unit test
 *
 * @author ltudor
 */
public class NamedThreadFactory implements ThreadFactory {
    /**
     * Default format for naming.
     */
    public static final String DEF_FORMAT = "%s.%03d";
    /**
     * Format of the name. If not specified in constructor, then {@link #DEF_FORMAT} will be used.
     * This is a format string for <code>String.format()</code>. The first argument passed in is always
     * basename and the second one is the counter.
     */
    private final String format;
    /**
     * Base name for all the threads in this pool.
     */
    private final String baseName;

    /**
     * Counter used in naming threads. Starting at 1.
     */
    private AtomicInteger counter;

    /**
     * Initializes the factory with the given base name.
     * Also sets the counter to 1.
     *
     * @param baseName Basename to use for all threads in this pool.
     */
    public NamedThreadFactory(String baseName) {
        this(baseName, DEF_FORMAT);
    }

    public NamedThreadFactory(String baseName, String format) {
        this.baseName = baseName;
        this.format = format;
        counter = new AtomicInteger(1);
    }

    /**
     * Used internally to build the name following the format: basename.NNN or whatever other format is specified.
     * It also increments the internal counter after building the name.
     *
     * @return Basename for new thread in this pool
     */
    final String buildName() {
        return String.format(format, baseName, counter.getAndIncrement());
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = buildName();
        System.out.println("Creating new thread with name " + threadName);
        return new Thread(threadName);
    }
}
