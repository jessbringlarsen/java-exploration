package dk.bringlarsen.exploration.java.async.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletableFutureTest {

    @Test
    void fireAndForgetTest() {
        CompletableFuture.runAsync(() -> System.out.println("task 1"));
    }

    @Test
    void waitForTasksToCompleteTest() throws Exception {
        var task1 = CompletableFuture.supplyAsync(() -> "task 1");
        var task2 = CompletableFuture.supplyAsync(() -> "task 2");

        CompletableFuture.allOf(task1, task2);

        assertEquals("task 1", task1.get());
        assertEquals("task 2", task2.get());
    }

    @Test
    void combiningOperationsTest() {

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(firstResult -> firstResult + 1)
                .thenAccept(result -> assertEquals(2, result));
    }
}
