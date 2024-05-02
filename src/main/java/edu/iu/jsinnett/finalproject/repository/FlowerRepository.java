package edu.iu.jsinnett.finalproject.repository;

import edu.iu.jsinnett.finalproject.model.Flower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends CrudRepository<Flower, Integer> {
}
