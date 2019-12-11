package net.banck.serviceb;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    List<City> findByName(@Param("name") String name);
}
