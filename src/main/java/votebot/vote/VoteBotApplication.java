package votebot.vote;

import java.io.FileNotFoundException;
import votebot.proxies.ProxyGetter;

public class VoteBotApplication {
  public static void main(String[] args) throws FileNotFoundException {
    ProxyGetter proxyGetter = new ProxyGetter();

    Thread voteThread = new Thread(new VoteRunnable(proxyGetter.getProxyListFromFile()), "FileThread");
    Thread voteThread1 = new Thread(new VoteRunnable(proxyGetter.getNewProxyAddressesFromWebsite()), "WebThread");

    voteThread.start();
    voteThread1.start();
  }
}
