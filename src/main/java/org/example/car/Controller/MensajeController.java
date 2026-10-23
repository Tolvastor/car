package org.example.car.Controller;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Mensaje;
import org.example.car.Service.MensajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping
    public List<Mensaje> getAll() {
        return mensajeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getById(@PathVariable Long id) {
        return mensajeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/receptor/{receptorId}")
    public List<Mensaje> getByReceptor(@PathVariable Long receptorId) {
        return mensajeService.findByReceptorId(receptorId);
    }

    @GetMapping("/conversacion")
    public List<Mensaje> getConversacion(@RequestParam Long emisorId, @RequestParam Long receptorId) {
        return mensajeService.findByEmisorIdAndReceptorId(emisorId, receptorId);
    }

    @GetMapping("/viaje/{viajeId}")
    public List<Mensaje> getByViaje(@PathVariable Long viajeId) {
        return mensajeService.findByViajeId(viajeId);
    }

    @PostMapping
    public ResponseEntity<Mensaje> create(@RequestBody Mensaje mensaje) {
        return ResponseEntity.ok(mensajeService.save(mensaje));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (mensajeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        mensajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
