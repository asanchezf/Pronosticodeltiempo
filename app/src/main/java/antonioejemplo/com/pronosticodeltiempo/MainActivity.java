package antonioejemplo.com.pronosticodeltiempo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;//Cola de peticiones de Volley. se encarga de gestionar automáticamente el envió de las peticiones, la administración de los hilos, la creación de la caché y la publicación de resultados en la UI.


    private TextView txtrespuesta, txtcoordenadas,txtbase,txtwind,txtclima,txtinformacion,txtnubes,txtestation;
    private EditText txtciudad;
    //private EditText txtpais;
    private Button btnResultado;
    private FloatingActionButton btnfloat;
    private ImageView icono;
    private ImageView imgToolbar;
    private ImageView imgclima;


    //private String png;



    private static final String LOGTAG = "Pronosticodeltiempo";//Constante para gestionar la escritura en el Log

    private static long back_pressed;//Contador para cerrar la app al pulsar dos veces seguidas el btón de cerrar. Se gestiona en el evento onBackPressed

    private JsonObjectRequest myjsonObjectRequest;
    //Para el RecyclerView
    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private Adaptador adaptador;
    private List<Modelo> listdatos;//Se le enviará al Adaptador


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //App bar style NoActionBar es obligatorio para que salga la toolbar o bien deshabilitar la ActionBar
        //con windowActionBar a false y windowstitle a true dentro del archivo styles.xml

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);

        setSupportActionBar(toolbar);//Seteamos la toobar para que se comporte como una ActionBar
        LinearLayoutManager llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);

        CollapsingToolbarLayout ctlLayout = (CollapsingToolbarLayout) findViewById(R.id.ctlLayout);
        ctlLayout.setTitle("El tiempo");

        FloatingActionButton btnfloat=(FloatingActionButton)findViewById(R.id.btnFab);


        txtciudad = (EditText) findViewById(R.id.txtciudad);
        //txtpais = (EditText) findViewById(R.id.txtpais);
        txtrespuesta = (TextView) findViewById(R.id.txtrespuesta);
        txtclima= (TextView) findViewById(R.id.txtclima);
        txtcoordenadas = (TextView) findViewById(R.id.txtcoordenadas);
        txtbase= (TextView) findViewById(R.id.textbase);
        txtwind= (TextView) findViewById(R.id.txtwind);
        btnResultado = (Button) findViewById(R.id.btnresultado);
        txtinformacion=(TextView)findViewById(R.id.txtinformacion);
        txtnubes=(TextView)findViewById(R.id.txtnubes);
        icono=(ImageView)findViewById(R.id.icono);
        txtestation=(TextView)findViewById(R.id.txtstation);

        imgToolbar=(ImageView)findViewById(R.id.imgToolbar);
        imgclima=(ImageView)findViewById(R.id.imgclima);

        // Preparar lista
        listaUI = (RecyclerView) findViewById(R.id.lista);
        listaUI.setHasFixedSize(true);//Va a tener tamaño fijo

        listaUI.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //adaptador=new Adaptador(getApplicationContext());
        //listaUI.setAdapter(adaptador);//Se lo pasamos aunque esté vacío para que el log no devuelva error: RecyclerView: No adapter attached; skipping layout
       // adaptador.notifyDataSetChanged();


//////////////=================LLenar el Recyclerview a modo de prueba con datos literales======================//////////////////////////////////////

        /*listdatos=new ArrayList<Modelo>();

        for (int i=0;i<50;i++){

            //datos.add(new Modelo("Día" +i,"Nubes"+i,""+i));
            listdatos.add(new Modelo("Día" +i,"Nubes"+i));
            Log.v("Llenando aaraylist",listdatos.get(i).getDia());
        }

        adaptador = new Adaptador(listdatos,this);
        listaUI.setAdapter(adaptador);*/

