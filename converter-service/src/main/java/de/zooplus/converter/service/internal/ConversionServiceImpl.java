package de.zooplus.converter.service.internal;

import de.zooplus.converter.dao.repository.ConversionRepository;
import de.zooplus.converter.model.entity.Conversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dragan on 20-Nov-16.
 */
@Service
public class ConversionServiceImpl implements ConversionService{

    @Autowired
    private ConversionRepository conversionRepository;

    @Override
    public List<Conversion> getAllForUser(Integer userId) {
        return conversionRepository.findTop10ByUserIdOrderByIdDesc(userId);
    }

    @Override
    public Conversion getById(Integer conversionId) {
        return conversionRepository.findOne(conversionId);
    }

    @Transactional
    @Override
    public void saveConversion(Conversion conversion) {
         conversionRepository.save(conversion);
    }
}