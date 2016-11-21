package de.zooplus.converter.service.internal;

import de.zooplus.converter.model.entity.Conversion;

import java.util.List;

/**
 * Created by dragan on 20-Nov-16.
 */
public interface ConversionService {

    List<Conversion> getAllForUser(String userEmail);

    Conversion getById(Integer conversionId);

    void saveConversion(Conversion conversion, String userEmail);
}
