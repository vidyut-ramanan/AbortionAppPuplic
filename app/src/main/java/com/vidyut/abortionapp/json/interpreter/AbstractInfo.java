package com.vidyut.abortionapp.json.interpreter;

import org.json.JSONException;
import org.json.JSONObject;

public class AbstractInfo {

    protected static String getOrNull(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            return null;
        }
    }

    protected static String getDate(String s) {

        return s.substring(0, s.lastIndexOf('T'));

    }

}
