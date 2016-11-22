package de.zooplus.converter.service.internal;

import de.zooplus.converter.model.entity.Conversion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by dragan on 20-Nov-16.
 */
public interface ConversionService {

    List<Conversion> getAllForUser(String userEmail);

    Optional<Conversion> findByDateAndCurrency(Date date, String sourceCurrency, String targetCurrency);

    void saveConversion(Conversion conversion, String userEmail);
}
