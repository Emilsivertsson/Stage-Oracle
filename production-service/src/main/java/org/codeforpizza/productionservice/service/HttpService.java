package org.codeforpizza.productionservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.codeforpizza.productionservice.modell.Performer;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final ObjectMapper mapper = new ObjectMapper();

    public Performer getPerformer(String Username, Long performerId) {
        HttpGet request = new HttpGet("http://localhost:8081/performers/" + performerId );
    }
}
