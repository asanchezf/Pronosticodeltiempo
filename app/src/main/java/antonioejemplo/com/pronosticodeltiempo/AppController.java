package antonioejemplo.com.pronosticodeltiempo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Susana on 02/04/2016.
 * La mejor forma de mantener los objetos de Volley y la cola de peticiones es hacerlos globales mediante
 * la creación de una clase que sigue el patrón de diseño singleton y que hereda del objeto Application
 * Para poder utilizar esta clase debemos añadirla en el Manifiest dentro de la etiqueta Application utilizando
 * la propiedad android;name para que se ejecute al inicio de la aplicación.
 */
public class AppController extends Application {
    public static final String TAG = AppController.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;


    @Override
    protected void attachBaseContext(Context base) {
    /*    MODIFICACIÓN POR MultiDex
        4/12/2017--https://stackoverflow.com/questions/42827205/app-crashed-wiht-classes-dex-permission-denied-error*/
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // asigna un valor a tag si tag está vacío
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
