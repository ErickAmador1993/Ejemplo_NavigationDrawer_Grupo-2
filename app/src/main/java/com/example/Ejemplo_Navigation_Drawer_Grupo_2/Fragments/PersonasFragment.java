package com.example.Ejemplo_Navigation_Drawer_Grupo_2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Ejemplo_Navigation_Drawer_Grupo_2.Adaptadores.AdapterPersonas;
import com.example.Ejemplo_Navigation_Drawer_Grupo_2.Entidades.Persona;
import com.example.Ejemplo_Navigation_Drawer_Grupo_2.Interfaces.iComunicaFragments;
import com.example.Ejemplo_Navigation_Drawer_Grupo_2.R;

import java.util.ArrayList;

public class PersonasFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;


    AdapterPersonas adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<Persona> listaPersonas;

    EditText txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personas_fragment,container,false);
        txtnombre = view.findViewById(R.id.txtnombre);

        recyclerViewPersonas = view.findViewById(R.id.recyclerView);
        listaPersonas = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listaPersonas.add(new Persona("Andy Carrasco","201610110304",R.drawable.gohan_cara1));
        listaPersonas.add(new Persona("Erick Mejia","201830060004",R.drawable.goku_cara2));
        listaPersonas.add(new Persona("Jorge Asfura","201910130119",R.drawable.krilin_cara4));
        listaPersonas.add(new Persona("Axel Varela","201810060034",R.drawable.picoro_cara5));
        listaPersonas.add(new Persona("Sara Pavon","201810060306", R.drawable.androide18_cara_8));
        listaPersonas.add(new Persona("Daniel Guardado","201710060123",R.drawable.vegueta_cara7));
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterPersonas(getContext(), listaPersonas);
        recyclerViewPersonas.setAdapter(adapterPersonas);

        adapterPersonas.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nombre = listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre();
               txtnombre.setText(nombre);
               Toast.makeText(getContext(), "Seleccionó: "+listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarPersona(listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
        //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
