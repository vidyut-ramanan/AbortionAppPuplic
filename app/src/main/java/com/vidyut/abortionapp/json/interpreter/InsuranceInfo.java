package com.vidyut.abortionapp.json.interpreter;

import org.json.JSONException;
import org.json.JSONObject;

public class InsuranceInfo extends AbstractInfo {

    public String requires_coverage;

    public String private_coverage_no_restrictions;

    public String private_exception_life;

    public String private_exception_health;

    public String private_exception_fetal;

    public String private_exception_rape_or_incest;

    public String exchange_coverage_no_restrictions;

    public String exchange_exception_life;

    public String exchange_exception_health;

    public String exchange_exception_fetal;

    public String exchange_exception_rape_or_incest;

    public String exchange_forbids_coverage;

    public String medicaid_coverage_provider_patient_decision;

    public String medicaid_exception_life;

    public String medicaid_exception_health;

    public String medicaid_exception_fetal;

    public String medicaid_exception_rape_or_incest;

    public String last_updated;

    public StringBuilder text;

    public String state;

    public InsuranceInfo(String jsonArrayString, String state) {

        this.state = state;

        this.text = new StringBuilder();


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonArrayString).getJSONObject(state);

            requires_coverage = getOrNull(jsonObject, "requires_coverage");
            private_coverage_no_restrictions = getOrNull(jsonObject, "private_coverage_no_restrictions");
            private_exception_life = getOrNull(jsonObject, "private_exception_life");
            private_exception_health = getOrNull(jsonObject, "private_exception_health");
            private_exception_fetal = getOrNull(jsonObject, "private_exception_fetal");
            private_exception_rape_or_incest = getOrNull(jsonObject, "private_exception_rape_or_incest");
            exchange_coverage_no_restrictions = getOrNull(jsonObject, "exchange_coverage_no_restrictions");
            exchange_exception_life = getOrNull(jsonObject, "exchange_exception_life");
            exchange_exception_health = getOrNull(jsonObject, "exchange_exception_health");
            exchange_exception_fetal = getOrNull(jsonObject, "exchange_exception_fetal");
            exchange_exception_rape_or_incest = getOrNull(jsonObject, "exchange_exception_rape_or_incest");
            exchange_forbids_coverage = getOrNull(jsonObject, "exchange_forbids_coverage");
            medicaid_coverage_provider_patient_decision = getOrNull(jsonObject, "medicaid_coverage_provider_patient_decision");
            medicaid_exception_life = getOrNull(jsonObject, "medicaid_exception_life");
            medicaid_exception_health = getOrNull(jsonObject, "medicaid_exception_health");
            medicaid_exception_fetal = getOrNull(jsonObject, "medicaid_exception_fetal");
            medicaid_exception_rape_or_incest = getOrNull(jsonObject, "medicaid_exception_rape_or_incest");
            last_updated = getOrNull(jsonObject, "Last Updated");


        } catch (JSONException e) {
            text.append("No information provided. \n");
        }


    }
}
