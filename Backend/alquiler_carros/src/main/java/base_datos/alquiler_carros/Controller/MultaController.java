package base_datos.alquiler_carros.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import base_datos.alquiler_carros.Models.Dto.Request.MultaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.MultaResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IMultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/multas")
@RequiredArgsConstructor
public class MultaController {

    private final IMultaService multaService;

    @PostMapping
    public ResponseEntity<MultaResponseDTO> crear(
            @RequestBody @Valid MultaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(multaService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.buscarPorId(id));
    }

    @GetMapping("/alquiler/{idAlquiler}")
    public ResponseEntity<List<MultaResponseDTO>> listarPorAlquiler(
            @PathVariable Long idAlquiler) {
        return ResponseEntity.ok(multaService.listarPorAlquiler(idAlquiler));
    }
}