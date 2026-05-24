package base_datos.alquiler_carros.Controller;

import java.util.List;

import base_datos.alquiler_carros.Models.Cliente;
import base_datos.alquiler_carros.Util.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base_datos.alquiler_carros.Models.Dto.Response.AlquilerResponseDTO;
import base_datos.alquiler_carros.Service.Interfaces.IAlquilerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class AlquilerController {

    private final IAlquilerService alquilerService;
    private final AuthUtils authUtils;

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alquilerService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlquilerResponseDTO>> listarTodos() {
        return ResponseEntity.ok(alquilerService.listarTodos());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<AlquilerResponseDTO>> listarPorCliente(
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(alquilerService.listarPorCliente(idCliente));
    }

    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<List<AlquilerResponseDTO>> listarPorVehiculo(
            @PathVariable Long idVehiculo) {
        return ResponseEntity.ok(alquilerService.listarPorVehiculo(idVehiculo));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<AlquilerResponseDTO>> listarPorEstado(
            @PathVariable String estado) {
        return ResponseEntity.ok(alquilerService.listarPorEstado(estado));
    }


    @PutMapping("/{idAlquiler}/finalizar")
    public ResponseEntity<AlquilerResponseDTO> finalizar(@PathVariable Long idAlquiler) {
        return ResponseEntity.ok(alquilerService.finalizar(idAlquiler));
    }
    @GetMapping("/mis-alquileres")  // ← el cliente ve solo los suyos
    public ResponseEntity<List<AlquilerResponseDTO>> misAlquileres() {
        Cliente cliente = authUtils.getClienteAutenticado();
        return ResponseEntity.ok(alquilerService.listarPorCliente(cliente.getId()));
    }

}