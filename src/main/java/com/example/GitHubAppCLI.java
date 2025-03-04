package com.example;

import com.example.api.IPeticionApi;
import com.example.api.PeticionApi;

public class GitHubAppCLI {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usar ---> java GitHubAppCLI <username>");
            return;
        } else {
            IPeticionApi api = new PeticionApi();
            api.conexionApi(args[0]);
        }
    }
}