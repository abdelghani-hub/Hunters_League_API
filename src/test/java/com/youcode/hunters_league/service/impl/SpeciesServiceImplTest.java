package com.youcode.hunters_league.service.impl;

import com.youcode.hunters_league.domain.Species;
import com.youcode.hunters_league.exception.AlreadyExistException;
import com.youcode.hunters_league.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpeciesServiceImplTest {

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SpeciesServiceImpl speciesService;

    private Species species;
    private UUID speciesId;

    @BeforeEach
    void setUp() {
        speciesId = UUID.randomUUID();
        species = new Species();
        species.setId(speciesId);
        species.setName("Lion");
    }

    @Test
    void create_WithNewSpecies_ShouldSaveAndReturnSpecies() {
        // Arrange
        when(speciesRepository.existsByName(anyString())).thenReturn(false);
        when(speciesRepository.save(any(Species.class))).thenReturn(species);

        // Act
        Species result = speciesService.create(species);

        // Assert
        assertNotNull(result);
        assertEquals(species.getName(), result.getName());
        verify(speciesRepository).existsByName(species.getName());
        verify(speciesRepository).save(species);
    }

    @Test
    void create_WithExistingSpeciesName_ShouldThrowAlreadyExistException() {
        // Arrange
        when(speciesRepository.existsByName(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(AlreadyExistException.class, () -> speciesService.create(species));
        verify(speciesRepository).existsByName(species.getName());
        verify(speciesRepository, never()).save(any(Species.class));
    }

    @Test
    void delete_WithExistingSpecies_ShouldReturnTrue() {
        // Arrange
        when(speciesRepository.existsById(any(UUID.class))).thenReturn(true);
        when(jdbcTemplate.update(anyString(), any(UUID.class))).thenReturn(1);

        // Act
        boolean result = speciesService.delete(speciesId);

        // Assert
        assertTrue(result);
        verify(jdbcTemplate).update(anyString(), eq(speciesId));
        verify(speciesRepository).deleteById(speciesId);
    }

    @Test
    void delete_WithNonExistingSpecies_ShouldReturnFalse() {
        // Arrange
        when(speciesRepository.existsById(any(UUID.class))).thenReturn(false);

        // Act
        boolean result = speciesService.delete(speciesId);

        // Assert
        assertFalse(result);
        verify(jdbcTemplate, never()).update(anyString(), any(UUID.class));
        verify(speciesRepository, never()).deleteById(any(UUID.class));
    }

    @Test
    void update_WithExistingSpecies_ShouldUpdateAndReturnSpecies() {
        // Arrange
        when(speciesRepository.existsById(any(UUID.class))).thenReturn(true);
        when(speciesRepository.existsByNameAndIdNot(anyString(), any(UUID.class))).thenReturn(false);
        when(speciesRepository.save(any(Species.class))).thenReturn(species);

        // Act
        Species result = speciesService.update(species);

        // Assert
        assertNotNull(result);
        assertEquals(species.getName(), result.getName());
        verify(speciesRepository).existsById(species.getId());
        verify(speciesRepository).existsByNameAndIdNot(species.getName(), species.getId());
        verify(speciesRepository).save(species);
    }

    @Test
    void update_WithNonExistingSpecies_ShouldReturnNull() {
        // Arrange
        when(speciesRepository.existsById(any(UUID.class))).thenReturn(false);

        // Act
        Species result = speciesService.update(species);

        // Assert
        assertNull(result);
        verify(speciesRepository).existsById(species.getId());
        verify(speciesRepository, never()).existsByNameAndIdNot(anyString(), any(UUID.class));
        verify(speciesRepository, never()).save(any(Species.class));
    }

    @Test
    void update_WithDuplicateSpeciesName_ShouldThrowAlreadyExistException() {
        // Arrange
        when(speciesRepository.existsById(any(UUID.class))).thenReturn(true);
        when(speciesRepository.existsByNameAndIdNot(anyString(), any(UUID.class))).thenReturn(true);

        // Act & Assert
        assertThrows(AlreadyExistException.class, () -> speciesService.update(species));
        verify(speciesRepository).existsById(species.getId());
        verify(speciesRepository).existsByNameAndIdNot(species.getName(), species.getId());
        verify(speciesRepository, never()).save(any(Species.class));
    }
}