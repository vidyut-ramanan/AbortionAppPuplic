package com.vidyut.abortionapp.json.interpreter;

import org.json.JSONException;
import org.json.JSONObject;

public class WaitingInfo extends AbstractInfo {

    public String waiting_period_hours;

    public String counseling_visits;

    public String exception_health;

    public String waiting_period_notes;

    public String last_updated;

    public String counseling_waived_condition;

    public StringBuilder text;

    public WaitingInfo(String jsonArrayString, String state) {

        JSONObject jsonObject = null;
        text = new StringBuilder();
        try {
            jsonObject = new JSONObject(jsonArrayString).getJSONObject(state);
            waiting_period_hours = getOrNull(jsonObject, "waiting_period_hours");
            counseling_visits = getOrNull(jsonObject, "counseling_visits");
            exception_health = getOrNull(jsonObject, "exception_health");
            waiting_period_notes = getOrNull(jsonObject, "waiting_period_notes");
            last_updated = getOrNull(jsonObject, "Last Updated");
            counseling_waived_condition = getOrNull(jsonObject, "counseling_waived_condition");

            if (waiting_period_hours != null) {
                text.append("Must wait ").append(waiting_period_hours)
                        .append(" hours between receiving state mandated abortion counseling and obtaining an abortion.\n");
            } else {
                text.append("No waiting period is in effect. \n");
            }

            if (counseling_visits != null) {
                if (counseling_visits.equals("1")) {
                    text.append("Counseling required by the state. \n");

                } else if (counseling_visits.equals("2")) {
                    text.append("Abortion counseling or ultrasound must be obtained at the facility before the waiting period begins, requiring two trips to the clinic. \n");
                }
            }
            if (waiting_period_notes != null) {
                text.append(waiting_period_hours).append("\n");
            }

            if (counseling_waived_condition != null) {
                text.append("Counseling waived condition: ").append(counseling_waived_condition).append("\n");
            }

            if (last_updated != null) {
                text.append("Last updated: ").append(getDate(last_updated)).append("\n");
            }
        } catch (JSONException e) {
            text.append("No waiting period requirements");
        }


    }
}
