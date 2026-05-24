package base_datos.alquiler_carros.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import base_datos.alquiler_carros.Models.*;
import base_datos.alquiler_carros.Models.Dto.Response.*;
import base_datos.alquiler_carros.Models.Dto.Request.CategoriaVehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.MantenimientoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.MultaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.PagoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.ReservaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.SucursalRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Request.VehiculoRequestDTO;

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
    public static Sucursal toEntity(SucursalRequestDTO dto) {
        if (dto == null) return null;
        return Sucursal.builder()
                .nombre(dto.getNombre())
                .ciudad(dto.getCiudad())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .build();
    }

    public static SucursalResponseDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) return null;
        return SucursalResponseDTO.builder()
                .idSucursal(sucursal.getId())
                .nombre(sucursal.getNombre())
                .ciudad(sucursal.getCiudad())
                .telefono(sucursal.getTelefono())
                .direccion(sucursal.getDireccion())
                .build();
    }

// ─── Mantenimiento ────────────────────────────────────────

    public static Mantenimiento toEntity(MantenimientoRequestDTO dto, Vehiculo vehiculo) {
        if (dto == null) return null;
        return Mantenimiento.builder()
                .fecha(dto.getFecha())
                .descripcion(dto.getDescripcion())
                .costo(dto.getCosto())
                .vehiculo(vehiculo)
                .build();
    }

    public static MantenimientoResponseDTO toDTO(Mantenimiento mantenimiento) {
        if (mantenimiento == null) return null;
        return MantenimientoResponseDTO.builder()
                .idMantenimiento(mantenimiento.getId())
                .fecha(mantenimiento.getFecha())
                .descripcion(mantenimiento.getDescripcion())
                .costo(mantenimiento.getCosto())
                .idVehiculo(mantenimiento.getVehiculo().getId())
                .placaVehiculo(mantenimiento.getVehiculo().getPlaca())
                .marcaVehiculo(mantenimiento.getVehiculo().getMarca())
                .build();
    }
    public static Reserva toEntity(ReservaRequestDTO dto, Cliente cliente, Vehiculo vehiculo) {
        if (dto == null) return null;
        return Reserva.builder()
                .fecha_inicio(dto.getFecha_inicio())
                .fecha_fin(dto.getFecha_fin())
                .estado("PENDIENTE")
                .cliente(cliente)
                .vehiculo(vehiculo)
                .build();
    }

    public static ReservaResponseDTO toDTO(Reserva reserva) {
        if (reserva == null) return null;

        long dias = ChronoUnit.DAYS.between(
                reserva.getFecha_inicio(),
                reserva.getFecha_fin()
        );

        BigDecimal totalEstimado = reserva.getVehiculo()
                .getPrecio_dia()
                .multiply(BigDecimal.valueOf(dias));

        return ReservaResponseDTO.builder()
                .idReserva(reserva.getId())
                .fechaInicio(reserva.getFecha_inicio())
                .fechaFin(reserva.getFecha_fin())
                .estado(reserva.getEstado())
                .idVehiculo(reserva.getVehiculo().getId())
                .placaVehiculo(reserva.getVehiculo().getPlaca())
                .marcaVehiculo(reserva.getVehiculo().getMarca())
                .modeloVehiculo(reserva.getVehiculo().getModelo())
                .precioDia(reserva.getVehiculo().getPrecio_dia())
                .totalEstimado(totalEstimado)
                .idCliente(reserva.getCliente().getId())
                .nombreCliente(reserva.getCliente().getNombre())
                .apellidoCliente(reserva.getCliente().getApellido())
                .build();
    }

    public static AlquilerResponseDTO toDTO(Alquiler alquiler) {
        if (alquiler == null) return null;

        long dias = ChronoUnit.DAYS.between(
                alquiler.getFecha_inicio(),
                alquiler.getFecha_fin()
        );

        BigDecimal totalEstimado = alquiler.getVehiculo()
                .getPrecio_dia()
                .multiply(BigDecimal.valueOf(dias));

        return AlquilerResponseDTO.builder()
                .idAlquiler(alquiler.getId())
                .fecha_inicio(alquiler.getFecha_inicio())
                .fecha_fin(alquiler.getFecha_fin())
                .estado(alquiler.getEstado())
                .idVehiculo(alquiler.getVehiculo().getId())
                .placaVehiculo(alquiler.getVehiculo().getPlaca())
                .marcaVehiculo(alquiler.getVehiculo().getMarca())
                .modeloVehiculo(alquiler.getVehiculo().getModelo())
                .precioDia(alquiler.getVehiculo().getPrecio_dia())
                .totalEstimado(totalEstimado)
                .idCliente(alquiler.getCliente().getId())
                .nombreCliente(alquiler.getCliente().getNombre())
                .apellidoCliente(alquiler.getCliente().getApellido())
                .build();
    }
