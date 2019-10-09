package antonioejemplo.com.pronosticodeltiempo;

/**
 * Created by Usuario on 12/01/2018.
 */

public class Util {

    public int dameicono(String icon) {


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
}
