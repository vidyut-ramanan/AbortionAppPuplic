package com.vidyut.abortionapp;

public class UrlConstructor {


    private final String state;
    private final Info info;

    public UrlConstructor(String state, Info info) {
        this.state = state;
        this.info = info;
    }

    public String getURLString() {
        String answer = "https://api.abortionpolicyapi.com/v1/";
        switch (info) {
            case Gestation:
                answer += "gestational_limits/states/";
                break;
            case Insurance:
                answer += "insurance_coverage/states/";
                break;
            case Minors:
                answer += "minors/states/";
                break;
            case Waiting:
                answer += "waiting_periods/states/";
                break;
        }

        StringBuilder newState = new StringBuilder();

        for (char c : state.toCharArray()) {
            if (c == ' ') {
                newState.append("%20");
            } else {
                newState.append(c);
            }
        }

        answer+= newState;

        return answer;
    }

    public enum Info {
        Gestation,
        Insurance,
        Minors,
        Waiting;
    }
}