// ─── Pago ─────────────────────────────────────────────────

    public static Pago toEntity(PagoRequestDTO dto, Alquiler alquiler) {
        if (dto == null) return null;
        return Pago.builder()
                .fecha_pago(LocalDate.now())
                .monto(dto.getMonto())
                .metodoPago(dto.getMetodoPago())
                .estado("COMPLETADO")
                .alquiler(alquiler)
                .build();
    }

    public static PagoResponseDTO toDTO(Pago pago) {
        if (pago == null) return null;
        return PagoResponseDTO.builder()
                .idPago(pago.getId())
                .fecha_pago(pago.getFecha_pago())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago())
                .estado(pago.getEstado())
                .idAlquiler(pago.getAlquiler().getId())
                .nombreCliente(pago.getAlquiler().getCliente().getNombre())
                .apellidoCliente(pago.getAlquiler().getCliente().getApellido())
                .build();
    }
// ─── Multa ────────────────────────────────────────────────

    public static Multa toEntity(MultaRequestDTO dto, Alquiler alquiler) {
        if (dto == null) return null;
        return Multa.builder()
                .descripcion(dto.getDescripcion())
                .monto(dto.getMonto())
                .fecha(dto.getFecha())
                .alquiler(alquiler)
                .build();
    }

    public static MultaResponseDTO toDTO(Multa multa) {
        if (multa == null) return null;
        return MultaResponseDTO.builder()
                .idMulta(multa.getId())
                .descripcion(multa.getDescripcion())
                .monto(multa.getMonto())
                .fecha(multa.getFecha())
                .idAlquiler(multa.getAlquiler().getId())
                .nombreCliente(multa.getAlquiler().getCliente().getNombre())
                .apellidoCliente(multa.getAlquiler().getCliente().getApellido())
                .placaVehiculo(multa.getAlquiler().getVehiculo().getPlaca())
                .build();
    }
    // ─── Perfil ───────────────────────────────────────────────

    public static PerfilResponseDTO toPerfilDTO(Usuario usuario) {
        if (usuario == null) return null;

        PerfilResponseDTO.PerfilResponseDTOBuilder builder = PerfilResponseDTO.builder()
                .idUsuario(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .telefono(usuario.getTelefono())
                .estado(usuario.getEstado())
                .fecha_registro(usuario.getFecha_registro());

        // Detecta el rol y agrega campos específicos
        switch (usuario) {
            case Cliente c -> builder
                    .rol("CLIENTE")
                    .documento(c.getDocumento())
                    .direccion(c.getDireccion())
                    .licencia_conduccion(c.getLicencia_conduccion());

            case Empleado e -> builder
                    .rol("EMPLEADO")
                    .documento(e.getDocumento())
                    .cargo(e.getCargo())
                    .salario(e.getSalario())
                    .nombreSucursal(e.getSucursal().getNombre());

            case Administrador a -> builder
                    .rol("ADMIN")
                    .documento(a.getDocumento())
                    .nivel_acceso(a.getNivelAcceso());

            default -> builder.rol("DESCONOCIDO");
        }

        return builder.build();
    }
}