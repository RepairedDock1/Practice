package multithreading.sync;

public class Counter {
  Integer count = 0;

  /* synchronized keyword means that only one thread at a time will enter this method for a given
  instance of this object
  If the program is run without synchronize then the numbers will be inconsistent
  */
  public synchronized void add(Integer number){
    for(int i=1; i<number; i++){
      fakeProcessing();
      count++;
    }
  }

  public void fakeProcessing(){
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Integer getCount() {
    return count;
  }
}
