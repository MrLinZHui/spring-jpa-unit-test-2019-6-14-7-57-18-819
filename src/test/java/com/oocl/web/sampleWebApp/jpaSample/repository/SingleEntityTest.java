package com.oocl.web.sampleWebApp.jpaSample.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SingleEntityTest {
    @Autowired
    private SingleEntityRepository singleEntityRepository;
    @Test
    public void test_should_return_singleEntity_when_the_singleEntity_exist(){
        //given
        SingleEntity singleEntity = new SingleEntity("Tomi", 20);
        singleEntityRepository.save(singleEntity);
        //when
        SingleEntity singleEntity1 = singleEntityRepository.findAll().get(0);
        //then
        Assertions.assertEquals("Tomi",singleEntity1.getName());
    }
    @Test
    public void test_should_return_Exception_when_the_singleEntity_name_over_64(){
        //given
        SingleEntity singleEntity = new SingleEntity("TomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomiTomi", 20);
        singleEntityRepository.save(singleEntity);
        //when
        //SingleEntity singleEntity1 = singleEntityRepository.findAll().get(0);
        //then
        Assertions.assertThrows(Exception.class,()->singleEntityRepository.findAll());
    }
    @Test
    public void test_should_return_singleEntity_when_give_a_sanme(){
        //given
        SingleEntity singleEntity = new SingleEntity("Tomi", 20);
        SingleEntity singleEntity1 = new SingleEntity("Tomil", 20);
        singleEntityRepository.saveAndFlush(singleEntity);
        singleEntityRepository.saveAndFlush(singleEntity1);
        //when
        SingleEntity singleEntity2 = singleEntityRepository.findByName("Tomi");
        //then
        Assertions.assertEquals("Tomi",singleEntity2.getName());
    }
    @Test
    public void test_should_return_singleEntitys_when_give_a_name(){
        //given
        SingleEntity singleEntity = new SingleEntity("Tomi", 20);
        SingleEntity singleEntity2 = new SingleEntity("Tomi", 20);
        SingleEntity singleEntity1 = new SingleEntity("Tomil", 20);
        singleEntityRepository.saveAndFlush(singleEntity);
        singleEntityRepository.saveAndFlush(singleEntity1);
        singleEntityRepository.saveAndFlush(singleEntity2);
        //when
        List<SingleEntity> singleEntityList = singleEntityRepository.findUsersByName("Tomi");
        //then
        Assertions.assertEquals(2,singleEntityList.size());
    }
    @Test
    public void test_should_return_singleEntitys_order_by_age(){
        //given
        SingleEntity singleEntity = new SingleEntity("Tomi",20);
        SingleEntity singleEntity2 = new SingleEntity("Tomi",16);
        SingleEntity singleEntity1 = new SingleEntity("Tomil",21);
        singleEntityRepository.saveAndFlush(singleEntity);
        singleEntityRepository.saveAndFlush(singleEntity1);
        singleEntityRepository.saveAndFlush(singleEntity2);
        //when
        List<SingleEntity> singleEntityList = singleEntityRepository.findByOrderByAgeDesc();
        //then
        Assertions.assertEquals(21,singleEntityList.get(0).getAge());
        Assertions.assertEquals(20,singleEntityList.get(1).getAge());
        Assertions.assertEquals(16,singleEntityList.get(2).getAge());
    }
//    @Test
//    public void test_should_return_singleEntitys_when_distinct_by_name(){
//        //given
//        SingleEntity singleEntity = new SingleEntity("Tomi",20);
//        SingleEntity singleEntity2 = new SingleEntity("Tomi",16);
//        SingleEntity singleEntity1 = new SingleEntity("Tomil",21);
//        singleEntityRepository.saveAndFlush(singleEntity);
//        singleEntityRepository.saveAndFlush(singleEntity1);
//        singleEntityRepository.saveAndFlush(singleEntity2);
//        //when
//        List<String> singleEntityList = singleEntityRepository.findAllDistinctName();
//        //then
//        Assertions.assertEquals(2,singleEntityList.size());
//    }
@Test
public void test_should_return_singleEntitys_by_name_ignore_name(){
    //given
    SingleEntity singleEntity = new SingleEntity("Tomi",20);
    SingleEntity singleEntity2 = new SingleEntity("tomi",16);
    SingleEntity singleEntity1 = new SingleEntity("Tomil",21);
    singleEntityRepository.saveAndFlush(singleEntity);
    singleEntityRepository.saveAndFlush(singleEntity1);
    singleEntityRepository.saveAndFlush(singleEntity2);
    //when
    List<SingleEntity> singleEntityList = singleEntityRepository.findSimpleEntitiesByNameIgnoreCase("tomi");
    //then
    Assertions.assertEquals(2,singleEntityList.size());
}
@Test
public void test_should_delete_singleEntitys_by_Id(){
    //given
    SingleEntity singleEntity = new SingleEntity("Tomi",20);
    SingleEntity singleEntity2 = new SingleEntity("tomi",16);
    SingleEntity singleEntity1 = new SingleEntity("Tomil",21);
    singleEntityRepository.saveAndFlush(singleEntity);
    singleEntityRepository.saveAndFlush(singleEntity1);
    singleEntityRepository.saveAndFlush(singleEntity2);
    //when
    singleEntityRepository.deleteSingleEntityById((long) 1);
    List<SingleEntity> singleEntities = singleEntityRepository.findAll();
    //then
    Assertions.assertEquals(2,singleEntities.size());
}@Test
public void test_should_delete_singleEntitys_by_Id_with_sql(){
    //given
    SingleEntity singleEntity = new SingleEntity("Tomi",20);
    SingleEntity singleEntity2 = new SingleEntity("tomi",16);
    SingleEntity singleEntity1 = new SingleEntity("Tomil",21);
    singleEntityRepository.saveAndFlush(singleEntity);
    singleEntityRepository.saveAndFlush(singleEntity1);
    singleEntityRepository.saveAndFlush(singleEntity2);
    //when
    singleEntityRepository.deleteSingleEntityByIdWithSql((long) 1);
    List<SingleEntity> singleEntities = singleEntityRepository.findAll();
    //then
    Assertions.assertEquals(2,singleEntities.size());
}
}
