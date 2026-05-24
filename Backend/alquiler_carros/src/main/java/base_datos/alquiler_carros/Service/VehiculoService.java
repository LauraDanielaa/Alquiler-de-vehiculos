package base_datos.alquiler_carros.Service;

import base_datos.alquiler_carros.Models.CategoriaVehiculo;
import base_datos.alquiler_carros.Models.Dto.Request.VehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.VehiculoResponseDTO;
import base_datos.alquiler_carros.Models.Sucursal;
import base_datos.alquiler_carros.Models.Vehiculo;
import base_datos.alquiler_carros.Repository.*;
import base_datos.alquiler_carros.Service.Interfaces.IVehiculoService;
import base_datos.alquiler_carros.Util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService implements IVehiculoService {

    private final IVehiculoRepository vehiculoRepository;
    private final ICategoriaVehiculoRepository categoriaRepository;
    private final ISucursalRepository sucursalRepository;
    private final IReservaRepository reservaRepository;
    private final IAlquilerRepository alquilerRepository;

    @Override
    @Transactional
    public VehiculoResponseDTO crear(VehiculoRequestDTO dto) {
        if (vehiculoRepository.existsByPlaca(dto.getPlaca()))
            throw new RuntimeException("Ya existe un vehículo con la placa: " + dto.getPlaca());

        CategoriaVehiculo categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Sucursal sucursal = sucursalRepository.findById(dto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Vehiculo vehiculo = Mapper.toEntity(dto, categoria, sucursal);
        return Mapper.toDTO(vehiculoRepository.save(vehiculo));
    }

    @Override
    public VehiculoResponseDTO buscarPorId(Long id) {
        return Mapper.toDTO(findOrThrow(id));
    }

    @Override
    public VehiculoResponseDTO buscarPorPlaca(String placa) {
        return Mapper.toDTO(vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con placa: " + placa)));
    }

    @Override
    public List<VehiculoResponseDTO> listarTodos() {
        return vehiculoRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<VehiculoResponseDTO> listarPorEstado(String estado) {
        return vehiculoRepository.findByEstado(estado)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public List<VehiculoResponseDTO> listarPorCategoria(Long idCategoria) {
        return vehiculoRepository.findByCategoria_Id(idCategoria)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public VehiculoResponseDTO actualizar(Long id, VehiculoRequestDTO dto) {
        Vehiculo vehiculo = findOrThrow(id);

        CategoriaVehiculo categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Sucursal sucursal = sucursalRepository.findById(dto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setColor(dto.getColor());
        vehiculo.setKilometraje(dto.getKilometraje());
        vehiculo.setPrecio_dia(dto.getPrecioDia());
        vehiculo.setEstado(dto.getEstado());
        vehiculo.setCategoria(categoria);
        vehiculo.setSucursal(sucursal);

        return Mapper.toDTO(vehiculoRepository.save(vehiculo));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        vehiculoRepository.delete(findOrThrow(id));
    }

    private Vehiculo findOrThrow(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + id));
    }
    @Override
    public List<VehiculoResponseDTO> buscarDisponibles(LocalDate fechaInicio, LocalDate fechaFin) {
        // Trae todos los vehículos disponibles
        List<Vehiculo> disponibles = vehiculoRepository.findByEstado("DISPONIBLE");

        // Filtra los que no tienen reservas ni alquileres en ese rango
        return disponibles.stream()
                .filter(v ->
                        reservaRepository
                        .findReservasActivasEnRango(v.getId(), fechaInicio, fechaFin)
                        .isEmpty())
                .filter(v -> alquilerRepository
                        .findAlquileresActivosEnRango(v.getId(), fechaInicio, fechaFin)
                        .isEmpty())
                .map(Mapper::toDTO)
                .toList();
    }
}