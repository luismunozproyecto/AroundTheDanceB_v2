package Modelos;

/**
 * Created by Paxi on 24/04/2018.
 */

public class Evento {

    String ciudad;
    String creadoPor;
    String descripcion;
    String fechaFin;
    String fechaIncio;
    String horaInicio;
    String id;
    String imagen;
    double lat;
    double lon;
    String nombre;
    String pais;

    public Evento() {
    }

    public Evento(String ciudad, String creadoPor, String descripcion, String fechaFin, String fechaIncio, String horaInicio, String id, String imagen, double lat, double lon, String nombre, String pais) {
        this.ciudad = ciudad;
        this.creadoPor = creadoPor;
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaIncio = fechaIncio;
        this.horaInicio = horaInicio;
        this.id = id;
        this.imagen = imagen;
        this.lat = lat;
        this.lon = lon;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public String getFechaIncio() {
        return fechaIncio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }
}
