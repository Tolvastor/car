package org.example.car.Repository;

import org.example.car.Model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByReceptorId(Long receptorId);
    List<Mensaje> findByEmisorIdAndReceptorId(Long emisorId, Long receptorId);
    List<Mensaje> findByViajeId(Long viajeId);
}
