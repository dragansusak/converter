package de.zooplus.converter.service.external;

import de.zooplus.converter.service.external.pojo.ExchangeRateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dragan on 20-Nov-16.
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${EXCHANGE_REST_BASE_URL}")
    private String baseUrl;

    @Override
    public Double getExchangeRate(String sourceCurrency, String targetCurrency, Date validOn) {
        final String completeUrl = baseUrl + new SimpleDateFormat("yyyy-MM-dd").format(validOn) + "?base=" + sourceCurrency + "&symbols=" + targetCurrency;

        ExchangeRateResult result = restTemplate.getForObject(completeUrl, ExchangeRateResult.class);

        return result.getRates().get(targetCurrency);
    }
}
