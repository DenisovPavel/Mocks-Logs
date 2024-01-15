package org.max.home.accu;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetWeather5DaysTest extends AbstractTest {
    private static final Logger logger
            = LoggerFactory.getLogger(GetWeatherOneDayTest.class);

    @Test
    @DisplayName("Test with Different checks ")
    void get_DifferentChecks() throws IOException {
        logger.info("Тест код ответ 200 запущен");
        //given
        logger.debug("Формирование мока для GET /forecasts/v1/daily/5day/55");
        stubFor(get(urlPathEqualTo("/forecasts/v1/daily/5day/55"))
                .willReturn(aResponse()
                        .withStatus(200).withBody("Success")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(getBaseUrl() + "/forecasts/v1/daily/5day/55");
        logger.debug("http клиент создан");
        //when
        HttpResponse response = httpClient.execute(request);
        //then
        verify(getRequestedFor(urlPathEqualTo("/forecasts/v1/daily/5day/55")));

        assertEquals(200, response.getStatusLine().getStatusCode());
        logger.debug("200 status check ok!");
        assertNotEquals("Not Success", convertResponseToString(response));
        logger.debug("Not equals check - ok!");
        assertNotNull(response);
        logger.debug("Body not empty check - ok!");
    }
}


