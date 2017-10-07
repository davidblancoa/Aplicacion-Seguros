package inacaptemuco.cl.SeguroVehiculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import inacaptemuco.cl.appinacap1.R;

public class ResultadoActivity extends AppCompatActivity {

    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        //Asociamos la variable de objeto con el elemento de la interfaz
        resultado = (TextView) findViewById(R.id.txv_resultado);

        //Capturamos el intento recibido
        Intent intento = getIntent();

        //Obtenemos los datos enviados desde el activity anterior
        Bundle datos_recibidos = intento.getExtras();
        //Obtenemos uno de los datos enviados a través de putExtra
        int ano_recibido       = datos_recibidos.getInt("p_ano_ingresado");
        String marca_recibida  = datos_recibidos.getString("p_marca");
        String modelo_recibido  = datos_recibidos.getString("p_modelo");
        String patente_recibida  = datos_recibidos.getString("p_patente");
        String precio_recibido  = datos_recibidos.getString("p_precio");


        //Utilizamos el dato para desplegar en TextView
        resultado.setText("Ano Recibido " +
                ""+ano_recibido + " . Marca " +
                "" + marca_recibida + ", Modelo " +
                ""+ modelo_recibido+""+"Patente"+ patente_recibida+""+"Valor Seguro"+ precio_recibido);


    }
}