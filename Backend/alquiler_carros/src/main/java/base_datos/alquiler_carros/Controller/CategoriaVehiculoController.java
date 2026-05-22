package base_datos.alquiler_carros.Controller;

import base_datos.alquiler_carros.Models.Dto.Request.CategoriaVehiculoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.CategoriaVehiculoResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.ICategoriaVehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaVehiculoController {

    private final ICategoriaVehiculoService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaVehiculoResponseDTO> crear(
            @RequestBody @Valid CategoriaVehiculoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaVehiculoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaVehiculoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaVehiculoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaVehiculoRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}