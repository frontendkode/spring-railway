package com.KissTech.crm.apiService;

import com.KissTech.crm.DTO.DropDownDTO;
import com.KissTech.crm.createDTO.CreateDropDownDTO;
import com.KissTech.crm.model.DropDown;
import com.KissTech.crm.service.DropDownService;
import com.KissTech.crm.updateDTO.UpdateDropDownDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DropDownApiService {

    private final DropDownService dropDownService;

    public DropDownApiService(DropDownService dropDownService) {
        this.dropDownService = dropDownService;
    }

    public DropDownDTO create(CreateDropDownDTO createDropDownDTO) {
        // Convert Create DTO → Entity
        DropDown dropDown = mapToEntity(createDropDownDTO);

        // Save Entity
        DropDown savedDropDown = dropDownService.save(dropDown);

        // Convert Entity → DTO
        return mapToDTO(savedDropDown);
    }

    // ----------------- MAPPING METHODS -----------------

    private DropDown mapToEntity(CreateDropDownDTO dto) {
        DropDown dropDown = new DropDown();
        dropDown.setDropdownType(dto.getDropdownType());
        dropDown.setValues(dto.getValue());
        return dropDown;
    }

    private DropDownDTO mapToDTO(DropDown entity) {
        DropDownDTO dto = new DropDownDTO();
        dto.setId(entity.getId());
        dto.setDropdownType(entity.getDropdownType());
        dto.setValues(entity.getValues());
        return dto;
    }

    public DropDownDTO getById(UUID id) {
        DropDown dropDown= dropDownService.getById(id);
      return   mapToDTO(dropDown);
    }

    public List<DropDownDTO> getAll() {

        List<DropDown> dropDown= dropDownService.getAll();
return  dropDown.stream().map(this::mapToDTO).toList();
    }

    public DropDownDTO update(UpdateDropDownDTO updateDropDownDTO) {
        // Convert Create DTO → Entity
        DropDown dropDown = UpdatemapToEntity(updateDropDownDTO);

        // Save Entity
        DropDown savedDropDown = dropDownService.save(dropDown);

        // Convert Entity → DTO
        return mapToDTO(savedDropDown);
    }

    private DropDown UpdatemapToEntity(UpdateDropDownDTO dto) {
        DropDown dropDown = new DropDown();
        dropDown.setDropdownType(dto.getDropdownType());
        dropDown.setValues(dto.getValues());
        dropDown.setId(dto.getId());
        return dropDown;
    }
}
