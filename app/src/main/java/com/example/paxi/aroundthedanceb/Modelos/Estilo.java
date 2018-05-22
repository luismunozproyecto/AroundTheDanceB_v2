
package com.example.paxi.aroundthedanceb.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Estilo implements Parcelable
{
    String nombre;
    List<String> categorias;

    public Estilo()
    {

    }

    public Estilo(String nombre)
    {
        this.nombre = nombre;
    }

    public Estilo(String nombre, List<String> categorias)
    {
        this.nombre = nombre;
        this.categorias = categorias;
    }

    protected Estilo(Parcel in)
    {
        nombre = in.readString();
        categorias = in.createStringArrayList();
    }

    public static final Creator<Estilo> CREATOR = new Creator<Estilo>()
    {
        @Override
        public Estilo createFromParcel(Parcel in) {
            return new Estilo(in);
        }

        @Override
        public Estilo[] newArray(int size) {
            return new Estilo[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(nombre);
        dest.writeStringList(categorias);
    }
}


