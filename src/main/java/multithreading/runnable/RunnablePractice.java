package multithreading.runnable;

import com.google.common.collect.Lists;
import java.util.List;

/*
    There are three ways to implement the Runnable interface
      1) Create a Java class that implements the Runnable interface.
      2) Create an anonymous class that implements the Runnable interface.
      3) Create a Java Lambda that implements the Runnable interface.

   ** Note: A common gotcha is to call the run method instead of start() but this would be a mistake.
      The run method would NOT be executed by the new thread you just created. Instead the run()
      method is executed by the thread that created the thread. To avoid this, call using the
      start() method.
*/

public class RunnablePractice implements Runnable{
  // method 1 creation
  public void run() {
    System.out.println("This runnable implements the runnable run method");
  }


  public static void main(String[] args) {
    // method 2 creation
    Runnable runnableAnonymous =
        new Runnable() {
          public void run() {
            System.out.println("This runnable is created via anonymous inner class");
          }
        };

    // method 3 creation
    Runnable runnableLamda =
        () -> System.out.println("This runnable created by a lambda");

    // starting threads
    RunnablePractice runnableImplemented = new RunnablePractice();
    List<Runnable> runnableList = Lists.newArrayList(runnableImplemented, runnableAnonymous, runnableLamda);

    for(Runnable runnable: runnableList){
      Thread thread = new Thread(runnable);
      thread.start();
    }
  }
}


