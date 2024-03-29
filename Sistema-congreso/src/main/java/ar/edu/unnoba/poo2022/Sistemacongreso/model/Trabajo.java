package ar.edu.unnoba.poo2022.Sistemacongreso.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Table(name="Trabajos")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaHora;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Evento evento;
    
    @Column(nullable = false)
    private String descripcion;

    @Column(nullable=false)
    private String archivo;

  

    public Trabajo(Long id, LocalDateTime fechaHora, Usuario usuario, Evento evento, String descripcion,
            String archivo) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
        this.evento = evento;
        this.descripcion = descripcion;
        this.archivo = archivo;
    }

    public Trabajo(){

    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {return fechaHora;}

    public void setFechaHora(LocalDateTime fechaHora) {this.fechaHora = fechaHora;}

    public Evento getEvento() {return evento;}

    public void setEvento(Evento evento) {this.evento = evento;}
}
