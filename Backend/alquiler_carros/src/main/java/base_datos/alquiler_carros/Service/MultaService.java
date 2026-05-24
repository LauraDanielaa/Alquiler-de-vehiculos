package base_datos.alquiler_carros.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Models.Alquiler;
import base_datos.alquiler_carros.Models.Multa;
import base_datos.alquiler_carros.Models.Dto.Request.MultaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.MultaResponseDTO;
import base_datos.alquiler_carros.Repository.IAlquilerRepository;
import base_datos.alquiler_carros.Repository.IMultaRepository;
import base_datos.alquiler_carros.Service.Interfaces.IMultaService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MultaService implements IMultaService {

    private final IMultaRepository    multaRepository;
    private final IAlquilerRepository alquilerRepository;

    // ─── Crear multa ──────────────────────────────────────────

    @Override
    @Transactional
    public MultaResponseDTO crear(MultaRequestDTO dto) {

        // 1. Buscar y validar el alquiler
        Alquiler alquiler = alquilerRepository.findById(dto.getIdAlquiler())
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));

        // 2. Solo se pueden multar alquileres FINALIZADOS o ACTIVOS
        if (!alquiler.getEstado().equals("FINALIZADO")
                && !alquiler.getEstado().equals("ACTIVO"))
            throw new RuntimeException(
                    "Solo se pueden registrar multas en alquileres ACTIVOS o FINALIZADOS");

        // 3. Registrar multa
        Multa multa = Mapper.toEntity(dto, alquiler);
        return Mapper.toDTO(multaRepository.save(multa));
    }

    // ─── Consultas ────────────────────────────────────────────

    @Override
    public MultaResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada con id: " + id)));
    }

    @Override
    public List<MultaResponseDTO> listarPorAlquiler(Long idAlquiler) {
        return multaRepository.findByAlquiler_id(idAlquiler)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }
}