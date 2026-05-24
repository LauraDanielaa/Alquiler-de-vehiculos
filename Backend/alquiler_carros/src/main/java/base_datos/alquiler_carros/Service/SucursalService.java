package base_datos.alquiler_carros.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import base_datos.alquiler_carros.Models.Sucursal;
import base_datos.alquiler_carros.Models.Dto.Request.SucursalRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.SucursalResponseDTO;
import base_datos.alquiler_carros.Repository.ISucursalRepository;
import base_datos.alquiler_carros.Service.Interfaces.ISucursalService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SucursalService implements ISucursalService {

    private final ISucursalRepository sucursalRepository;

    @Override
    @Transactional
    public SucursalResponseDTO crear(SucursalRequestDTO dto) {
        if (sucursalRepository.existsByNombre(dto.getNombre()))
            throw new RuntimeException("Ya existe una sucursal con ese nombre");

        return Mapper.toDTO(sucursalRepository.save(Mapper.toEntity(dto)));
    }

    @Override
    public SucursalResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(findOrThrow(id));
    }

    @Override
    public List<SucursalResponseDTO> listarTodas() {
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::toDTO).toList();
    }

    @Override
    public List<SucursalResponseDTO> listarPorCiudad(String ciudad) {
        return sucursalRepository.findByCiudad(ciudad)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public SucursalResponseDTO actualizar(Long id, SucursalRequestDTO dto) {
        Sucursal sucursal = findOrThrow(id);
        sucursal.setNombre(dto.getNombre());
        sucursal.setCiudad(dto.getCiudad());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setDireccion(dto.getDireccion());
        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        sucursalRepository.delete(findOrThrow(id));
    }

    private Sucursal findOrThrow(Long id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + id));
    }
}