package com.megazone.demo.exrepository;

import com.megazone.demo.exentity.ExternalDemoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalDemoRepository extends CrudRepository<ExternalDemoEntity, Integer> {
}
