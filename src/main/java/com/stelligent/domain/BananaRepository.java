package com.stelligent.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository to manage our Banana objects
 */
@Repository
public interface BananaRepository extends CrudRepository<Banana, Long> {
}