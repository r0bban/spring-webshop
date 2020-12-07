package springWebshop.application.integration;

import java.util.List;

import springWebshop.application.model.dto.SegmentDTO;

public interface SegmentDTORepositoryCustom {
	
	List<SegmentDTO> getAllCategoryDTO();

	List<SegmentDTO> getAllSubCategoryDTO(long categoryId);
	
	List<SegmentDTO> getAllTypeDTO(long subCategoryId);

}
