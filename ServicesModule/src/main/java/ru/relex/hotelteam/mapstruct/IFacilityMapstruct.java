package ru.relex.hotelteam.mapstruct;


import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import ru.relex.hotelteam.db.domain.Facility;
import ru.relex.hotelteam.dto.facility.FacilityDtoWithNoPrice;

@Mapper(componentModel = "spring")
public interface IFacilityMapstruct {

  @Mapping(target = "price", ignore = true)
  Facility fromDto(FacilityDtoWithNoPrice dto);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<Facility> fromDto(List<FacilityDtoWithNoPrice> facilityList);

  FacilityDtoWithNoPrice toDto(Facility facility);

  @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<FacilityDtoWithNoPrice> toDto(List<Facility> facilityDTOList);
}
