package org.example.car.Service;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Viaje;
import org.example.car.Repository.ViajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViajeService {

    private final ViajeRepository viajeRepository;

    public List<Viaje> findAll() {
        return viajeRepository.findAll();
    }

    public Optional<Viaje> findById(Long id) {
        return viajeRepository.findById(id);
    }

    public List<Viaje> findByUsuarioId(Long usuarioId) {
        return viajeRepository.findByUsuarioId(usuarioId);
    }

    public List<Viaje> findByOrigenAndDestino(String origen, String destino) {
        return viajeRepository.findByOrigenAndDestino(origen, destino);
    }

    public Viaje save(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    public void deleteById(Long id) {
        viajeRepository.deleteById(id);
    }
}
