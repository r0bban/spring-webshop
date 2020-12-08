package springWebshop.application.integration;

import java.util.List;

import springWebshop.application.model.dto.SegmentDTO;

public interface SegmentationDTORepository {
	public List<SegmentDTO> getAllCategoryDTO();
	public List<SegmentDTO> getAllSubCategoryDTO(long categoryId);
	public List<SegmentDTO> getAllTypeDTO(long subCategoryId);

}
