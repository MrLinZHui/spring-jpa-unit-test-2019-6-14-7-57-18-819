package com.oocl.web.sampleWebApp.jpaSample.repository;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

public interface SingleEntityRepository extends JpaRepository<SingleEntity,Long> {
    SingleEntity findByName(String name);

    List<SingleEntity> findUsersByName(String name);

    List<SingleEntity> findByOrderByAgeDesc();

    List<SingleEntity> findSimpleEntitiesByNameIgnoreCase(String name);

    void deleteSingleEntityById(Long id);
    @Query(value = "delete from T_SingleEntity where id = :#{#id}",nativeQuery = true)
    @Modifying
    void deleteSingleEntityByIdWithSql(@Param("id") Long id);
}
