package com.example.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.example.util.DesplazarActividades;

public class PeticionApi implements IPeticionApi {

    @Override
    public void conexionApi(String nombreUsuario) {
        String urlApiGitHub = "https://api.github.com/users/" + nombreUsuario + "/events";

        try {
            HttpClient clienteHttp = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                                            .uri(new URI(urlApiGitHub))
                                            .header("Accept", "application/vnd.github+json")
                                            .GET()
                                            .build();
            HttpResponse<String> response = clienteHttp.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case 200:
                    DesplazarActividades.actividades(response.body());
                    break;
                case 404:
                    System.out.println("Usuario no encontrado. Por favor verifique el nombre del usuerio | codigo de estado: "+response.statusCode());
                    break;
                default:
                    System.out.println("Error | codigo de estado: "+response.statusCode());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
