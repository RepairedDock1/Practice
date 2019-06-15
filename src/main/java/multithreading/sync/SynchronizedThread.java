package multithreading.sync;

public class SynchronizedThread extends Thread{
  private Counter counter;

  public SynchronizedThread(Counter counter){
    this.counter = counter;
  }

  @Override
  public void run(){
      counter.add(5);
  }

  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();
    SynchronizedThread synchronizedThread = new SynchronizedThread(counter);
    SynchronizedThread synchronizedThread2 = new SynchronizedThread(counter);
    synchronizedThread.start();
    synchronizedThread2.start();
    synchronizedThread.join();
    synchronizedThread2.join();

    System.out.println(counter.getCount());

  }
}
