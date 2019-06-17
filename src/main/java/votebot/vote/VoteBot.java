package votebot.vote;

import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class VoteBot {
  private static Integer successCount = 0;

  public static void vote(String host, Integer port){
    // Set Proxy
    HttpHost proxy = new HttpHost(host, port);

    // Set no SSL certificate verification and no retry
    CloseableHttpClient httpClient = HttpClients.custom()
        .setSSLHostnameVerifier(new NoopHostnameVerifier()).setProxy(proxy).setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
        .build();

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setReadTimeout(1000*15);
    requestFactory.setConnectionRequestTimeout(1000*15);
    requestFactory.setConnectTimeout(1000*15);
    requestFactory.setHttpClient(httpClient);

    // Create request with headers
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    String url = "https://www.powr.io/app_form_response.json";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("appId", "20062851");
    map.add("data[0][label]", "");
    map.add("data[0][idx]", "zkpat7ns_1559047209255");
    map.add("data[0][type]", "radio");
    map.add("data[0][value]", "Quentin Rozensweig");
    map.add("responseToken", "");
    map.add("isStandalone", "false");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);


    // Post request
    try {
      ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
      if(!response.toString().contains("You've already submitted a response")){
        successCount++;
        System.out.println("Success Count:  " + successCount + " from Thread: " + Thread.currentThread().getName());
      }
      System.out.println("SUCCESS for host: " + host);
    }
    catch(Throwable error){
      System.out.println("Failed for host: " + host + " with ERROR: " + error.getMessage());
    }
  }
}
