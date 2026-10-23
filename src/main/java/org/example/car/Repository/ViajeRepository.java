package org.example.car.Repository;

import org.example.car.Model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    List<Viaje> findByUsuarioId(Long usuarioId);
    List<Viaje> findByOrigenAndDestino(String origen, String destino);
}
