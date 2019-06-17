package votebot.vote;

import java.util.List;
import votebot.proxies.ProxyAddress;

public class VoteRunnable implements Runnable {
  List<ProxyAddress> proxyAddresses;

  public VoteRunnable(List<ProxyAddress> proxyAddresses){
    this.proxyAddresses = proxyAddresses;
  }

  @Override
  public void run() {
    VoteBot voteBot = new VoteBot();

    for(ProxyAddress proxyAddress: proxyAddresses){
        voteBot.vote(proxyAddress.getHost(), proxyAddress.getPort());
    }
  }
}
