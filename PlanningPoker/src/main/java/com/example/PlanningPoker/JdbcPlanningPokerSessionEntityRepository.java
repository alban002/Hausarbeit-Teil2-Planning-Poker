package com.example.PlanningPoker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcPlanningPokerSessionEntityRepository 
	extends CrudRepository<PlanningPokerSessionEntity, Integer> {
}