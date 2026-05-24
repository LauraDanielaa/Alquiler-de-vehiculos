package base_datos.alquiler_carros.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import base_datos.alquiler_carros.Models.Dto.Request.PagoRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.PagoResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IPagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final IPagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> pagar(
            @RequestBody @Valid PagoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.pagar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping("/alquiler/{idAlquiler}")
    public ResponseEntity<List<PagoResponseDTO>> listarPorAlquiler(
            @PathVariable Long idAlquiler) {
        return ResponseEntity.ok(pagoService.listarPorAlquiler(idAlquiler));
    }
}