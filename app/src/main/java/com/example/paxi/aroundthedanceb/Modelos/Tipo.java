
package com.example.paxi.aroundthedanceb.Modelos;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Tipo  implements Parcelable{

    String nombre;
    List<Estilo> estilos;

    public Tipo()
    {

    }

    public Tipo(String nombre)
    {
        this.nombre = nombre;
    }

    public Tipo(String nombre, List<Estilo> estilos)
    {
        this.nombre = nombre;
        this.estilos = estilos;
    }


    protected Tipo(Parcel in)
    {
        nombre = in.readString();
        estilos = in.createTypedArrayList(Estilo.CREATOR);
    }

    public static final Creator<Tipo> CREATOR = new Creator<Tipo>()
    {
        @Override
        public Tipo createFromParcel(Parcel in)
        {
            return new Tipo(in);
        }

        @Override
        public Tipo[] newArray(int size)
        {
            return new Tipo[size];
        }
    };

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public List<Estilo> getEstilos()
    {
        return estilos;
    }

    public void setEstilos(List<Estilo> estilos)
    {
        this.estilos = estilos;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(nombre);
        dest.writeTypedList(estilos);
    }
}