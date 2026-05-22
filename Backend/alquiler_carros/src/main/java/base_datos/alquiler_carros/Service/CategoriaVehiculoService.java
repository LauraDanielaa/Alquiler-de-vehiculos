package base_datos.alquiler_carros.Service;

import base_datos.alquiler_carros.Models.CategoriaVehiculo;
import base_datos.alquiler_carros.Models.Dto.Request.CategoriaVehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.CategoriaVehiculoResponseDTO;
import base_datos.alquiler_carros.Repository.ICategoriaVehiculoRepository;
import base_datos.alquiler_carros.Service.Interfaces.ICategoriaVehiculoService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaVehiculoService implements ICategoriaVehiculoService {

    private final ICategoriaVehiculoRepository categoriaRepository;

    @Override
    @Transactional
    public CategoriaVehiculoResponseDTO crear(CategoriaVehiculoRequestDTO dto) {
        if (categoriaRepository.existsByNombreCategoria(dto.getNombreCategoria()))
            throw new RuntimeException("Ya existe una categoría con ese nombre");

        CategoriaVehiculo categoria = Mapper.toEntity(dto);
        return Mapper.toDTO(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaVehiculoResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(findOrThrow(id));
    }

    @Override
    public List<CategoriaVehiculoResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public CategoriaVehiculoResponseDTO actualizar(Long id, CategoriaVehiculoRequestDTO dto) {
        CategoriaVehiculo categoria = findOrThrow(id);
        categoria.setNombreCategoria(dto.getNombreCategoria());
        categoria.setDescripcion(dto.getDescripcion());
        return Mapper.toDTO(categoriaRepository.save(categoria));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        categoriaRepository.delete(findOrThrow(id));
    }

    private CategoriaVehiculo findOrThrow(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }
}