package org.example.car.Service;

import lombok.RequiredArgsConstructor;
import org.example.car.Model.Reserva;
import org.example.car.Repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> findByUsuarioId(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> findByViajeId(Long viajeId) {
        return reservaRepository.findByViajeId(viajeId);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public boolean existsByUsuarioIdAndViajeId(Long usuarioId, Long viajeId) {
        return reservaRepository.existsByUsuarioIdAndViajeId(usuarioId, viajeId);
    }
}
