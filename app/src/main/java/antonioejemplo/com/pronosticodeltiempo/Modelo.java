package antonioejemplo.com.pronosticodeltiempo;

/**
 * Created by Susana on 05/04/2016.
 */
public class Modelo {
    //Atributos de clase
    private String dia;
   // private  Double longitud;
   // private Double latitud;
    private Double temperatura;
    private int presion;
    private int humedad;
    private Double temp_min;
    private Double temp_max;
    private Double speed;
    private String pronostico;
    private String imagen;

    public Modelo() {
    }

    public Modelo(String dia, String pronostico) {
        this.dia = dia;
        this.pronostico = pronostico;
    }


    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }



    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }


}
