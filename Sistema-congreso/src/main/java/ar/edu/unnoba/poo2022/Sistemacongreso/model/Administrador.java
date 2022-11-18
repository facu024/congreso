package ar.edu.unnoba.poo2022.Sistemacongreso.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private String apellido;
}
