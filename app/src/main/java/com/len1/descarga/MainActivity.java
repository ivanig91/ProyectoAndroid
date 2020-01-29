package com.len1.descarga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Lugar> lugares  = new ArrayList<Lugar>();
    LugaresAdapter adapter;
    public static  int cont =0;
    public static String longitud;
    public static String latitud;
    public static String nombreLugar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new LugaresAdapter(this);
        ListView lali = findViewById(R.id.lvLali);
        lali.setAdapter(adapter);
        lali.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lugar lug = (Lugar) parent.getAdapter().getItem(position);

                Log.i("Descripcion",lug.descripcion);
                Intent intent = new Intent(view.getContext(),lugarDetalle.class);
                intent.putExtra("objeto", (Serializable) lug);
                startActivityForResult(intent,0);

            }
        });

        if(lugares.size()==0){
            TareaDescarga tarea = new TareaDescarga();
            tarea.execute((Constantes.URL));
        }





    }


    @Override
    protected void onPostResume() {
        super.onPostResume();


    }

    private void cargarLugares(){
        TareaDescarga tarea = new TareaDescarga();
        tarea.execute(Constantes.URL);


    }
    private class TareaDescarga extends AsyncTask<String,Void,Void> {
        MainActivity main;

        private boolean error;

        {
            error = false;
        }

        private ProgressDialog dialog;



        @Override
        protected Void doInBackground(String... strings) {

            String resultado = null;
            JSONObject json = null;
            JSONArray jsonArray= null;

            try {
                URL url = new URL(Constantes.URL);
                HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linea = null;

                while((linea = br.readLine())!=null){
                    sb.append(linea+"\n");

                }

                conexion.disconnect();
                br.close();
                resultado = sb.toString();

                json = new JSONObject(resultado);
                jsonArray = json.getJSONArray("@graph");

                String id = null;
                String title =null;
                String calle =null;
                String descripcion = null;
                String longitud =null;
                String latitud = null;
                String html =null;


                // Ahora es donde comienzo a parsear, me gusta esto



                Lugar lugar;
               int cpMin=28000;
               int cpMax=28055;
               int cp;
               String cadenaCP;
               boolean flip= false;
                for(int i =0; i<100;i++){
                    cadenaCP = jsonArray.getJSONObject(i).getJSONObject("address").getString("postal-code");
                    if(!cadenaCP.equals("")){
                        cp = Integer.parseInt(jsonArray.getJSONObject(i).getJSONObject("address").getString("postal-code"));
                        if(cp>=cpMin && cp<=cpMax){
                            flip=true;
                        }
                    }

                    if(jsonArray.getJSONObject(i).has("location") && flip ){

                        id = jsonArray.getJSONObject(i).getString("id");

                        title = jsonArray.getJSONObject(i).getString("title");

                        calle =  jsonArray.getJSONObject(i).getJSONObject("address").getString("street-address");
                        descripcion = jsonArray.getJSONObject(i).getJSONObject("organization").getString("organization-desc");
                        MainActivity.cont++;
                        longitud = jsonArray.getJSONObject(i).getJSONObject("location").getString("longitude");

                        latitud = jsonArray.getJSONObject(i).getJSONObject("location").getString("latitude");

                        // aqui comienzan las cosas para descargar la foto
                        html = jsonArray.getJSONObject(i).getString("relation");

                        lugar = new Lugar();
                        lugar.setId(id);
                        lugar.setNombre(title);
                        lugar.setCalle(calle);
                        lugar.setDescripcion(descripcion);
                        lugar.setLatitud(Float.parseFloat(latitud));
                        lugar.setLongitud(Float.parseFloat(longitud));
                        lugar.setFotoString(html);
                        lugares.add(lugar);


                    }


                    //El problema hasta el momento parece que el bucle se hace solo una vez
                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("EXCEP","Si esto sale es que salto una excep");
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            adapter.clear();
            lugares = new ArrayList<Lugar>();


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

            adapter.notifyDataSetChanged();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("App de ivan villarreal ");
            dialog.show();
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



            if(error){
                Toast.makeText(getApplicationContext(),"Ha ocurrido un error",Toast.LENGTH_SHORT).show();
                return;
            }

            if(dialog!= null){
                dialog.dismiss();
                adapter.notifyDataSetChanged();


            }
            Log.i("CONT2",Integer.toString(MainActivity.cont) );
            Log.i("Lugares", String.valueOf(lugares.size()));


        }
    }

}
