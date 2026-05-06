package labos.pnc.lab01;

import labos.pnc.lab01.service.BowService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import labos.pnc.lab01.domain.model.BOW;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Lab01Application implements CommandLineRunner {

    private final BowService bowService;

    public static void main(String[] args) {
        SpringApplication.run(Lab01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== PROTOCOLO DE CONTENCIÓN UMBRELLA: INICIANDO REPORTE ===");

        // Filtrado por Virus T
        System.out.println("\n--- Filtrado por Virus: T-Virus ---");
        printReport(bowService.findByVirus("T-Virus"));

        // Filtrado por Estado
        System.out.println("\n--- Filtrado por Estado: En libertad ---");
        printReport(bowService.findByStatus("Free"));

        // Virus sin repetición
        System.out.println("\n--- Virus con especímenes en libertad ---");
        List<String> activeViruses = bowService.getUniqueActiveViruses();
        activeViruses.forEach(v -> System.out.println("[S.T.A.R.S-REPORT] Virus Activo: " + v));
    }

    private void printReport(List<BOW> bows) {
        bows.forEach(bow -> {
            System.out.printf("[S.T.A.R.S-REPORT] Nombre: %s | Nivel de Peligro: %d | Punto Débil: %s%n",
                    bow.getName(), bow.getDanger(), bow.getWeakness());
        });
    }
}