package votebot.vote;

import static com.google.common.collect.Lists.partition;

import java.io.FileNotFoundException;
import java.util.List;
import votebot.proxies.ProxyAddress;
import votebot.proxies.ProxyGetter;

public class VoteBotApplication {
  public static void main(String[] args) throws FileNotFoundException {
    int threadNameCounter = 1;
    ProxyGetter proxyGetter = new ProxyGetter();

    Thread webThread = new Thread(new VoteRunnable(proxyGetter.getNewProxyAddressesFromWebsite()), "WebThread");
    webThread.start();

    List<List<ProxyAddress>> partitionedList = partition(proxyGetter.getProxyListFromFile("proxy.txt"), 20);

    for(List<ProxyAddress> subListFromFile: partitionedList){
      Thread fileThread = new Thread(new VoteRunnable(subListFromFile), "fileThread" + threadNameCounter++);

      fileThread.start();
    }

  }
}
