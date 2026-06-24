package clases.pnc.lab02pnc.service.impl;

import clases.pnc.lab02pnc.domain.entity.Wizard;
import clases.pnc.lab02pnc.repository.WizardRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WizardServiceImplTest {

    @Mock
    private WizardRepository wizardRepository;

    @InjectMocks
    private WizardServiceImpl wizardService;

    private Wizard wizard;
    private UUID wizardId;

    @BeforeEach
    void setup() {

        wizardId = UUID.randomUUID();

        wizard = new Wizard();

        wizard.setId(wizardId);
        wizard.setName("Harry Potter");
        wizard.setHouse("Gryffindor");
        wizard.setPatronus("Stag");
        wizard.setIsDeatheater(false);
    }

    @Test
    void createWizardShouldSaveWizard() {

        when(wizardRepository.save(wizard))
                .thenReturn(wizard);

        Wizard result = wizardService.createWizard(wizard);

        assertEquals("Harry Potter", result.getName());

        verify(wizardRepository).save(wizard);
    }

    @Test
    void getAllShouldReturnList() {

        when(wizardRepository.findAll())
                .thenReturn(List.of(wizard));

        List<Wizard> result = wizardService.getAll();

        assertEquals(1, result.size());

        verify(wizardRepository).findAll();
    }

    @Test
    void updateWizardShouldUpdateData() {

        Wizard updated = new Wizard();

        updated.setName("Severus Snape");
        updated.setHouse("Slytherin");
        updated.setPatronus("Doe");
        updated.setIsDeatheater(false);

        when(wizardRepository.findById(wizardId))
                .thenReturn(Optional.of(wizard));

        when(wizardRepository.save(any()))
                .thenReturn(updated);

        Wizard result =
                wizardService.updateWizard(wizardId, updated);

        assertEquals("Severus Snape",
                result.getName());

        verify(wizardRepository).findById(wizardId);

        verify(wizardRepository).save(any());
    }

    @Test
    void deleteWizardShouldCallRepository() {

        wizardService.deleteWizard(wizardId);

        verify(wizardRepository)
                .deleteById(wizardId);
    }
}