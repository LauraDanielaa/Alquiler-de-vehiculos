package base_datos.alquiler_carros.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import base_datos.alquiler_carros.Models.Dto.Request.MantenimientoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.MantenimientoResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IMantenimientoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final IMantenimientoService mantenimientoService;

    @PostMapping
    public ResponseEntity<MantenimientoResponseDTO> crear(
            @RequestBody @Valid MantenimientoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mantenimientoService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mantenimientoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(mantenimientoService.listarTodos());
    }

    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<List<MantenimientoResponseDTO>> listarPorVehiculo(
            @PathVariable Long idVehiculo) {
        return ResponseEntity.ok(mantenimientoService.listarPorVehiculo(idVehiculo));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<MantenimientoResponseDTO>> listarPorRangoFechas(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(mantenimientoService.listarPorRangoFechas(fechaInicio, fechaFin));
    }

    @GetMapping("/costo-total/{idVehiculo}")
    public ResponseEntity<BigDecimal> calcularCostoTotal(@PathVariable Long idVehiculo) {
        return ResponseEntity.ok(mantenimientoService.calcularCostoTotalPorVehiculo(idVehiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid MantenimientoRequestDTO dto) {
        return ResponseEntity.ok(mantenimientoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}