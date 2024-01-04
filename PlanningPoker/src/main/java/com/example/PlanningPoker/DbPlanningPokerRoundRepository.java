package com.example.PlanningPoker;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DbPlanningPokerRoundRepository implements PlanningPokerRoundRepository {
	
	private final JdbcPlanningPokerRoundEntityRepository jdbcPlanningPokerRoundEntityRepository;
	
	@Autowired
	public DbPlanningPokerRoundRepository(JdbcPlanningPokerRoundEntityRepository jdbcPlanningPokerRoundEntityRepository) {
		this.jdbcPlanningPokerRoundEntityRepository = jdbcPlanningPokerRoundEntityRepository;
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
	public PlanningPokerRound findById(PlanningPokerRoundId planningPokerRoundId) {
		  Optional<PlanningPokerRoundEntity> planningPokerRoundEntity = jdbcPlanningPokerRoundEntityRepository.findById(planningPokerRoundId.getPlanningPokerRoundId());
	        if (planningPokerRoundEntity.isPresent()) {
	            return planningPokerRoundEntity.get().toDomain();
	        } else {
	            return null;
	        }
	}


	@Override
	public void save(PlanningPokerRound planningPokerRound) {
		jdbcPlanningPokerRoundEntityRepository.save(new PlanningPokerRoundEntity(planningPokerRound));

	}

	

}