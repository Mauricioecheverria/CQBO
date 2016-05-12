package com.example.mauricio.cqbo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistroUsuario extends AppCompatActivity {

    EditText mEdit;
    Spinner spComunas,spProvincia, Pref1, Pref2, Pref3;
    RadioButton Male, Female;

    ArrayAdapter<CharSequence> aa_elqui;
    ArrayAdapter<CharSequence> aa_limari;
    ArrayAdapter<CharSequence> aa_choapa;
    ArrayAdapter<CharSequence> aa_default;

    private EditText nombre_usuario;
    private EditText apellido_usuario;
    private EditText telefono_usuario;
    private EditText email_usuario;
    private EditText password_usuario;
    private EditText fecnac_usuario;
    private Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        Spinner spPaises = (Spinner) findViewById(R.id.provincia);
        // Asigno el listener al Spinner de paises
        spPaises.setOnItemSelectedListener(new SpinnerListener());

        nombre_usuario = (EditText) findViewById(R.id.nombre_usuario);
        apellido_usuario = (EditText) findViewById(R.id.apellido_usuario);
        telefono_usuario = (EditText) findViewById(R.id.telefono_usuario);
        email_usuario = (EditText) findViewById(R.id.email_usuario);
        password_usuario = (EditText) findViewById(R.id.password_usuario);
        spComunas = (Spinner) findViewById(R.id.comuna);
        spProvincia = (Spinner) findViewById(R.id.provincia);
        Pref1 = (Spinner) findViewById(R.id.preferencia1);
        Pref2 = (Spinner) findViewById(R.id.preferencia2);
        Pref3 = (Spinner) findViewById(R.id.preferencia3);
        Male = (RadioButton) findViewById(R.id.masculino);
        Female = (RadioButton) findViewById(R.id.femenino);






        //Insertamos los datos de la persona.
        insertar = (Button) findViewById(R.id.insertar);
        insertar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //if(!dni.getText().toString().trim().equalsIgnoreCase("")||
                //      !nombre.getText().toString().trim().equalsIgnoreCase("")||
                //      !telefono.getText().toString().trim().equalsIgnoreCase("")||
                //       !email.getText().toString().trim().equalsIgnoreCase(""))
                new Insertar(RegistroUsuario.this).execute();
                //  else
                //      Toast.makeText(WebServiceExample.this, "Hay información por rellenar", Toast.LENGTH_LONG).show();
                //  }

                //});
            }
        });
    }
    private boolean insertar(){
        String pref1_usuario = Pref1.getSelectedItem().toString();
        String pref2_usuario = Pref2.getSelectedItem().toString();
        String pref3_usuario = Pref3.getSelectedItem().toString();
        String comuna_usuario = spComunas.getSelectedItem().toString();
        String provincia_usuario = spProvincia.getSelectedItem().toString();

        HttpClient httpclient;
        List<NameValuePair> nameValuePairs;
        HttpPost httppost;
        httpclient=new DefaultHttpClient();
        httppost= new HttpPost("http://www.cqbo.cl/insertarUsuario.php"); // Url del Servidor
        //Añadimos nuestros datos
        nameValuePairs = new ArrayList<NameValuePair>(10);
        nameValuePairs.add(new BasicNameValuePair("nombre",nombre_usuario.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("apellido",apellido_usuario.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("telefono",telefono_usuario.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("email",email_usuario.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("password",password_usuario.getText().toString().trim()));
        nameValuePairs.add(new BasicNameValuePair("comuna",comuna_usuario.trim()));
        nameValuePairs.add(new BasicNameValuePair("provincia",provincia_usuario.trim()));
        nameValuePairs.add(new BasicNameValuePair("pref1",pref1_usuario.trim()));
        nameValuePairs.add(new BasicNameValuePair("pref2",pref2_usuario.trim()));
        nameValuePairs.add(new BasicNameValuePair("pref3",pref3_usuario.trim()));


        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httpclient.execute(httppost);
            return true;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    // Defino los adaptadores para cada pais y asigno el que corresponde
    private void cargaSpinnerCiudad(int pais){

        spComunas = (Spinner) findViewById(R.id.comuna);
        aa_elqui = ArrayAdapter.createFromResource
                (this, R.array.arrayElqui,
                        android.R.layout.simple_spinner_item);
        aa_limari = ArrayAdapter.createFromResource
                (this, R.array.arrayLimari,
                        android.R.layout.simple_spinner_item);
        aa_choapa = ArrayAdapter.createFromResource
                (this, R.array.arrayChoapa,
                        android.R.layout.simple_spinner_item);
        aa_default = ArrayAdapter.createFromResource
                (this, R.array.arrayDefecto,
                        android.R.layout.simple_spinner_item);

        switch (pais) {
            case 1: spComunas.setAdapter(aa_elqui);
                break;
            case 2: spComunas.setAdapter(aa_limari);
                break;
            case 3: spComunas.setAdapter(aa_choapa);
                break;
            default: spComunas.setAdapter(aa_default);
                break;
        }
    }

    public void selectDate(View view) {
        DialogFragment newFragment = new SelectDateFragment();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void populateSetDate(int year, int month, int day) {
        mEdit = (EditText)findViewById(R.id.fecnac_usuario);
        mEdit.setText(day+"/"+month+"/"+year);

    }

    //AsyncTask para insertar Personas
    class Insertar extends AsyncTask<String,String,String> {

        private Activity context;

        Insertar(Activity context){
            this.context=context;
        }
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            if(insertar())
                context.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "Persona insertada con éxito", Toast.LENGTH_LONG).show();

                    }
                });
            else
                context.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Toast.makeText(context, "Persona no insertada con éxito", Toast.LENGTH_LONG).show();
                    }
                });
            return null;
        }
    }

    public class SpinnerListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            // Lamo a un método para cargar el Spinner de ciudades
            // pasandole la posicion del elemento seleccionado en
            // el Spinner de paises
            cargaSpinnerCiudad(parent.getSelectedItemPosition());
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
    }
}