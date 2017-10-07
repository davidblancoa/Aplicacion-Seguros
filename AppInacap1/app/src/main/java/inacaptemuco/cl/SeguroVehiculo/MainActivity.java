package inacaptemuco.cl.SeguroVehiculo;

import android.app.Activity;
import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import inacaptemuco.cl.appinacap1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //Creamos objetos relacionados al layout
    EditText patente;
    Spinner marca;
    EditText modelo;
    EditText ano;
    EditText uf;
    Button calcular;
    String marcaSeleccionada;
    String modeloSeleccionado;
    int anoCorte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asociamos los objetos con un elemento de la interfaz
        patente     =   (EditText) findViewById(R.id.edt_patente);
        marca       =   (Spinner) findViewById(R.id.spn_marca);
        modelo      =   (EditText) findViewById(R.id.edt_modelo);
        //resultado   =    (TextView) findViewById(R.id.rxv_resultado);
        ano         =   (EditText)findViewById(R.id.edt_ano);
        uf          =   (EditText)findViewById(R.id.edt_uf);
        calcular    =   (Button) findViewById(R.id.btn_calcular);


        //Adaptador para Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marca_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        marca.setAdapter(adapter);

        //Habilidamos la posibilidad de que el boton ejecute operaciones en el metodo OnClick
        calcular.setOnClickListener(this);

        //Habilitamos la posibilidad de que el Spinner ejecute el método OnItemSelected
        marca.setOnItemSelectedListener(this);



    }

    @Override
    public void onClick(View view) {
        //capturamos la edad ingresada
        //capturamos el peso actual
        int ano_ingresado =Integer.parseInt(ano.getText().toString());
        int uf_ingresado  =Integer.parseInt(uf.getText().toString());
        String modelo_ingresado =(modelo.getText().toString());
        String validez = determinarValidez(ano_ingresado);
        String patente_ingresada =(patente.getText().toString());

        //se invoca metodo para obtener el peso ideal y estado peso
        int precio   = calcularPrecio(uf_ingresado,ano_ingresado);
        //Mostramos un valor literal en el TextView de resultado
        //resultado.setText("El peso ideal es : "+peso_ideal);

        // Establecemos que desde MainActivity vamos a abrir un activity llamado ResultadoActivity
        Intent intento = new Intent(MainActivity.this,ResultadoActivity.class);
        //Establecemos los datos que queremos enviar al destino(ResultadoActivity)
        intento.putExtra("p_ano_ingresado",ano_ingresado);
        intento.putExtra("p_uf_actual",uf_ingresado);
        intento.putExtra("p_precio",precios);
        intento.putExtra("p_marca",marcaSeleccionada);
        intento.putExtra("p_modelo",modelo_ingresado);
        intento.putExtra("p_validez", validez);
        intento.putExtra("p_patente", patente_ingresada);



        if(marcaSeleccionada == null){
            Toast.makeText(this, "No ingreso año del Vehiculo", Toast.LENGTH_SHORT).show();
        }/*else if(uf_ingresado==null) {
            Toast.makeText(this, "No ingreso Valor de la UF", Toast.LENGTH_SHORT).show();
        }*/else if(modelo_ingresado==null){
            Toast.makeText(this, "No ingreso el Modelo del Vehiculo", Toast.LENGTH_SHORT).show();
        }else if(patente_ingresada==null){
            Toast.makeText(this, "No ingreso la patente", Toast.LENGTH_SHORT).show();
        }



        startActivity(intento);


    }
    //Funcion que efectua el calculo del peso ideal
    public int calcularPrecio( int ano, int uf){
        int precios;
        precios=(ano-2007)*uf;
        return precios;
    }
    //funcion del estado del peso actual comparandolo con el peso ideal
    public String determinarValidez(int ano){
        String estado;
        int anoCorte=2007;

        if(ano==anoCorte){
            estado="ES ASEGURABLE";
        }else if(ano> anoCorte) {
            estado="ES ASEGURABLE";
        }else {
            estado= "NO ES ASEGURABLE";
        }
        return estado;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //resultado.setText("Seleccionado "+ adapterView.getItemAtPosition(i));
        marcaSeleccionada = adapterView.getItemAtPosition(i).toString();
        modeloSeleccionado = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

   /* private class ProcessJSON extends AsyncTask<String, Void, String>{
        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){
            TextView tv = (TextView) findViewById(R.id.txv_resultado);

            if(stream !=null){
                try{
                    // Obtenemos todos los datos HTTP medinte un objeto JSONObject
                    JSONObject reader= new JSONObject(stream);

                    // Obtenemos uno de los valores que necesitamos
                    String nombres = reader.getString("NOMBRES");


                    tv.setText("NOMBRES "+ nombres);


                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }*/
}
