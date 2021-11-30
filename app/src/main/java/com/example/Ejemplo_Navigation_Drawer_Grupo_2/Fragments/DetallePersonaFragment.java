package com.example.Ejemplo_Navigation_Drawer_Grupo_2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Ejemplo_Navigation_Drawer_Grupo_2.Entidades.Persona;
import com.example.Ejemplo_Navigation_Drawer_Grupo_2.R;

public class DetallePersonaFragment extends Fragment {
    TextView nombre;
    ImageView imagen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        imagen = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoPersona = getArguments();
        Persona persona = null;;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoPersona !=null){
            persona = (Persona) objetoPersona.getSerializable("objeto");
            imagen.setImageResource(persona.getImagenid());
            nombre.setText(persona.getNombre());
        }
        return view;
    }
}
