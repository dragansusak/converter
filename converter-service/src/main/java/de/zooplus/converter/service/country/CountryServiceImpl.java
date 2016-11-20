package de.zooplus.converter.service.country;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dragan on 20-Nov-16.
 */
@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public List<Locale> getAllCountries() {
        return Stream.of(Locale.getISOCountries()).map(c -> new Locale("", c)).collect(Collectors.toList());
    }
}
