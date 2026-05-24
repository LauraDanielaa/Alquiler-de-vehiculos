package base_datos.alquiler_carros.Service;

import java.time.LocalDate;

import base_datos.alquiler_carros.Service.Interfaces.IAlquilerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Service.Interfaces.IReservaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {

    private final IReservaService  reservaService;
    private final IAlquilerService alquilerService;

    // Corre todos los días a medianoche
    @Scheduled(cron = "0 0 0 * * *")
    public void cancelarVencidos() {
    log.info("Iniciando cancelación automática: {}", LocalDate.now());
    try {
        reservaService.cancelarReservasPendientesVencidas();
        alquilerService.cancelarAlquileresPendientesPagoVencidos();
        log.info("Cancelación automática finalizada con éxito.");
    } catch (Exception e) {
        log.error("Error crítico durante la cancelación automática: ", e);
    }
}

}