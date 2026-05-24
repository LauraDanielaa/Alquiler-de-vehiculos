package base_datos.alquiler_carros.Controller;

import base_datos.alquiler_carros.Models.Dto.Request.VehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.VehiculoResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IVehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final IVehiculoService vehiculoService;

    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> crear(
            @RequestBody @Valid VehiculoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.buscarPorId(id));
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoResponseDTO>> buscarDisponibles(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(vehiculoService.buscarDisponibles(fechaInicio, fechaFin));
    }
    @GetMapping("/placa/{placa}")
    public ResponseEntity<VehiculoResponseDTO> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(vehiculoService.buscarPorPlaca(placa));
    }

    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(vehiculoService.listarTodos());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(vehiculoService.listarPorEstado(estado));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarPorCategoria(
            @PathVariable Long idCategoria) {
        return ResponseEntity.ok(vehiculoService.listarPorCategoria(idCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid VehiculoRequestDTO dto) {
        return ResponseEntity.ok(vehiculoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}