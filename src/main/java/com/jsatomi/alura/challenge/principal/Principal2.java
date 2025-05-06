package com.jsatomi.alura.challenge.principal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Principal2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcionUser = 0;
        float monedaUser;
        double rate = 0;
        Boolean run = true;
        DecimalFormat df = new DecimalFormat("#.##");
        // Setting URL
        String url_str = "https://v6.exchangerate-api.com/v6/7d2ffdebb6a1023188a89a6a/latest/USD";
        // Making Request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url_str))
                .build();

        //Con Response es la informacion que recibimos al servidor
        HttpResponse<String> response = client
                //.send requiere manejor de excepciones
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        // de JSON -> OBJETO
        JsonObject object = JsonParser.parseString(response.body()).getAsJsonObject();
        // OBJETO le estas pidiendo la informacion de la llave "conversion_rates"
        JsonObject rates = object.getAsJsonObject("conversion_rates");
        System.out.println("************************************************************************");
        System.out.println(rates);

        //Obtengo un solo valor de una key de conversion_rates
        rate = rates.get("MXN").getAsDouble();
        System.out.println("****************************");
        System.out.println(rate);

        System.out.println("Bienvenido al conversor de monedas!");

        while (run) {
            System.out.println("Ingrese la opcion a la que desea convertir!");
            System.out.println("1) Dolar -> Peso Mexicano" +
                    "\n2) Peso Mexicano -> Dolar" +
                    "\n3) Dolar -> Euro" +
                    "\n4) Euro -> Dolar" +
                    "\n5) Dolar -> Libra Esterlina" +
                    "\n6) Libra Esterlina -> Dolar" +
                    "\n7) Salir");

            opcionUser = scanner.nextInt();
            if(opcionUser == 7){
                break;
            }
            System.out.println("Ingrese la cantidad de monedas que desea convertir");
            monedaUser = scanner.nextFloat();

            switch (opcionUser) {

                case 1:
                    rate = rates.get("MXN").getAsDouble();
                    System.out.println("Tus dolares [USD] " + monedaUser + " son " + df.format(rate*monedaUser) + " [MXN]");
                    break;
                case 2:
                    rate = rates.get("MXN").getAsDouble();
                    System.out.println("Tus Pesos Mexicanos [MXN] " + monedaUser + " son " + df.format(monedaUser/rate) + " [USD]");
                    break;
                case 3:
                    rate = rates.get("EUR").getAsDouble();
                    System.out.println("Tus dolares [USD] " + monedaUser + " son " + df.format(monedaUser*rate) + " [EUR]");
                    break;
                case 4:
                    rate = rates.get("EUR").getAsDouble();
                    System.out.println("Tus Euros [EUR] " + monedaUser + " son " + df.format(monedaUser/rate) + " [USD]");
                    break;
                case 5:
                    rate = rates.get("GBP").getAsDouble();
                    System.out.println("Tus dolares [USD] " + monedaUser + " son " + df.format(monedaUser*rate) + " [GBP]");
                    break;
                case 6:
                    rate = rates.get("GBP").getAsDouble();
                    System.out.println("Tus Libras Esterlinas [GBP] " + monedaUser + " son " + df.format(monedaUser/rate) + " [USD]");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}
