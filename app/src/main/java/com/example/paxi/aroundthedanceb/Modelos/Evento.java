package com.example.paxi.aroundthedanceb.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Evento implements Parcelable, Serializable
{

    String id;
    String nombre;
    String descripcion;
    String fechaInicio;
    String horaInicio;
    String fechaFin;
    String ciudad;
    String pais;
    Double lat;
    Double lon;
    List<Tipo> tipos;
    String imagen;
    String creadoPor;

    public Evento(String id, String nombre,
                  String descripcion,
                  String fechaInicio, String horaInicio, String fechaFin,
                  String ciudad, String pais, Double lat, Double lon,
                  List<Tipo> tipos,
                  String imagen,
                  String creadoPor)
    {
        this.ciudad = ciudad;
        this.creadoPor = creadoPor;
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.id = id;
        this.imagen = imagen;
        this.lat = lat;
        this.lon = lon;
        this.nombre = nombre;
        this.pais = pais;
        this.tipos = tipos;
    }

    public Evento()
    {

    }

    protected Evento(Parcel in)
    {
        ciudad = in.readString();
        creadoPor = in.readString();
        descripcion = in.readString();
        fechaFin = in.readString();
        fechaInicio = in.readString();
        horaInicio = in.readString();
        id = in.readString();
        imagen = in.readString();

        if (in.readByte() == 0)
        {
            lat = null;
        }
        else
        {
            lat = in.readDouble();
        }

        if (in.readByte() == 0)
        {
            lon = null;
        }
        else
        {
            lon = in.readDouble();
        }

        nombre = in.readString();
        pais = in.readString();
        tipos = in.createTypedArrayList(Tipo.CREATOR);
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>()
    {
        @Override
        public Evento createFromParcel(Parcel in)
        {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size)
        {
            return new Evento[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(ciudad);
        dest.writeString(creadoPor);
        dest.writeString(descripcion);
        dest.writeString(fechaFin);
        dest.writeString(fechaInicio);
        dest.writeString(horaInicio);
        dest.writeString(id);
        dest.writeString(imagen);

        if (lat == null)
        {
            dest.writeByte((byte) 0);
        }
        else
        {
            dest.writeByte((byte) 1);
            dest.writeDouble(lat);
        }

        if (lon == null)
        {
            dest.writeByte((byte) 0);
        }
        else
        {
            dest.writeByte((byte) 1);
            dest.writeDouble(lon);
        }

        dest.writeString(nombre);
        dest.writeString(pais);
        dest.writeTypedList(tipos);
    }

    public String getCiudad()
    {
        return ciudad;
    }

    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }

    public String getCreadoPor()
    {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor)
    {
        this.creadoPor = creadoPor;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getFechaFin()
    {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio()
    {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio()
    {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio)
    {
        this.horaInicio = horaInicio;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getImagen()
    {
        return imagen;
    }

    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }

    public Double getLat()
    {
        return lat;
    }

    public void setLat(Double lat)
    {
        this.lat = lat;
    }

    public Double getLon()
    {
        return lon;
    }

    public void setLon(Double lon)
    {
        this.lon = lon;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public List<Tipo> getTipos()
    {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos)
    {
        this.tipos = tipos;
    }

}
