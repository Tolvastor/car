package org.example.car.Controller;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Reserva;
import org.example.car.Service.ReservaService;
import org.example.car.Service.ViajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final ViajeService viajeService;

    @GetMapping
    public List<Reserva> getAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getById(@PathVariable Long id) {
        return reservaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> getByUsuario(@PathVariable Long usuarioId) {
        return reservaService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/viaje/{viajeId}")
    public List<Reserva> getByViaje(@PathVariable Long viajeId) {
        return reservaService.findByViajeId(viajeId);
    }

    @PostMapping
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
        Long usuarioId = reserva.getUsuario().getId();
        Long viajeId   = reserva.getViaje().getId();

        // Bloquear reserva duplicada del mismo usuario en el mismo viaje
        if (reservaService.existsByUsuarioIdAndViajeId(usuarioId, viajeId)) {
            return ResponseEntity.status(409).build();
        }

        // Comprobar que quedan plazas
        var viajeOpt = viajeService.findById(viajeId);
        if (viajeOpt.isEmpty() || viajeOpt.get().getPlazasDisponibles() <= 0) {
            return ResponseEntity.status(409).build();
        }

        // Descontar plaza
        var viaje = viajeOpt.get();
        viaje.setPlazasDisponibles(viaje.getPlazasDisponibles() - 1);
        viajeService.save(viaje);

        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        return reservaService.findById(id).map(existing -> {
            // Si el conductor cancela/rechaza una reserva que no estaba cancelada, devolver la plaza
            boolean seEstaCancelando = "cancelada".equals(reserva.getEstado())
                    && !"cancelada".equals(existing.getEstado());
            if (seEstaCancelando) {
                viajeService.findById(existing.getViaje().getId()).ifPresent(viaje -> {
                    viaje.setPlazasDisponibles(viaje.getPlazasDisponibles() + 1);
                    viajeService.save(viaje);
                });
            }
            reserva.setId(id);
            return ResponseEntity.ok(reservaService.save(reserva));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var reservaOpt = reservaService.findById(id);
        if (reservaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reserva = reservaOpt.get();
        // Devolver la plaza si la reserva no estaba ya cancelada
        if (!"cancelada".equals(reserva.getEstado())) {
            viajeService.findById(reserva.getViaje().getId()).ifPresent(viaje -> {
                viaje.setPlazasDisponibles(viaje.getPlazasDisponibles() + 1);
                viajeService.save(viaje);
            });
        }
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
