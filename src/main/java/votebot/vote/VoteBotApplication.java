package votebot.vote;

import static com.google.common.collect.Lists.partition;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.util.List;
import votebot.proxies.ProxyAddress;
import votebot.proxies.ProxyGetter;

public class VoteBotApplication {
  public static void main(String[] args) throws FileNotFoundException {
    int threadNameCounter = 1;
    ProxyGetter proxyGetter = new ProxyGetter();
    List<ProxyAddress> masterProxyList = Lists.newArrayList();
    masterProxyList.addAll(proxyGetter.getProxyListFromFile("proxy.txt"));
    masterProxyList.addAll(proxyGetter.getListFromWebsite());

    List<List<ProxyAddress>> partitionedList = partition(masterProxyList, 20);

    for(List<ProxyAddress> subListFromFile: partitionedList){
      Thread fileThread = new Thread(new VoteRunnable(subListFromFile), "thread" + threadNameCounter++);

      fileThread.start();
    }

  }
}
