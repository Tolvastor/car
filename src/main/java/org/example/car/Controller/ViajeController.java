package org.example.car.Controller;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Viaje;
import org.example.car.Service.ViajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/viajes")
@RequiredArgsConstructor
public class ViajeController {

    private final ViajeService viajeService;

    @GetMapping
    public List<Viaje> getAll() {
        return viajeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getById(@PathVariable Long id) {
        return viajeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Viaje> buscar(@RequestParam String origen, @RequestParam String destino) {
        return viajeService.findByOrigenAndDestino(origen, destino);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Viaje> getByUsuario(@PathVariable Long usuarioId) {
        return viajeService.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Viaje> create(@RequestBody Viaje viaje) {
        if (viaje.getFechaSalida() == null || viaje.getFechaSalida().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(viajeService.save(viaje));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> update(@PathVariable Long id, @RequestBody Viaje viaje) {
        return viajeService.findById(id).map(existing -> {
            viaje.setId(id);
            return ResponseEntity.ok(viajeService.save(viaje));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (viajeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        viajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
