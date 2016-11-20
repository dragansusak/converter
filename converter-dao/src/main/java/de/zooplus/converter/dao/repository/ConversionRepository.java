package de.zooplus.converter.dao.repository;

import de.zooplus.converter.model.entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dragan on 20-Nov-16.
 */
@Repository
public interface ConversionRepository extends JpaRepository<Conversion,Integer> {

    List<Conversion> findTop10ByUserIdOrderByIdDesc(Integer userId);
}
