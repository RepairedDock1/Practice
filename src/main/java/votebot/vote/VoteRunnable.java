package votebot.vote;

import java.util.List;
import votebot.proxies.ProxyAddress;

public class VoteRunnable implements Runnable {
  private List<ProxyAddress> proxyAddresses;

  public VoteRunnable(List<ProxyAddress> proxyAddresses){
    this.proxyAddresses = proxyAddresses;
  }

  @Override
  public void run() {
      for (ProxyAddress proxyAddress : proxyAddresses) {
        VoteBot.vote(proxyAddress.getHost(), proxyAddress.getPort());
      }
    }
}
