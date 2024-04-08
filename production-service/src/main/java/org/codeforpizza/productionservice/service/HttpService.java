package org.codeforpizza.productionservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.codeforpizza.productionservice.modell.GetPerformerRequestDTO;
import org.codeforpizza.productionservice.modell.Performer;
import org.codeforpizza.productionservice.modell.PerformerResponsDTO;
import org.codeforpizza.productionservice.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Slf4j
@Service
public class HttpService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    Gson gson = new Gson();

    public PerformerResponsDTO getPerformer(GetPerformerRequestDTO getPerformerRequestDTO) throws IOException, ParseException {
        log.info("sending request to performer service");
        HttpGet request = new HttpGet("http://localhost:8080/toProduction");

        request.setEntity(HttpUtils.createPayload(getPerformerRequestDTO));

        CloseableHttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity);
        log.info("respons :" + responseString);

        log.info(String.valueOf(response.getCode()));

        if (response.getCode() == 200) {
            log.info(response.toString());
            return gson.fromJson(responseString, PerformerResponsDTO.class);
        } else {
            return null;
        }
    }
}
