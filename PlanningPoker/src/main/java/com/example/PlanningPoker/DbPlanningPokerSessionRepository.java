package com.example.PlanningPoker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DbPlanningPokerSessionRepository implements PlanningPokerSessionRepository {
	
	private final JdbcPlanningPokerSessionEntityRepository jdbcPlanningPokerSessionEntityRepository;
	
	@Autowired
	public DbPlanningPokerSessionRepository(JdbcPlanningPokerSessionEntityRepository jdbcPlanningPokerSessionEntityRepository) {
		this.jdbcPlanningPokerSessionEntityRepository = jdbcPlanningPokerSessionEntityRepository;
}
//	@Autowired
//	public DbArtikelRepository() {
//	}
	
	
//	public Artikel findById(ArtikelId artikelId) {
//		if (artikelId.getId() == 1) {
//			ArtikelEntity artikelEntity = new ArtikelEntity();
//			artikelEntity.setId(1);
//			artikelEntity.setBestand(4713);
//			return artikelEntity.toDomain();
//		}
//		else return null;
//		
//	}

	@Override
	public PlanningPokerSession findById(PlanningPokerSessionId planningPokerSessionId) {
		  Optional<PlanningPokerSessionEntity> planningPokerSessionEntity = jdbcPlanningPokerSessionEntityRepository.findById(planningPokerSessionId.getPlanningPokerSessionId());
	        if (planningPokerSessionEntity.isPresent()) {
	            return planningPokerSessionEntity.get().toDomain();
	        } else {
	            return null;
	        }
	}


	@Override
	public void save(PlanningPokerSession planningPokerSession) {
		jdbcPlanningPokerSessionEntityRepository.save(new PlanningPokerSessionEntity(planningPokerSession));

	}

	

}