package com.vidyut.abortionapp.json.interpreter;

import org.json.JSONException;
import org.json.JSONObject;

public class MinorsInfo extends AbstractInfo {

    public String below_age;

    public String parental_consent_required;

    public String parental_notification_required;

    public String parents_required;

    public String judicial_bypass_available;

    public String allows_minor_to_consent;

    public String last_updated;

    public StringBuilder text;

    public MinorsInfo(String jsonArrayString, String state) {

        JSONObject jsonObject = null;
        text = new StringBuilder();
        try {
            jsonObject = new JSONObject(jsonArrayString).getJSONObject(state);
            below_age = getOrNull(jsonObject, "below_age");
            parental_consent_required = getOrNull(jsonObject, "parental_consent_required");
            parental_notification_required = getOrNull(jsonObject, "parental_notification_required");
            parents_required = getOrNull(jsonObject, "parents_required");
            judicial_bypass_available = getOrNull(jsonObject, "judicial_bypass_available");
            allows_minor_to_consent = getOrNull(jsonObject, "allows_minor_to_consent_to_abortion");
            last_updated = getOrNull(jsonObject, "Last Updated");

            if (below_age != null) {
                text.append("Minor restrictions apply for below age ").append(this.below_age).append(".\n");
            } else {
                text.append("There are no restrictions on minors' abortions currently being enforced.\n");
            }

            if (parental_consent_required != null) {
                text.append("Parental consent is required (a parent or parents must give permission for the minor to have an abortion).\n");
            }

            if (parental_notification_required != null) {
                text.append("Parental notification is required (a parent or parents must be told beforehand of the minor's decision to have an abortion).\n");
            }

            if (parents_required != null) {
                if (Integer.parseInt(parents_required) == 1) {
                    text.append("One parent must be notified.\n");
                } else if (Integer.parseInt(parents_required) == 2) {
                    text.append("Both parents must be notified.\n");
                }

            }
            if (judicial_bypass_available != null) {
                text.append("A judge can excuse a minor from the required parental consent and/or notification. \n");
            }
            if (allows_minor_to_consent != null) {
                text.append("A minor can consent to an abortion.\n");
            }
            if (last_updated != null) {
                text.append("Last updated: ").append(getDate(last_updated)).append("\n");
            }

        } catch (JSONException e) {
            text.append("No information. \n");
        }


    }
}
