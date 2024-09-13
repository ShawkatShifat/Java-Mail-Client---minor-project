import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThread {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        executor.submit(() -> MailClient.sendEmail("user1@gmail.com", "Test", "This is a test."));
        executor.submit(() -> MailClient.sendEmail("user2@gmail.com", "Tesl", "This is a test."));
        executor.submit(() -> MailClient.sendEmail("user3@gmail.com", "Test", "This is a test."));
        executor.shutdown();
    }
}
