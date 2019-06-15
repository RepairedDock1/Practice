package votebot.proxies;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import votebot.vote.ProxyAddress;

public class ProxyGetter {
  public List<ProxyAddress> getNewProxyAddressesFromWebsite(){
    List<ProxyAddress> proxyAddresses = Lists.newArrayList();
    // Setup Headers with agent
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    // Get Webpage
    String proxyPayload = restTemplate.exchange("https://free-proxy-list.net/", HttpMethod.GET,entity, String.class).toString();
    Document proxyPageHtml = Jsoup.parse(proxyPayload);

    Elements proxyRows = proxyPageHtml.getElementById("proxylisttable").getElementsByTag("tr");
    for(Element proxyRow: proxyRows){
      Elements columns = proxyRow.getElementsByTag("td");
      if(!columns.isEmpty()){
        String host = columns.get(0).html();
        Integer port = Integer.valueOf(columns.get(1).html());

        proxyAddresses.add(new ProxyAddress(host, port));
      }
    }

    return proxyAddresses;
  }

  public List<ProxyAddress> getProxyListFromFile() throws FileNotFoundException {
    List<ProxyAddress> proxyAddresses = Lists.newArrayList();

    Scanner scanner = new Scanner(new File("/Users/jxb5862/Documents/git/Practice/src/main/java/votebot/proxy.txt"));
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      proxyAddresses.add(new ProxyAddress(line.split(":")[0], Integer.valueOf(line.split(":")[1])));
    }

    return proxyAddresses;
  }
}
