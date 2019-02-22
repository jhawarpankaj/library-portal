package edu.utd.ecs.db.repository;

import org.springframework.data.repository.CrudRepository;

import edu.utd.ecs.db.model.Authors;

public interface AuthorsRepository extends CrudRepository<Authors, Integer> {
}
