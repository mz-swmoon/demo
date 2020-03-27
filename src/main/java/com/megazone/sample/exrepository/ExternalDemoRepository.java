package com.megazone.sample.exrepository;

import com.megazone.sample.exentity.ExternalDemoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalDemoRepository extends CrudRepository<ExternalDemoEntity, Integer> {
}
