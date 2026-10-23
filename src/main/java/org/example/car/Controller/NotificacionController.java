package org.example.car.Controller;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Notificacion;
import org.example.car.Service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> getAll() {
        return notificacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getById(@PathVariable String id) {
        return notificacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{notificableId}")
    public List<Notificacion> getByUsuario(@PathVariable Long notificableId) {
        return notificacionService.findByNotificableId(notificableId);
    }

    @PostMapping
    public ResponseEntity<Notificacion> create(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(notificacionService.save(notificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (notificacionService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        notificacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
