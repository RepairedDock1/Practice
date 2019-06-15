package votebot.vote;

public class ProxyAddress {
  private String host;
  private Integer port;

  public ProxyAddress(String host, Integer port){
    this.host = host.trim();
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public Integer getPort() {
    return port;
  }
}