//////////////////==============================================================================================///////////////////////////////////////7

        /*String parametro = String.format(txtciudad.getText().toString(), txtpais.getText().toString());
        String URL_BASE = "http://api.openweathermap.org/data/2.5/weather?q=";
        String URL_BASE2 = "http://api.openweathermap.org/data/2.5/weather?q=" + txtciudad.getText().toString() + txtpais.getText().toString() + "&appid=b1b15e88fa797225412429c1c50c122a";
        String URL_BASE3 = "http://api.openweathermap.org/data/2.5/weather?q=" + txtciudad.getText().toString() + "," + txtpais.getText().toString() + "&appid=b1b15e88fa797225412429c1c50c122a";
        String URL_BASE4 = "http://api.openweathermap.org/data/2.5/weather?q=" + txtciudad.getText().toString() + "," + txtpais.getText().toString();
*/
        //Se ha solicitado una nueva apikey a la página del WS.Correo yahoo petyl@

        //&units=metric..Sistema métrico
        //&lang=ES..Lenguaje

        //String patron = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric&appid=ffff21faa9754c531c28bad3ddc19605";
        //String patron = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=ffff21faa9754c531c28bad3ddc19605";

        //Log.v(LOGTAG,"Uri antes de llamada: "+Uri);

        requestQueue = Volley.newRequestQueue(this);
        //cargarImagenToolbar();//Para cambiar la imagen de la toolbar en ejecución.

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borraDatos();
            }
        });



        if (btnfloat != null) {
            btnfloat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Si no informan la ciudad no accedemos a la API...
                    if (txtciudad.getText().toString().equals("")) {

                        Snackbar snack = Snackbar.make(v, R.string.ciudad, Snackbar.LENGTH_LONG);
                        ViewGroup group = (ViewGroup) snack.getView();
                        group.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        snack.show();
                    } else {

                        //REALIZAMOS LA PRIMERA PETICIÓN.
                        immediateRequestPronosticos();
                        immediateRequestTiempoActual();//Nos trae el tiempo en las últimas tres horas
                        //immediateRequestPronosticos();//Nos trae los pronósticos

                        //Lineas para ocultar el teclado virtual (Hide keyboard)
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            });
        }


    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();

        //adaptador.notifyDataSetChanged();
        //listaUI.setAdapter(adaptador);
        //adaptador.notifyDataSetChanged();
    }

    private void immediateRequestPronosticos() {
        //PETICIÓN DATOS DE PRONÓSTICOS DEL TIEMPO:
        //Es una segunda petición a otro url:
        //http://api.openweathermap.org/data/2.5/forecast?q=Mostoles,es&mode=json&units=metric&lang=ES&appid=ffff21faa9754c531c28bad3ddc19605

        //Inicializamos la cola de peticiones...
        //requestQueueAdaptador = Volley.newRequestQueue(contexto);

        // Etiqueta utilizada para cancelar la petición
        String tag_json_obj = "json_obj_req_adaptador";
        String ciudad = txtciudad.getText().toString();
        String pais = "";
        String Uri;
        //http://api.openweathermap.org/data/2.5/forecast?q=%s,%s&mode=json&units=metric&lang=ES&appid=ffff21faa9754c531c28bad3ddc19605
        String patronUrl = "http://api.openweathermap.org/data/2.5/forecast?q=%s,%s&mode=json&units=metric&lang=ES&appid=ffff21faa9754c531c28bad3ddc19605";
        Uri = String.format(patronUrl, ciudad, pais);

        //Log.v(LOGTAG, "Ha llegado a immediateRequestPronosticos. Uri: " + Uri);

        myjsonObjectRequest = new MyJSonRequestImmediate(
                Request.Method.GET,
                Uri,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        String fecha="";
                        Double temperatura=null;
                        int presion=0;
                        int humedad=0;
                        Double temp_min=null;
                        Double temp_max=null;
                        Double speed=null;
                        int clouds=0;
                        String icon="";
                        String description="";
                        listdatos=new ArrayList<Modelo>();
                        Modelo modelo = null;

                        try {


                            //Todo el json está en response. Lo recorremos

                            //YA NO NECESITAMOS RECORRER EL OBJETO PQ LOS DATOS LOS TRAEMOS DESDE EL MAIN
                            /*for ( int i = 0; i < response.length(); i++) {
                                //Log.v(LOGTAG,"Número de registros: "+response.length());
                                //RECOJEMOS DATOS EN VARIABLES:

                                //city-es un objeto que contiene otros 6 objetos:
                                // id,name,coord,country y population.
                                JSONObject city = response.getJSONObject("city");

                                //Sacamos name. Es tipo Auto. No hay que hacer conversión a tipo objeto:
                                nombre = city.getString("name");//No debe ir al Adapatador
                                //Log.v(LOGTAG,"Estamos en raiz-nombre: "+nombre+"Soy i: "+i);

                                //Sacamos coord. Es un objeto:NO debe ir al Adaptador
                                JSONObject coord = city.getJSONObject("coord");
                                //JSONObject coord = response.getJSONObject("coord");
                                longitud = coord.getDouble("lon");
                                latitud = coord.getDouble("lat");
                                //Log.v(LOGTAG,"Estamos en raiz-coordenadas: "+longitud+latitud+"Soy i: "+i);

                            }*/

                                //"list"--EN ESTE CASO ES UN ARRAY DE OBJETOS:
                                JSONArray json_array = response.getJSONArray("list");

                                Log.v(LOGTAG,"Estamos en list.Total"+" "+json_array.length());//Trae 40-ok

                                //LIST ES UN ARRAY. LO RECORREMOS Y EXTRAEMOS SUS VALORES==========================
                                for (int y = 0; y < json_array.length(); y++) {
                                    //Log.v(LOGTAG, "Estamos en list dentro de su bucle" + " " + json_array.length());
                                    //Sacamos fecha. Es tipo Auto.No hay
                                    fecha=json_array.getJSONObject(y).getString("dt_txt");
                                    Log.v(LOGTAG,"Estamos en list obteniendo fecha: "+fecha+"Soy y: "+y);

                                    //main. Dentro de list. Es un objeto


                        ////////========================================================================================////////////////
                                    //PRIMERO RECOGEMOS DATOS DEL DÍA--getJSONObject(0)
                                   /* JSONObject main_today = json_array.getJSONObject(y).getJSONObject("main");
                                    if(i==0) {
                                        temperatura_today = main_today.getDouble("temp");
                                        presion_today = main_today.getInt("pressure");
                                        humedad_today = main_today.getInt("humidity");
                                        temp_min_today = main_today.getDouble("temp_min");
                                        temp_max_today = main_today.getDouble("temp_max");
                                    }*/

                                    //RECOGEMOS EL RESTO DE DATOS PARA PASÁRSELOS AL ADAPTADOR
                                    JSONObject main = json_array.getJSONObject(y).getJSONObject("main");
                                    temperatura = main.getDouble("temp");
                                    presion = main.getInt("pressure");
                                    humedad = main.getInt("humidity");
                                    temp_min = main.getDouble("temp_min");
                                    temp_max = main.getDouble("temp_max");

                                    //Log.v(LOGTAG,"Estamos en list obteniendo main: "+temperatura+presion+humedad+temp_min+temp_max+"Soy y: "+y);
                                    Log.v(LOGTAG,"Estamos en list obteniendo main: "+temperatura+"Soy y: "+y);
                                    //wind. Dentro de list. Es un objeto
                                    //JSONObject windkk = response2.getJSONObject("wind");

                                    //RECOGEMOS DATOS DEL viento del DÍA--getJSONObject(0)
                                    //YA NO NECESITAMOS EL OBJETO PQ LOS DATOS LOS TRAEMOS DESDE EL MAIN
                                    //JSONObject wind_today = json_array.getJSONObject(0).getJSONObject("wind");
                                    //speed_today = wind_today.getDouble("speed");

                                    //RESTO DE DATOS
                                    JSONObject wind = json_array.getJSONObject(y).getJSONObject("wind");
                                    speed = wind.getDouble("speed");
                                    //deg=wind.getDouble("deg");
                                    Log.v(LOGTAG,"Estamos en list obteniendo velocidad del viento: "+speed+"Soy y: "+y);

                                    //Nubes clouds.Dentro de list. Es un objeto
                                    //RECOGEMOS DATOS DEL DÍA--getJSONObject(0)
                                    //YA NO NECESITAMOS RECORRER EL OBJETO PQ LOS DATOS LOS TRAEMOS DESDE EL MAIN
                                    //JSONObject nubes_today = json_array.getJSONObject(0).getJSONObject("clouds");
                                    //clouds_today = nubes_today.getInt("all");

                                    //RESTO DE DATOS
                                    JSONObject nubes = json_array.getJSONObject(y).getJSONObject("clouds");
                                    clouds = nubes.getInt("all");
                                    Log.v(LOGTAG,"Estamos en list obteniendo nubes: "+clouds+" "+"Soy y: "+y);

                                    // JSONArray json_array_weather = response.optJSONArray("weather");Error
                                    //JSONArray json_array_weather = json_array.optJSONArray("weather");



                                    //Definimos weather. Es un array de objetos que está dentro de list
                                    //PRIMERO RECOGEMOS DATOS DEL DÍA--getJSONObject(0)
                                    //JSONArray json_array_weather_today=json_array.getJSONObject(0).getJSONArray("weather");


                                    JSONArray json_array_weather=json_array.getJSONObject(y).getJSONArray("weather");

                                    Log.v(LOGTAG,"Estamos en weather"+" "+json_array_weather.getString(0));

                                    //weather ES UN ARRAY. LO RECORREMOS Y EXTRAEMOS SUS VALORES.
                                    for (int z = 0; z < json_array_weather.length(); z++) {

                                        //RESTO DE DATOS PARA EL ADAPTADOR
                                        icon = json_array_weather.getJSONObject(z).getString("icon");
                                        description = json_array_weather.getJSONObject(z).getString("description");
                                       // Log.v(LOGTAG,"descripcion_today: "+description_today);

                                        //RELLENAMOS EL MODELO
                                        modelo=new Modelo();
                                        modelo.setDia(fecha);
                                        modelo.setHumedad(humedad);
                                        modelo.setPresion(presion);
                                        modelo.setPronostico(description);
                                        modelo.setSpeed(speed);
                                        modelo.setTemperatura(temperatura);
                                        modelo.setTemp_max(temp_max);
                                        modelo.setTemp_min(temp_min);
                                        modelo.setImagen(icon);

                                        //CARGAMOS EL ARRAY
                                        listdatos.add(modelo);


                                        Log.v(LOGTAG, "ArrayList lleno: "+listdatos.size());

                                    }//FIN json_array_wheather


                                }//adaptadorFIN json_array


                            adaptador = new Adaptador(listdatos,getApplicationContext());
                            listaUI.setAdapter(adaptador);

                            btnResultado.setEnabled(true);
                            imgclima.setVisibility(View.INVISIBLE);
                            listaUI.setVisibility(View.VISIBLE);

                            //Log.v(LOGTAG, "JSON uriprueba: " + uriprueba);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(LOGTAG, "Error Respuesta en JSON: " );
                        }

                        //priority = Request.Priority.IMMEDIATE;

                    }//fin onresponse

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOGTAG, "Error Respuesta en JSON: " + error.getMessage());
                        //txtrespuesta.setText(error.toString());
                        txtinformacion.setText(R.string.conexionerror);

                        Snackbar snack = Snackbar.make(txtinformacion, R.string.conexionerror, Snackbar.LENGTH_LONG);
                        ViewGroup group = (ViewGroup) snack.getView();
                        group.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        snack.show();

                    }
                }


        ) ;


        // Añadir petición a la cola
        //requestQueue.add(request);//Sin tener en cuenta el AppController.
        // Añadimos la petición a la cola de peticiones teniendo en cuenta al AppController
        AppController.getInstance().addToRequestQueue(myjsonObjectRequest, tag_json_obj);

        //AppController.getInstance().getRequestQueue().getCache().remove(Uri);//Eliminar cache
        //AppController.getInstance().getRequestQueue().getCache().invalidate(Uri, true);//Desactivar cache
    }


    private void immediateRequestTiempoActual() {
        //PRIMERA PETICIÓN DATOS DEL TIEMPO ACTUAL:
        //LLAMADA A LA URL:"http://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric&lang=ES&appid=ffff21faa9754c531c28bad3ddc19605";


        String tag_json_obj_actual = "json_obj_req_actual";

        String ciudad = txtciudad.getText().toString();

        String pais = "";
        String patronUrl = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric&lang=ES&appid=ffff21faa9754c531c28bad3ddc19605";
        String uri = String.format(patronUrl, ciudad, pais);


        Log.v(LOGTAG, "Ha llegado a immediateRequestTiempoActual. Uri: " + uri);

        myjsonObjectRequest = new MyJsonRequest(//Prioridad
                Request.Method.GET,
                uri,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response2) {

                        //Variables los datos del pronóstico de los próximos 5 días:
                        String nombre = "";
                        Double longitud=null;
                        Double latitud=null;
                        Double temperatura=null;
                        int presion=0;
                        int humedad=0;
                        Double temp_min=null;
                        Double temp_max=null;
                        Double speed=null;
                        int clouds=0;
                        String icon="";
                        String description="";


                        try {

                            for (int i = 0; i < response2.length(); i++) {

                                //RECOJEMOS DATOS EN VARIABLES:

                                //"weather"--EN ESTE CASO ES UN ARRAY DE OBJETOS:
                                JSONArray json_array = response2.getJSONArray("weather");
                                //String description2="";
                                //Log.v(LOGTAG, "Respuesta en JSON- valor descripción antes" + description);
                                for (int z = 0; z < json_array.length(); z++) {
                                    icon = json_array.getJSONObject(z).getString("icon");
                                    //id = json_array.getJSONObject(z).getString("id");
                                    //main2 = json_array.getJSONObject(z).getString("main");
                                    description = json_array.getJSONObject(z).getString("description");

                                    //Pruebas:
                                    //description2=json_array.getJSONObject(1).getString("description");
                                    //Log.v(LOGTAG, "Respuesta en JSON- valor descripción después"+description);
                                    //Log.v(LOGTAG, "Respuesta en JSON- valor descripción2 después"+description2);

                                }



                                //coord
                                JSONObject coord = response2.getJSONObject("coord");
                                longitud = coord.getDouble("lon");
                                latitud = coord.getDouble("lat");

                                //main
                                JSONObject main = response2.getJSONObject("main");
                                temperatura = main.getDouble("temp");
                                presion = main.getInt("pressure");
                                humedad = main.getInt("humidity");
                                temp_min = main.getDouble("temp_min");
                                temp_max = main.getDouble("temp_max");

                                //wind
                                JSONObject wind = response2.getJSONObject("wind");
                                speed = wind.getDouble("speed");
                                //deg=wind.getDouble("deg");

                                //Nubes clouds
                                JSONObject nubes = response2.getJSONObject("clouds");
                                clouds = nubes.getInt("all");


//                                JSONObject nombrefinal = response2.getJSONObject("name");
//                                nombre = nombre.ge;
                                nombre = response2.getString("name");


                            }


                            int velocidad = (int) (speed * 3.6);
                            //PINTAMOS LOS DATOS EN EL LAYOUT:
                            txtcoordenadas.setText(String.format("Coordenadas: Longitud: %s -Latitud: %s", longitud.toString(), latitud.toString()));
                            txtclima.setText(String.format("Temperatura : %sºC  Mín: %sºC  Máx: %sºC", temperatura.toString(), temp_min.toString(), temp_max.toString()));
                            txtnubes.setText(String.format("Nubosidad: %d%%", clouds));
                            txtbase.setText(String.format("Presión: %dmbar Humedad: %d%%", presion, humedad));
                            txtwind.setText(String.format("Datos del viento: Velocidad: %s km/h ", velocidad));

                            txtinformacion.setText(String.format("Resultados obtenidos sobre la climatología de %s", txtciudad.getText()));

                            txtestation.setText(String.format("Estación meteorológica de %s", nombre));

                            //txtrespuesta.setText("Descripción del tiempo: "+" "+fin.toString());
                            txtrespuesta.setText(String.format("Descripción del tiempo:  %s", description));
                            //txtrespuesta.setText(String.format("Descripción del tiempo:  %s", description));
                            //btnResultado.setVisibility(View.VISIBLE);
                            btnResultado.setEnabled(true);
                            // uriprueba="http://openweathermap.org/img/w/"+icon+""+png+"&appid=ffff21faa9754c531c28bad3ddc19605";
                            //png = ".png";

                            //REALIZAMOS LA SEGUNDA PETICIÓN. PRIORITY NORMAL.
                            //lowRequest();
                            //immediateRequestPronosticos();

                            icono.setImageResource(dameicono(icon));
                            icono.setVisibility(View.VISIBLE);
                            //uriprueba = String.format("http://openweathermap.org/img/w/%s%s", icon, png);

                            /*icono.setVisibility(View.VISIBLE);
                            uriprueba = String.format("http://openweathermap.org/img/w/%s%s", icon, png);

                            Log.v(LOGTAG, "JSON uriprueba: " + uriprueba);*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(LOGTAG, "Error Respuesta en JSON: " + description);
                        }

                        //priority = Request.Priority.IMMEDIATE;

                    }//fin onresponse

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOGTAG, "Error Respuesta en JSON: " + error.getMessage());
                        //txtrespuesta.setText(error.toString());
                        txtinformacion.setText(R.string.conexionerror);

                        Snackbar snack = Snackbar.make(txtinformacion, R.string.conexionerror, Snackbar.LENGTH_LONG);
                        ViewGroup group = (ViewGroup) snack.getView();
                        group.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        snack.show();

                    }
                }


        ) ;


        // Añadir petición a la cola
        //requestQueue.add(myjsonObjectRequest);
        //requestQueue.add(request);
        // Añadimos la petición a la cola de peticiones
        AppController.getInstance().addToRequestQueue(myjsonObjectRequest, tag_json_obj_actual);
        //AppController.getInstance().getRequestQueue().getCache().invalidate(Uri, true);
    }



    private void cargarImagenToolbar() {
 /*PARA QUE FUNCIONE CORRECTAMENTE HEMOS TENIDO QUE MODIFICAR LAS CLASES ORIGINALES DE VOLLEY QUE OBTIENEN LAS IMÁGENES Y LOS JSONOBJECT PARA PODER ESTABLECER UNA PRIORITY
 * DISTINTA A LA QUE VIENE POR DEFECTO. ADEMÁS ESTA SEGUNDA PETICIÓN SE REALIZA EN LA RESPUESTA DE LA PRIMERA PORQUE NECESITA DATOS DE ÉSTA.*/
 /*UTILIZANDO ESTA PETICIÓN SE OBTIENE EL ICONO CUYO CÓDIGO HEMOS OBTENIDO PREVIAMENTE EN LA RESPUESTA DE LA PRIMERA PETICIÓN*/
        /*En este situación usaremos el tipo ImageRequest para obtener cada imagen. Solo necesitamos concatenar
        la url absoluta que fue declarada como atributo, más la dirección relativa que cada imagen trae consigo
        en el objeto JSON*/

        // Etiqueta utilizada para cancelar la petición
        String tag_json_obj_img2 = "json_obj_req_img2";
        //http://openweathermap.org/img/w/10d.png&appid=ffff21faa9754c531c28bad3ddc19605
        //String patronIcono = "http://openweathermap.org/img/w/%s%s";
        //String uriIcono = String.format(patronIcono, icon, png);

        String url="http://wiki.openstreetmap.org/w/images/5/50/Leaflet-OpenWeatherMap.png";

        //String uriprueba="http://openweathermap.org/img/w/"+icon+""+png+"&appid=ffff21faa9754c531c28bad3ddc19605";

        //String uriprueba="http://openweathermap.org/img/w/04d.png&appid=ffff21faa9754c531c28bad3ddc19605";
        //String uriprueba="http://openweathermap.org/img/w/01n.png";
      /*  Log.v(LOGTAG, "Respuesta en JSON uriprueba: " + uriprueba);
        Log.v(LOGTAG, "Respuesta en JSON icono: " + icon);
        Log.v(LOGTAG, "Respuesta en JSON png: " + png);
        Log.v(LOGTAG, "Respuesta en JSON uriIcono: " + uriIcono);*/
        //urlIcono="http://openweathermap.org/img/w/10d.png";


        // requestQueue = Volley.newRequestQueue(this);

        //priority = Request.Priority.LOW;
        ImageRequest request = new MyImageRequest(

                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        //icono.setImageBitmap(bitmap);
                        imgToolbar.setImageBitmap(bitmap);
                        //Log.v(LOGTAG, "Respuesta en JSON imgToolbar: " + icon);
                    }
                },
                0, 0, null, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        icono.setImageResource(R.drawable.sol);//Si de error ponemos un sol...
                        Log.d(LOGTAG, "Error en respuesta Bitmap imgToolbar: " + error.getMessage());
                    }
                }
        );

        // Añadir petición a la cola
        //requestQueue.add(jsArrayRequest);
        //requestQueue.add(myjsonObjectRequest);

        //requestQueue.add(request);

        AppController.getInstance().addToRequestQueue(request, tag_json_obj_img2);

        //AppController.getInstance().getRequestQueue().getCache().remove(uriIcono);//Eliminar cache

        //AppController.getInstance().getRequestQueue().getCache().invalidate(uriIcono, true);//Desactivar cache



    }




    public void borraDatos(){

        txtcoordenadas.setText("");
        txtciudad.setText("");
        txtrespuesta.setText("");
        txtinformacion.setText("");
        txtwind.setText("");
        txtclima.setText("");
        txtbase.setText("");
        txtnubes.setText("");
        icono.setVisibility(View.INVISIBLE);
        txtestation.setText("");
        //imgToolbar.setImageResource(R.drawable.alberta);
        imgclima.setVisibility(View.VISIBLE);
        listaUI.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onBackPressed() {
/**
 * Cierra la app cuando se ha pulsado dos veces seguidas en un intervalo inferior a dos segundos.
 */

        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(getBaseContext(), R.string.eltiempo_salir, Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
        // super.onBackPressed();
    }


    private  int dameicono(String icon){


        if(icon.equals("01d")){
            //icono.setImageResource(R.drawable.sol);
            return R.drawable.sol;
        }else if (icon.equals("02d")){
            //icono.setImageResource(R.drawable.claros);
            return R.drawable.claros;
        }else if (icon.equals("03d")){
            //icono.setImageResource(R.drawable.nubes);
            return R.drawable.nubes;
        }else if (icon.equals("04d")){
            //icono.setImageResource(R.drawable.masnubes);
            return R.drawable.masnubes;
        }else if (icon.equals("09d")){
            //icono.setImageResource(R.drawable.nubeslluvia);
            return R.drawable.nubeslluvia;
        }else if (icon.equals("10d")){
            //icono.setImageResource(R.drawable.lluviasol);
            return R.drawable.lluviasol;
        }else if (icon.equals("11d")){
            //icono.setImageResource(R.drawable.maslluvia);
            return R.drawable.maslluvia;
        }else if (icon.equals("13d")){
            //icono.setImageResource(R.drawable.nieve);
            return R.drawable.nieve;
        }else if (icon.equals("50d")){
            //icono.setImageResource(R.drawable.niebla);
            return R.drawable.niebla;
        }else if (icon.equals("01n")){
            //icono.setImageResource(R.drawable.nocheclara);
            return R.drawable.nocheclara;
        }else if (icon.equals("02n")){
            //icono.setImageResource(R.drawable.nochenubes);
            return R.drawable.nochenubes;
        }else if (icon.equals("03n")){
            //icono.setImageResource(R.drawable.nubes);
            return R.drawable.nubes;
        }else if (icon.equals("04n")){
            //icono.setImageResource(R.drawable.masnubes);
            return R.drawable.masnubes;
        }else if (icon.equals("10n")){
            //icono.setImageResource(R.drawable.nubeslluvia);
            return R.drawable.nubeslluvia;
        }else if (icon.equals("11n")){
            //icono.setImageResource(R.drawable.maslluvia);
            return R.drawable.maslluvia;
        }else if (icon.equals("13n")){
            //icono.setImageResource(R.drawable.nieve);
            return R.drawable.nieve;
        }else if (icon.equals("50n")){
            //icono.setImageResource(R.drawable.niebla);
            return R.drawable.niebla;
        }
        else{
            //icono.setImageResource(R.drawable.sol);
            return R.drawable.error;
        }


    }


}
