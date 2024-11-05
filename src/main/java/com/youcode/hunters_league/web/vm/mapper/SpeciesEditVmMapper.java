package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.web.vm.species.SpeciesEditVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpeciesEditVmMapper {
    SpeciesEditVM toSpeciesEditVM(Species species);
    Species toSpecies(SpeciesEditVM speciesEditVM);
}
