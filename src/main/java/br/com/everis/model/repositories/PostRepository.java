package br.com.everis.model.repositories;

import br.com.everis.model.domain.Posts;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PostRepository extends CrudRepository<Posts, Long> {
}
