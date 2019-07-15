package multithreading.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
ExecutorService is a framework provided by the JDK which simplifies the execution of tasks in
asynchronous mode. Generally speaking, ExecutorService automatically provides a pool of threads
and API for assigning tasks to it.
 */
public class ExecutorServicePractice {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    Callable<Integer> callableTask = ()->{
      Thread.sleep(3000);
      return 10;
    };

    List<Callable<Integer>> callables = new ArrayList<>();
    callables.add(callableTask);
    callables.add(callableTask);
    callables.add(callableTask);

    List<Future<Integer>> futures = executorService.invokeAll(callables);
    Integer count = 0;
    for(Future<Integer> future: futures){
      count+=future.get();
    }

    System.out.println(count);
  }
}
