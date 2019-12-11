package net.banck.serviceb;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TreeRepository extends PagingAndSortingRepository<Tree, Long> {
    List<Tree> findByName(@Param("name") String name);
}
