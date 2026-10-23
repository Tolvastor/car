package org.example.car.Repository;

import org.example.car.Model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, String> {
    List<Notificacion> findByNotificableId(Long notificableId);
}
