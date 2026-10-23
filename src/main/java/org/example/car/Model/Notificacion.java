package org.example.car.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "notificable_id")
    private Long notificableId;

    @Column(name = "notificable_tipo")
    private String notificableTipo;

    @Column(name = "datos", columnDefinition = "TEXT")
    private String datos;
}