package de.zooplus.converter.service.external;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dragan on 20-Nov-16.
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Override
    public Double getExchangeRate(String sourceCurrency, String targetCurrency, Date validOn) {
        return 4d;
    }
}
