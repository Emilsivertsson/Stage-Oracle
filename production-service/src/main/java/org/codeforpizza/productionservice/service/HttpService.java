package org.codeforpizza.productionservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Service
public class HttpService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final ObjectMapper mapper = new ObjectMapper();

    public PerformerResponsDTO getPerformer(GetPerformerRequestDTO getPerformerRequestDTO) throws IOException, ParseException {
        HttpGet request = new HttpGet("http://localhost:8081/performers/" + getPerformerRequestDTO.getPerformerId());

        request.setEntity(HttpUtils.createPayload(getPerformerRequestDTO));

        CloseableHttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();

        return mapper.readValue(EntityUtils.toString(entity), new TypeReference<PerformerResponsDTO>() {});
    }
}
