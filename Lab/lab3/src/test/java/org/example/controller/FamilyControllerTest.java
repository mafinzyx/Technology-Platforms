package org.example.controller;

import org.example.dto.FamilyDTO;
import org.example.entity.Family;
import org.example.repository.FamilyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for FamilyController
 * Uses Mockito for repository mocking
 */
@ExtendWith(MockitoExtension.class)
class FamilyControllerTest {
    @Mock
    private FamilyRepository repository;

    private FamilyController controller;
    private FamilyDTO testFamilyDTO;
    private Family testFamily;

    @BeforeEach
    void setUp() {
        System.out.println("\n=== Setting up test environment ===");
        controller = new FamilyController(repository);

        testFamilyDTO = new FamilyDTO(1L, "Test", "Test Address");
        testFamily = new Family();
        testFamily.setId(1L);
        testFamily.setSurname("Test");
        testFamily.setAddress("Test Address");
        System.out.println("Test environment is ready");
    }

    @Test
    void save_ShouldReturnDone_WhenSaveSuccessful() {
        System.out.println("\n=== Test: save_ShouldReturnDone_WhenSaveSuccessful ===");
        System.out.println("Attempting to save family through controller...");

        // When
        String result = controller.save(testFamilyDTO);
        System.out.println("Save result received: " + result);

        // Then
        System.out.println("Verifying result...");
        assertThat(result).isEqualTo("done");
        verify(repository).save(any(Family.class));
        System.out.println("Test completed successfully: save operation performed correctly");
    }

    @Test
    void save_ShouldReturnBadRequest_WhenFamilyExists() {
        System.out.println("\n=== Test: save_ShouldReturnBadRequest_WhenFamilyExists ===");
        System.out.println("Setting up mock for existing family simulation...");

        // Given
        doThrow(new IllegalArgumentException()).when(repository).save(any(Family.class));
        System.out.println("Mock configured");

        // When
        System.out.println("Attempting to save existing family...");
        String result = controller.save(testFamilyDTO);
        System.out.println("Save result received: " + result);

        // Then
        System.out.println("Verifying result...");
        assertThat(result).isEqualTo("bad request");
        System.out.println("Test completed successfully: expected bad request result received");
    }

    @Test
    void findById_ShouldReturnFamilyString_WhenFamilyExists() {
        System.out.println("\n=== Test: findById_ShouldReturnFamilyString_WhenFamilyExists ===");
        System.out.println("Setting up mock to return test family...");

        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(testFamily));
        System.out.println("Mock configured");

        // When
        System.out.println("Searching for family through controller...");
        String result = controller.findById(1L);
        System.out.println("Search result received: " + result);

        // Then
        System.out.println("Verifying result...");
        assertThat(result).contains("Test").contains("Test Address");
        System.out.println("Test completed successfully: correct family information found");
    }

    @Test
    void findById_ShouldReturnNotFound_WhenFamilyDoesNotExist() {
        System.out.println("\n=== Test: findById_ShouldReturnNotFound_WhenFamilyDoesNotExist ===");
        System.out.println("Setting up mock to return empty result...");

        // Given
        when(repository.findById(999L)).thenReturn(Optional.empty());
        System.out.println("Mock configured");

        // When
        System.out.println("Searching for non-existent family...");
        String result = controller.findById(999L);
        System.out.println("Search result received: " + result);

        // Then
        System.out.println("Verifying result...");
        assertThat(result).isEqualTo("not found");
        System.out.println("Test completed successfully: expected not found result received");
    }

    @Test
    void deleteById_ShouldReturnDone_WhenDeleteSuccessful() {
        System.out.println("\n=== Test: deleteById_ShouldReturnDone_WhenDeleteSuccessful ===");
        System.out.println("Attempting to delete family through controller...");

        // When
        String result = controller.deleteById(1L);
        System.out.println("Delete result received: " + result);

        // Then
        System.out.println("Verifying result...");
        assertThat(result).isEqualTo("done");
        verify(repository).deleteById(1L);
        System.out.println("Test completed successfully: delete operation performed correctly");
    }

    @Test
    void deleteById_ShouldReturnNotFound_WhenFamilyDoesNotExist() {
        System.out.println("\n=== Test: deleteById_ShouldReturnNotFound_WhenFamilyDoesNotExist ===");
        System.out.println("Setting up mock for non-existent family simulation...");

        // Given
        doThrow(new IllegalArgumentException()).when(repository).deleteById(999L);
        System.out.println("Mock configured");

        // When
        System.out.println("Attempting to delete non-existent family...");
        String result = controller.deleteById(999L);
        System.out.println("Delete result received: " + result);

        // Then
        System.out.println("Verifying result...");
        assertThat(result).isEqualTo("not found");
        System.out.println("Test completed successfully: expected not found result received");
    }
}
