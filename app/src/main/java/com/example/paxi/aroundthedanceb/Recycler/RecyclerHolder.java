package com.example.paxi.aroundthedanceb.Recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class RecyclerHolder extends RecyclerView.ViewHolder
{
    private TextView holder_NameEvent, holder_FechaEvent, holder_UbicacionEvent;
    private ImageView holder_ImageEvent;
    StorageReference storageRf;

    public RecyclerHolder(View itemView )
    {
        super(itemView);

        holder_ImageEvent = (ImageView) itemView.findViewById(R.id.recyclerlinea_imageview_event);
        holder_NameEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_nameevent);
        holder_FechaEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_fechaevent);
        holder_UbicacionEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_ubicacionevent);
        storageRf  = FirebaseStorage.getInstance().getReference();
    }

    public void bind(Evento evento)
    {
        holder_NameEvent.setText(evento.getNombre());
        holder_FechaEvent.setText(evento.getFechaInicio());
        holder_UbicacionEvent.setText(evento.getPais() + ", " + evento.getCiudad());
        cargarImagen(evento.getImagen());
    }
    private void cargarImagen(String nombre){

        storageRf.child("eventos/"+nombre).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(itemView).load(uri.toString()).into(holder_ImageEvent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

}

