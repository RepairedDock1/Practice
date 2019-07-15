package multithreading.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
The Runnable/Callable interfaces are designed to represent a task that can be executed by
multiple threads. Runnable tasks can be run using the Thread class or ExecutorService whereas
Callables can be run only using the latter. The runnable call() method does not return a value
whereas the callables run value returns a generic value V. The result of call() method is returned
within a Future object.
 */
public class CallablePractice implements Callable<Integer> {
  private Integer number;

  public CallablePractice(Integer number) {
    this.number = number;
  }

  @Override
  public Integer call(){
    Integer total = 0;

    for(int count = 0; count <= number; count++){
      total += count;
    }

    return total;
  }

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, TimeoutException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    CallablePractice callableTask = new CallablePractice(12);

    Future<Integer> future = executorService.submit(callableTask);

    System.out.println(future.get());
    System.out.println("Get Future with timeout: " + future.get(200, TimeUnit.MILLISECONDS));
  }
}
