package com.youcode.hunters_league.web.vm.mapper;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.web.vm.species.SpeciesEditVM;
import com.youcode.hunters_league.web.vm.species.SpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpeciesVmMapper {
    SpeciesVM toSpeciesVM(Species species);
    Species toSpecies(SpeciesVM speciesVM);

    SpeciesEditVM toSpeciesEditVM(Species species);
}
