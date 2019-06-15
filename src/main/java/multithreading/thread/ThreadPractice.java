package multithreading.thread;

//Start five threads by overriding the run method of Thread class

public class ThreadPractice extends Thread{
  @Override
  public void run(){
    System.out.println(this.getId());
  }

  public static void main(String[] args) throws InterruptedException {
    for(int i = 0; i<5; i++) {
      ThreadPractice threadPractice = new ThreadPractice();
      threadPractice.start();
    }

    // Extra notes on threads

    //Create thread with name
    Thread namedThread = new Thread("hello");
    System.out.println(namedThread.getName());

    // Get reference to current thread that is running
    Thread currentThread = Thread.currentThread();

    // Pause a thread with Thread.sleep
    Thread.sleep(3000);
  }
}
