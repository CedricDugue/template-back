package br.com.trackertemplateback.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Validadores do sistema.
 *
 * @author Cedric Christian on 30/04/2021
 */
public final class Validator {

    /**
     * Valida se o objeto e JSONObject ou JSONArray.
     *
     * @param test Texto contendo suposto JSON
     * @return Se o parametro e JSON
     */
    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valida se o objeto e JSONObject.
     *
     * @param test Texto contendo suposto JSONObject
     * @return Se o parametro e JSONObject
     */
    public static boolean isJSONObjectValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    /**
     * Valida se o objeto e JSONArray.
     *
     * @param test Texto contendo suposto JSONArray
     * @return Se o parametro e JSONArray
     */
    public static boolean isJSONArrayValid(String test) {
        try {
            new JSONArray(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }
}
