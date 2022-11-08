package com.vidyut.abortionapp.json.interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GestationalInfo extends AbstractInfo {

    public String state;

    public String banned_after_weeks_since_LMP;

    public String exception_life;

    public String exception_health;

    public String exception_fetal;

    public String exception_rape_or_incest;

    public StringBuilder text;

    public String getBanned_after_weeks_since_LMP() {
        return banned_after_weeks_since_LMP;
    }

    public String getException_life() {
        return exception_life;
    }

    public String getException_health() {
        return exception_health;
    }

    public String getException_fetal() {
        return exception_fetal;
    }

    public String getException_rape_or_incest() {
        return exception_rape_or_incest;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public String last_updated;


    public GestationalInfo(String jsonArrayString, String state) {
        this.state = state;

        text = new StringBuilder();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonArrayString).getJSONObject(state);

            this.banned_after_weeks_since_LMP = getOrNull(jsonObject, "banned_after_weeks_since_LMP");

            this.exception_life = getOrNull(jsonObject, "exception_life");

            this.exception_health = getOrNull(jsonObject, "exception_health");

            this.exception_fetal = getOrNull(jsonObject, "exception_fetal");

            this.exception_rape_or_incest = getOrNull(jsonObject, "exception_rape_or_incest");

            this.last_updated = getOrNull(jsonObject, "Last Updated");

            if (banned_after_weeks_since_LMP == null) {

            } else if (banned_after_weeks_since_LMP.equals("99")) {
                text.append("Abortion is banned after viability. \n");
            } else if (banned_after_weeks_since_LMP.equals("28")) {
                text.append("Abortion is banned in third trimester. \n");
            } else if (banned_after_weeks_since_LMP.equals("0")) {
                text.append("Abortion is banned in ").append(state).append(".\n");
            } else {
                text.append("Abortion is banned ").append(banned_after_weeks_since_LMP)
                        .append(" weeks after last menstrual period. \n");
            }

            if (exception_life == null) {

            } else if (exception_life.equals("true")) {
                text.append("Abortion is not banned if necessary to save the pregnant person's life.\n");
            }

            if (exception_health == null) {

            } else if (exception_health.equals("Any")) {
                text.append("Abortion is not banned if necessary to preserve the pregnant person's health.\n");
            } else if (exception_health.equals("Major Bodily Function")) {
                text.append("Abortion is permitted in cases where a person " +
                        "would suffer substantial and irreversible impairment of a major bodily function. \n");
            } else if (exception_health.equals("Physical")) {
                text.append("Abortion is not banned if necessary to preserve the pregnant person's physical health.\n");
            }

            if (exception_fetal == null) {

            } else {
                text.append("Abortion is not banned in case of ").append(exception_fetal.toLowerCase()).append(".\n");

            }

            if(exception_rape_or_incest != null){
                text.append("Exceptions may be granted in cases of rape or incest \n.");

            }

            if(last_updated !=null){
                text.append("Last updated: ").append(getDate(last_updated)).append("\n");
            }


        } catch (JSONException e) {
            text.append("There are no gestational limits for abortions in ").append(state);
        }

    }


}
