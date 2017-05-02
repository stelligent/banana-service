package com.stelligent.domain;

import com.stelligent.BananaApplication;
import com.stelligent.controller.BananaController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by casey.lee on 9/12/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BananaRepositoryTest {
  @Autowired
  private BananaRepository repository;

  private static final int INITIAL_SIZE = 1;

  private Banana newBanana(boolean peeled, LocalDateTime pickedAt) {
    Banana b = new Banana();
    b.setPeeled(peeled);
    b.setPickedAt(pickedAt);
    return b;
  }

  @Test
  public void testSaveNew() {
    List<Banana> results= new ArrayList<>();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE, results.size());

    Banana b = repository.save(newBanana(false, LocalDateTime.now()));
    Assert.assertNotNull("id", b.getId());

    results.clear();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE + 1, results.size());
    Assert.assertEquals("id", b.getId(), results.get(1).getId());
  }

  @Test
  public void testSaveExisting() {
    Banana b = repository.save(newBanana(false, LocalDateTime.now()));
    List<Banana> results= new ArrayList<>();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE + 1, results.size());

    b.setPeeled(true);
    repository.save(b);

    results.clear();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE + 1, results.size());
    Assert.assertTrue("peeled", results.get(1).getPeeled());
  }

  @Test
  public void testFindOneEmpty() {
    Banana b = repository.findOne(100L);
    Assert.assertNull("result is null", b);
  }

  @Test(expected = InvalidDataAccessApiUsageException.class)
  public void testFindOneNullId() {
    Banana b = repository.findOne(null);
  }

  @Test
  public void testFindOneWithResults() {
    repository.save(newBanana(false, LocalDateTime.now()));
    Banana expectedBanana = repository.save(newBanana(true, LocalDateTime.now()));
    Banana actualBanana = repository.findOne(expectedBanana.getId());

    Assert.assertEquals("id", expectedBanana.getId(), actualBanana.getId());
    Assert.assertEquals("peeled", expectedBanana.getPeeled(), actualBanana.getPeeled());
    Assert.assertEquals("pickedAt", expectedBanana.getPickedAt(), actualBanana.getPickedAt());

  }

  @Test
  public void testFindAllEmpty() {
    List<Banana> results= new ArrayList<>();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", 1, results.size());
  }

  @Test
  public void testFindAllWithResults() {
    repository.save(newBanana(false, LocalDateTime.now()));
    repository.save(newBanana(true, LocalDateTime.now()));
    List<Banana> results= new ArrayList<>();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE + 2, results.size());
  }

  @Test
  public void testDeleteNotFound() {
    repository.save(newBanana(false, LocalDateTime.now()));
    repository.save(newBanana(true, LocalDateTime.now()));
    List<Banana> results= new ArrayList<>();
    repository.findAll().forEach(results::add);
    Banana toDelete = new Banana();
    toDelete.setId(10000L);
    repository.delete(toDelete);
    Banana b = new Banana();
    b.setId(100L);
    repository.delete(b);
    Assert.assertEquals("results size", INITIAL_SIZE + 2, results.size());
  }

  @Test
  public void testDeleteFound() {
    repository.save(newBanana(true, LocalDateTime.now()));
    Banana b = repository.save(newBanana(true, LocalDateTime.now()));
    List<Banana> results= new ArrayList<>();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE + 2, results.size());

    repository.delete(b);
    results.clear();
    repository.findAll().forEach(results::add);
    Assert.assertEquals("results size", INITIAL_SIZE + 1, results.size());
    Assert.assertNotEquals("results size", b.getId(), results.get(0).getId());
  }

}
