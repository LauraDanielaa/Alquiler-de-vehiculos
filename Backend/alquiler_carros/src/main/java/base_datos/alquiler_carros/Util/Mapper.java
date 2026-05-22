package base_datos.alquiler_carros.Util;

import base_datos.alquiler_carros.Models.CategoriaVehiculo;
import base_datos.alquiler_carros.Models.Dto.Request.CategoriaVehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.VehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.CategoriaVehiculoResponseDTO;
import base_datos.alquiler_carros.Models.Dto.Response.VehiculoResponseDTO;
import base_datos.alquiler_carros.Models.Sucursal;
import base_datos.alquiler_carros.Models.Vehiculo;

public class Mapper {

    // ─── Categoria ────────────────────────────────────────────

    public static CategoriaVehiculo toEntity(CategoriaVehiculoRequestDTO dto) {
        if (dto == null) return null;
        return CategoriaVehiculo.builder()
                .nombreCategoria(dto.getNombreCategoria())
                .descripcion(dto.getDescripcion())
                .build();
    }

    public static CategoriaVehiculoResponseDTO toDTO(CategoriaVehiculo categoria) {
        if (categoria == null) return null;
        return CategoriaVehiculoResponseDTO.builder()
                .idCategoria(categoria.getId())
                .nombreCategoria(categoria.getNombreCategoria())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    // ─── Vehiculo ─────────────────────────────────────────────

    public static Vehiculo toEntity(VehiculoRequestDTO dto,
                                    CategoriaVehiculo categoria,
                                    Sucursal sucursal) {
        if (dto == null) return null;
        return Vehiculo.builder()
                .placa(dto.getPlaca())
                .marca(dto.getMarca())
                .anio(dto.getAnio())
                .modelo(dto.getModelo())
                .color(dto.getColor())
                .kilometraje(dto.getKilometraje())
                .precio_dia(dto.getPrecioDia())
                .estado(dto.getEstado())
                .categoria(categoria)
                .sucursal(sucursal)
                .build();
    }

    public static VehiculoResponseDTO toDTO(Vehiculo vehiculo) {
        if (vehiculo == null) return null;
        return VehiculoResponseDTO.builder()
                .idVehiculo(vehiculo.getId())
                .placa(vehiculo.getPlaca())
                .marca(vehiculo.getMarca())
                .anio(vehiculo.getAnio())
                .modelo(vehiculo.getModelo())
                .color(vehiculo.getColor())
                .kilometraje(vehiculo.getKilometraje())
                .precioDia(vehiculo.getPrecio_dia())
                .estado(vehiculo.getEstado())
                .categoria(toDTO(vehiculo.getCategoria()))
                .nombreSucursal(vehiculo.getSucursal().getNombre())
                .build();
    }
}