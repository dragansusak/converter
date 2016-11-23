package de.zooplus.converter.service.external;

import de.zooplus.converter.service.external.pojo.ExchangeRateResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dragan
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${EXCHANGE_REST_BASE_URL}")
    private URI baseUrl;

    @Value("${EXCHANGE_REST_BASE_URL_LATEST}")
    private URI baseUrlLaterst;

    @Cacheable("myCache")
    @Override
    public Double getExchangeRate(String sourceCurrency, String targetCurrency, Date validOn) {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(baseUrl.resolve(new SimpleDateFormat("yyyy-MM-dd").format(validOn)));
        uriComponentsBuilder.queryParam("base", sourceCurrency);
        uriComponentsBuilder.queryParam("symbols", targetCurrency);

        ExchangeRateResult result = restTemplate.getForObject(uriComponentsBuilder.build().toUri(), ExchangeRateResult.class);

        return result.getRates().get(targetCurrency);
    }

    @Cacheable("myCache")
    @Override
    public ExchangeRateResult getLatest(String sourceCurrency, String... targetCurrencies) {
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(baseUrlLaterst);
        if (StringUtils.isNotEmpty(sourceCurrency)) {
            uriComponentsBuilder.queryParam("base", sourceCurrency);
        }
        if (ArrayUtils.isNotEmpty(targetCurrencies)) {
            uriComponentsBuilder.queryParam("symbols", StringUtils.join(targetCurrencies, ','));
        }

        return restTemplate.getForObject(uriComponentsBuilder.build().toUri(), ExchangeRateResult.class);
    }
}
