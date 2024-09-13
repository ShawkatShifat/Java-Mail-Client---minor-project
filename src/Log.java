import java.util.logging.Logger;
import java.util.logging.Level;

public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }
}
