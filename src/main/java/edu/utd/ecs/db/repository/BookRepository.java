package edu.utd.ecs.db.repository;

import org.springframework.data.repository.CrudRepository;

import edu.utd.ecs.db.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
