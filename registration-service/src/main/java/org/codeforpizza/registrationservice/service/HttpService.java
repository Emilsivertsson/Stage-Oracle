package org.codeforpizza.registrationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.codeforpizza.registrationservice.models.IsAuthenticatedDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final ObjectMapper mapper = new ObjectMapper();

    public Boolean isAuthenticated(String username) throws IOException {
        HttpGet request = new HttpGet("http://localhost:8081/auth/isAuthenticated");
        IsAuthenticatedDTO isAuthenticatedDTO = new IsAuthenticatedDTO(username);
        request.setEntity(new StringEntity(mapper.writeValueAsString(isAuthenticatedDTO), ContentType.APPLICATION_JSON));
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getCode() == 200) {
            return true;
        } else {
            return false;
        }

    }
}
