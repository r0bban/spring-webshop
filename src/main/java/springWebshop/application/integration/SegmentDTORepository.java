package springWebshop.application.integration;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import springWebshop.application.model.dto.SegmentDTO;

public abstract class SegmentDTORepository implements SegmentDTORepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
}
