package base_datos.alquiler_carros.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Models.Alquiler;
import base_datos.alquiler_carros.Models.Pago;
import base_datos.alquiler_carros.Models.Dto.Request.PagoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.PagoResponseDTO;
import base_datos.alquiler_carros.Repository.IAlquilerRepository;
import base_datos.alquiler_carros.Repository.IPagoRepository;
import base_datos.alquiler_carros.Service.Interfaces.IPagoService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService implements IPagoService {

    private final IPagoRepository    pagoRepository;
    private final IAlquilerRepository alquilerRepository;

    // ─── Pagar → activa el alquiler ───────────────────────────

    @Override
    @Transactional
    public PagoResponseDTO pagar(PagoRequestDTO dto) {

        // 1. Buscar y validar el alquiler
        Alquiler alquiler = alquilerRepository.findById(dto.getIdAlquiler())
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));

        if (!alquiler.getEstado().equals("PENDIENTE_PAGO"))
            throw new RuntimeException("El alquiler no está en estado PENDIENTE_PAGO");

        // 2. Validar monto
        long dias = ChronoUnit.DAYS.between(
                alquiler.getFecha_inicio(),
                alquiler.getFecha_fin()
        );
        BigDecimal totalEsperado = alquiler.getVehiculo()
                .getPrecio_dia()
                .multiply(BigDecimal.valueOf(dias));

        if (dto.getMonto().compareTo(totalEsperado) < 0)
            throw new RuntimeException("El monto ingresado es menor al total del alquiler: "
                    + totalEsperado);

        // 3. Registrar pago
        Pago pago = Mapper.toEntity(dto, alquiler);
        pagoRepository.save(pago);

        // 4. Activar alquiler automáticamente
        alquiler.setEstado("ACTIVO");
        alquilerRepository.save(alquiler);

        return Mapper.toDTO(pago);
    }

    // ─── Consultas ────────────────────────────────────────────

    @Override
    public PagoResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id)));
    }

    @Override
    public List<PagoResponseDTO> listarPorAlquiler(Long idAlquiler) {
        return pagoRepository.findByAlquiler_Id(idAlquiler)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }
}