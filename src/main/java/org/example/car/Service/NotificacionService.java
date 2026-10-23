package org.example.car.Service;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Notificacion;
import org.example.car.Repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> findById(String id) {
        return notificacionRepository.findById(id);
    }

    public List<Notificacion> findByNotificableId(Long notificableId) {
        return notificacionRepository.findByNotificableId(notificableId);
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void deleteById(String id) {
        notificacionRepository.deleteById(id);
    }
}
