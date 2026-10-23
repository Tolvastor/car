package org.example.car.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Se lee del JSON entrante pero nunca se devuelve en respuestas
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "edad")
    private Integer edad;

    @JsonIgnore // Evita el bucle: Usuario → Viaje → Usuario → ...
    @OneToMany(mappedBy = "usuario")
    private List<Viaje> viajes;

    @JsonIgnore // Evita el bucle: Usuario → Reserva → Usuario → ...
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;
}