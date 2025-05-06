package com.jsatomi.alura.challenge.principal;

import com.jsatomi.alura.challenge.models.ConvertidorMoneda;
import com.jsatomi.alura.challenge.service.ExchangeRateService;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static <JsonElement> void main(String[] args) {
        try {
        ExchangeRateService service = new ExchangeRateService();
        ConvertidorMoneda convertidorMoneda = new ConvertidorMoneda(service);
        Scanner scanner = new Scanner(System.in);

     //   boolean run = true;
        int opcionUser = 0;
        double monedaUser;

            System.out.println("Bienvenido al conversor de monedas!");
            while (true) {
                System.out.println("Ingrese la opcion a la que desea convertir!");
                System.out.println("1) Peso Mexicano -> Dolar" +
                        "\n2) Dolar -> Peso Mexicano" +
                        "\n3) Dolar -> Euro" +
                        "\n4) Euro -> Dolar" +
                        "\n5) Dolar -> Libra Esterlina" +
                        "\n6) Libra Esterlina -> Dolar" +
                        "\n7) Salir");

                opcionUser = scanner.nextInt();

                if (opcionUser == 7) {break;}

                System.out.println("Ingrese la cantidad de monedas que desea convertir");
                monedaUser = scanner.nextFloat();

                switch (opcionUser) {

                    case 1: //Peso Mexicano -> Dolar
                        System.out.println("Tus " + monedaUser + " MXN son " + convertidorMoneda
                                .convertir("MXN", "USD", monedaUser) + " USD");
                        break;
                    case 2: // Dolar -> Peso Mexicano
                        System.out.println("Tus " + monedaUser + " USD son " + convertidorMoneda
                                .convertir("USD", "MXN", monedaUser) + " MXN");
                        break;
                    case 3: // Dolar -> Euro
                        System.out.println("Tus " + monedaUser + " USD son " + convertidorMoneda
                                .convertir("USD", "EUR", monedaUser) + " EUR");
                        break;
                    case 4: //Euro -> Dolar
                        System.out.println("Tus " + monedaUser + " EUR son " + convertidorMoneda
                                .convertir("EUR", "USD", monedaUser) + " USD");
                        break;
                    case 5: //Dolar -> Libra Esterlina
                        System.out.println("Tus " + monedaUser + " USD son " + convertidorMoneda
                                .convertir("USD", "GBP", monedaUser) + " GBP");
                        break;
                    case 6: //Libra Esterlina -> Dolar
                        System.out.println("Tus " + monedaUser + " GBP son " + convertidorMoneda
                                .convertir("GBP", "USD", monedaUser) + " USD");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            }
        }catch (IOException | InterruptedException e){
            System.out.println("Ha surgido un error inesperado " + e.getMessage());
        }
    }
}
