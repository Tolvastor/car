package org.example.car.Repository;

import org.example.car.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long usuarioId);
    List<Reserva> findByViajeId(Long viajeId);
    boolean existsByUsuarioIdAndViajeId(Long usuarioId, Long viajeId);
}
