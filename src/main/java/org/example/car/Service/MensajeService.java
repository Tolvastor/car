package org.example.car.Service;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Mensaje;
import org.example.car.Repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> findById(Long id) {
        return mensajeRepository.findById(id);
    }

    public List<Mensaje> findByReceptorId(Long receptorId) {
        return mensajeRepository.findByReceptorId(receptorId);
    }

    public List<Mensaje> findByEmisorIdAndReceptorId(Long emisorId, Long receptorId) {
        return mensajeRepository.findByEmisorIdAndReceptorId(emisorId, receptorId);
    }

    public List<Mensaje> findByViajeId(Long viajeId) {
        return mensajeRepository.findByViajeId(viajeId);
    }

    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public void deleteById(Long id) {
        mensajeRepository.deleteById(id);
    }
}
