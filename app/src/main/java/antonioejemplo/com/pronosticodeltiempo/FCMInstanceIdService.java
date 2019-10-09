package antonioejemplo.com.pronosticodeltiempo;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMInstanceIdService extends FirebaseInstanceIdService {

    public FCMInstanceIdService() {
    }


    ///////////NUEVO/////////////////////
    @Override
    public void onTokenRefresh() {
        //Se llama cuando se registra que pasa algo con el servicio
        String token= FirebaseInstanceId.getInstance().getToken();
        //super.onTokenRefresh();


        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
            //Ahora podemos utilizar el token de la notificación....
        //POR EJEMPLO PODEMOS REGISTRAR EL TOKEN EN EL SERVIDOR....


    }


}
