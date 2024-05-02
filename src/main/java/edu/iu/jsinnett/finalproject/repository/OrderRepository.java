package edu.iu.jsinnett.finalproject.repository;

import edu.iu.jsinnett.finalproject.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByCustomerId(int customerId);
}
