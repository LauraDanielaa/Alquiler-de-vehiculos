package base_datos.alquiler_carros.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import base_datos.alquiler_carros.Models.Dto.Request.SucursalRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.SucursalResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.ISucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final ISucursalService sucursalService;

    @PostMapping
    public ResponseEntity<SucursalResponseDTO> crear(
            @RequestBody @Valid SucursalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sucursalService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<SucursalResponseDTO>> listarTodas() {
        return ResponseEntity.ok(sucursalService.listarTodas());
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<SucursalResponseDTO>> listarPorCiudad(
            @PathVariable String ciudad) {
        return ResponseEntity.ok(sucursalService.listarPorCiudad(ciudad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid SucursalRequestDTO dto) {
        return ResponseEntity.ok(sucursalService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}