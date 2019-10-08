package com.exclusivelounge.rental.model.repositories;

import com.exclusivelounge.rental.model.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}
