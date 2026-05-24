package base_datos.alquiler_carros.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Models.Dto.Request.ReservaRequestDTO;
import base_datos.alquiler_carros.Models.Dto.Response.ReservaResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IReservaService;
import base_datos.alquiler_carros.Util.AuthUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final IReservaService reservaService;
    private final AuthUtils authUtils;

    @PostMapping()
   public ResponseEntity<ReservaResponseDTO> crear(
            @RequestBody @Valid ReservaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.crear(dto));
    }

 @PutMapping("/{idReserva}/confirmar")
    public ResponseEntity<ReservaResponseDTO> confirmar(@PathVariable Long idReserva) {
        return ResponseEntity.ok(reservaService.confirmar(idReserva));
    }

     @PutMapping("/{idReserva}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelar(@PathVariable Long idReserva) {
        return ResponseEntity.ok(reservaService.cancelar(idReserva));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }
    @GetMapping("/mis-reservas")  // ← el cliente ve solo las suyas
    public ResponseEntity<List<ReservaResponseDTO>> misReservas() {
        // AuthUtils lo resuelve internamente
        Cliente cliente = authUtils.getClienteAutenticado();
        return ResponseEntity.ok(reservaService.listarPorCliente(cliente.getId()));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorCliente(
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(reservaService.listarPorCliente(idCliente));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorEstado(
            @PathVariable String estado) {
        return ResponseEntity.ok(reservaService.listarPorEstado(estado));
    }

    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorVehiculo(
            @PathVariable Long idVehiculo) {
        return ResponseEntity.ok(reservaService.listarPorVehiculo(idVehiculo));
    }
}