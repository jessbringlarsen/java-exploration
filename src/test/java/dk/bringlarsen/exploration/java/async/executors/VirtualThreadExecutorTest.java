package dk.bringlarsen.exploration.java.async.executors;

import dk.bringlarsen.exploration.java.JDK;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

@JDK(version = 19)
class VirtualThreadExecutorTest {

    @Test
    void startVirualThreadTest() {
        Thread.startVirtualThread(() -> System.out.println("task"));
    }

    @Test
    void usingThreadBuilderTest() {
        var thread = Thread.ofVirtual().name("Test thread").unstarted(() -> System.out.println("task"));
        thread.start();
    }

    @Test
    void usingVirutualThreadPoolTest() {
        // Using try with resources ensures that all threads are done before continuting.
        try (var threadPool = Executors.newVirtualThreadPerTaskExecutor()) {
            threadPool.submit(() -> System.out.println("task 1"));
            threadPool.submit(() -> System.out.println("task 2"));
        }
    }
}