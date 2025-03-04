package com.example.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class DesplazarActividades {

    public static void actividades(String body) {
        JSONArray jsonArray = new JSONArray(body);
        jsonArray.forEach(element -> {
            JSONObject event = (JSONObject) element;
            String type = event.getString("type");
            String action = null;
            switch (type) {
                case "PushEvent":
                    int commitCount = event.getJSONObject("payload").getJSONArray("commits").length();
                    action = "Pushed " + commitCount + " commit(s) to " + event.getJSONObject("repo").getString("name");
                    break;
                case "IssuesEvent":
                    String issueAction = event.getJSONObject("payload").getString("action");
                    action = issueAction.toUpperCase().charAt(0) + issueAction.substring(1) + " an issue in "
                            + event.getJSONObject("repo").getString("name");
                    break;
                case "WatchEvent":
                    action = "Starred " + event.getJSONObject("repo").getString("name");
                    break;
                case "ForkEvent":
                    action = "Forked " + event.getJSONObject("repo").getString("name");
                    break;
                case "CreateEvent":
                    action = "Created " + event.getJSONObject("payload").getString("ref_type") + " in "
                            + event.getJSONObject("repo").getString("name");
                    break;
                default:
                    action = type.replace("Event", "") + " in " + event.getJSONObject("repo").getString("name");
                    break;
            }
            System.out.println(action);
        });
    }
}
