package it.englab.androidcourse.justdrink.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Peppe on 07/02/2017.
 */

public class JsonUtil {
    private static Gson gson = new Gson();

    /**
     * Wrapper della libreria Gson
     * @param json Stringa json da parsare
     * @param tClass Class del generics T
     * @param <T> ..
     * @return Oggetto parsato
     */
    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json,tClass);
    }

    /**
     *
     * @param context Context per l'accesso ad assets
     * @param name Nome del file Json senza estensione
     * @return Stringa json
     */
    public static String loadJSONFromAsset(Context context, String name) {
        String json;
        try {
            InputStream is = context.getAssets().open(name+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
