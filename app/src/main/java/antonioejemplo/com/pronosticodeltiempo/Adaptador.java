package antonioejemplo.com.pronosticodeltiempo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Adaptador para el recyclerview.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.PronosticosViewHolder> {

    private final Context contexto;
    private List<Modelo> items;//Collection de Modelo. Los datos nos llegan desde el Main en esta Collection List()
    private Modelo modelo;
    private static final String LOGTAG = "AdapatadorVolley";//Constante para gestionar la escritura en el Log

    //Conexion a la API
    //private RequestQueue requestQueueAdaptador;//Cola de peticiones de Volley.
    //private JsonObjectRequest myjsonObjectRequestAdaptador;//Tipo de petición Volley utilizada...


    //Constructor de la clase Adaptador
    public Adaptador(List<Modelo> datos,Context contexto) {
        this.contexto = contexto;
        this.items=datos;

        //LLAMADA A LA API MEDIANTE VOLLEY. Ahora se hace desde el Main.
       // immediateRequestAdaptador();

    }

    public Adaptador(Context contexto) {
        this.contexto = contexto;
    }

    private String  dameFecha(String fecharecibida){

        String dia = null;
        String mes=null;


        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);//Formato exacto para que traiga bien hh:mm:ss----yyyy-MM-dd HH:mm:ss
        //String strFecha = "2016-04-12 00:00:00";
        String strFecha = fecharecibida;
        Date fecha = null;
        try {

            fecha = formatoDelTexto.parse(strFecha.toString());

        } catch (ParseException ex) {

            ex.printStackTrace();

        }

        Log.v(LOGTAG,"Fecha en el adaptador:"+fecharecibida+" "+fecha);

        //DIA de la semana--"2016-04-07 00:00:00"
        if(fecha.toString().substring(0,3).equals("Mon")){
            dia="Lunes";
            System.out.println(dia);
        }else if(fecha.toString().substring(0,3).equals("Tue")){
            dia="Martes";
            System.out.println(dia);
        }else if(fecha.toString().substring(0,3).equals("Wed")){
            dia="Miércoles";
            System.out.println(dia);
        }else if(fecha.toString().substring(0,3).equals("Thu")){
            dia="Jueves";
            System.out.println(dia);
        }else if(fecha.toString().substring(0,3).equals("Fri")){
            dia="Viernes";
            System.out.println(dia);
        }else if(fecha.toString().substring(0,3).equals("Sat")){
            dia="Sábado";
            System.out.println(dia);
        }else if(fecha.toString().substring(0,3).equals("Sun")){
            dia="Domingo";
            System.out.println(dia);
        }

        //DIA MES
        String diames=fecha.toString().substring(8,10);
        //System.out.println(diames);

        //MES--Thu Apr 07 00:00:00
        if(fecha.toString().substring(4,7).equals("Jan")){
            mes="Enero";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Feb")){
            mes="Febrero";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Mar")){
            mes="Marzo";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Apr")){
            mes="Abril";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("May")){
            mes="Mayo";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Jun")){
            mes="Junio";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Jul")){
            mes="Julio";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Aug")){
            mes="Agosto";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Sep")){
            mes="Septiembre";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Oct")){
            mes="Octubre";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Nov")){
            mes="Noviembre";
            System.out.println(mes);
        }else if(fecha.toString().substring(4,7).equals("Dec")){
            mes="Diciembre";
            System.out.println(mes);
        }

        //ANIO
        String anio=fecha.toString().substring(24,28);
        //System.out.println(anio);
        //HORA
        String hora=fecha.toString().substring(11,16);
        //System.out.println(hora);


        //String fechafinal=dia+"\n"+diames+"/"+mes+"/"+anio+ " "+hora;
        //System.out.println(fechafinal);
        String fechafuera=dia+"\n"+diames+" de "+mes+"\n"+hora+" horas";

        return fechafuera;

    }

    private  int dameicono(String icon){


        switch (icon) {
            case "01d":
                //icono.setImageResource(R.drawable.sol);
                return R.drawable.sol;
            case "02d":
                //icono.setImageResource(R.drawable.claros);
                return R.drawable.claros;
            case "03d":
                //icono.setImageResource(R.drawable.nubes);
                return R.drawable.nubes;
            case "04d":
                //icono.setImageResource(R.drawable.masnubes);
                return R.drawable.masnubes;
            case "09d":
                //icono.setImageResource(R.drawable.nubeslluvia);
                return R.drawable.nubeslluvia;
            case "10d":
                //icono.setImageResource(R.drawable.lluviasol);
                return R.drawable.lluviasol;
            case "11d":
                //icono.setImageResource(R.drawable.maslluvia);
                return R.drawable.maslluvia;
            case "13d":
                //icono.setImageResource(R.drawable.nieve);
                return R.drawable.nieve;
            case "50d":
                //icono.setImageResource(R.drawable.niebla);
                return R.drawable.niebla;
            case "01n":
                //icono.setImageResource(R.drawable.nocheclara);
                return R.drawable.nocheclara;
            case "02n":
                //icono.setImageResource(R.drawable.nochenubes);
                return R.drawable.nochenubes;
            case "03n":
                //icono.setImageResource(R.drawable.nubes);
                return R.drawable.nubes;
            case "04n":
                //icono.setImageResource(R.drawable.masnubes);
                return R.drawable.masnubes;
            case "10n":
                //icono.setImageResource(R.drawable.nubeslluvia);
                return R.drawable.nubeslluvia;
            case "11n":
                //icono.setImageResource(R.drawable.maslluvia);
                return R.drawable.maslluvia;
            case "13n":
                //icono.setImageResource(R.drawable.nieve);
                return R.drawable.nieve;
            case "50n":
                //icono.setImageResource(R.drawable.niebla);
                return R.drawable.niebla;
            default:
                //icono.setImageResource(R.drawable.sol);
                return R.drawable.error;
        }


    }


    /**
     * Se llama cuando RecyclerView necesita un nuevo enlace @ {} ViewHolder del tipo dado para representar
     * Un elemento.
     * <P>

     * Esta nueva ViewHolder deberá estar construido con una nueva vista que pueden representar a los artículos
     * Del tipo dado. Puede crear una nueva vista de forma manual o inflarlo a partir de un XML
     * Archivo de diseño.
     * <P>
     * El nuevo ViewHolder se utilizará para mostrar los elementos del adaptador utilizando
     * {#onBindViewHolder @link (ViewHolder, int, Lista)}. Ya que será re-utilizado para mostrar
     * Diferentes elementos en el conjunto de datos, es una buena idea para almacenar en caché las referencias a sub vistas
     * Ver para evitar la innecesaria {@ link Ver # findViewById (int)} llama.
     *
     * @ Param matriz La ViewGroup en la que se añadirá la nueva vista después de que está obligado a
     * Una posición adaptador.
     * @param ViewType El tipo de vista de la nueva vista.
     * @return Un nuevo ViewHolder que tiene una visión del tipo de vista determinado.
     * @see #getItemViewType (Int)
     * @see #onBindViewHolder (ViewHolder, int)
     */
    @Override
    public PronosticosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);


        PronosticosViewHolder tvh = new PronosticosViewHolder(itemView);

        return tvh;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(PronosticosViewHolder holder, int position) {

        modelo=items.get(position);


        int temperatura=  (int)items.get(position).getTemperatura().doubleValue();

        //int temp=(int) temperatura.doubleValue();

        //Llamamos a dameFecha pasándole items.get(position).getDia() para que la formatee
        holder.txtDia.setText(dameFecha(items.get(position).getDia()));

        holder.txtPronostico.setText(items.get(position).getPronostico());//Pronóstico
        //holder.txtTemperatura.setText(String.format("%sºC", items.get(position).getTemperatura().toString()));//Temperatura
        holder.txtTemperatura.setText(String.format("%sºC", temperatura));//Temperatura
        //holder.imgicono.setImageResource(R.drawable.sol);
        //holder.imgicono.setImageResource(dameicono(items.get(position).getImagen()));
        holder.imgicono.setImageResource(new Util().dameicono(items.get(position).getImagen()));

        //dameicono(items.get(position).getImagen());

    }

    /**
     * Llamado por RecyclerView para visualizar los datos en la posición especificada. Este método debe
     * Actualizar el contenido de la {@ link ViewHolder # itemView} para reflejar el tema en la propuesta
     * posición.
     * <P>
     * Tenga en cuenta que a diferencia de {ListView @link}, RecyclerView no llamará a este método
     * Otra vez si la posición del elemento cambia en el conjunto de datos a menos que el artículo en sí mismo es
     * Invalidada o la nueva posición no se puede determinar. Por esta razón, sólo deben
     * Utilizar el <code> Posición </ code> mientras que la adquisición del elemento de datos relacionados con el interior
     * Este método y no debe guardar una copia de la misma. Si necesita la posición de un elemento más tarde
     * En (por ejemplo en un detector de clics), utilice {@ link ViewHolder # getAdapterPosition ()} y esto
     * Tiene la posición del adaptador actualizado.
     * <P>
     * Anulación {#onBindViewHolder @link (ViewHolder, int, Lista)} en su lugar si adaptador puede
     * Manejar unen parcial effcient.
     *
     * Titular @param El ViewHolder que debe ser actualizado para representar el contenido de la
     * Elemento en la posición dada en el conjunto de datos.
     * Posición @param La posición del elemento dentro del conjunto de datos del adaptador.
     */


    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return  0;
    }

    //Para implementar el patrón viewHolder...
    public static class PronosticosViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDia;
        private TextView txtPronostico;
        private TextView txtTemperatura;
        private ImageView imgicono;

        public PronosticosViewHolder(View itemView) {
            super(itemView);

            txtDia = (TextView)itemView.findViewById(R.id.txtdia);
            txtPronostico = (TextView)itemView.findViewById(R.id.txtpronostico);
            txtTemperatura = (TextView)itemView.findViewById(R.id.txttemperatura);
            imgicono = (ImageView)itemView.findViewById(R.id.icono);
        }

       /* public void bindPronosticos(items modelo) {
            txtTitulo.setText(modelo.getTitulo());
            txtSubtitulo.setText(t.getSubtitulo());
            imgicono.setImageResource(t.getImage);

        }*/
    }
}
