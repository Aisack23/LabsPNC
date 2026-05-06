package clases.pnc.lab02pnc.repository;

import clases.pnc.lab02pnc.domain.entity.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WizardRepository extends JpaRepository<Wizard, UUID> {
}