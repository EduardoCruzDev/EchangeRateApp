package com.eduardocruzdev;

import com.eduardocruzdev.Service.ExchangeService;

import java.util.Scanner;

public class Main {

    static int opcion = -1;
    static Scanner currenciesToSearch = new Scanner(System.in);

    public static void main(String[] args) {
        while (opcion != 8) {
            menu();
            opciones();
        }
        currenciesToSearch.close();
    }

    static void menu() {
        System.out.println("""
                **************************************************
                Sea bienvenido/a al Conversor de moneda =]
                
                1.- Dolar =>> Peso Argentino
                2.- Peso Argentino =>> Dolar
                3.- Dolar =>> Real Brasileño
                4.- Real Brasileño =>> Dolar
                5.- Dolar =>> Peso Colombiano
                6.- Peso Colombiano =>> Dolar
                7.- Elegir un par de monedas personalizado
                8.- Salir
                Elija una opción válida:
                **************************************************
                """);

        try {
            opcion = Integer.parseInt(currenciesToSearch.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
            opcion = -1;
        }
    }

    static void opciones() {
        switch (opcion) {
            case 1 -> handleConversion("USD", "ARS");
            case 2 -> handleConversion("ARS", "USD");
            case 3 -> handleConversion("USD", "BRL");
            case 4 -> handleConversion("BRL", "USD");
            case 5 -> handleConversion("USD", "COP");
            case 6 -> handleConversion("COP", "USD");
            case 7 -> customConversion();
            case 8 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    static void handleConversion(String fromCurrency, String toCurrency) {
        System.out.println("Ingresa el monto a convertir:");
        try {
            double amount = Double.parseDouble(currenciesToSearch.nextLine());
            double convertedAmount = ExchangeService.convertAmount(fromCurrency, toCurrency, amount);
            System.out.printf("%.2f %s es igual a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un monto válido.");
        }
    }

    static void customConversion() {
        System.out.println("Introduce la moneda 1:");
        String pairSearch1 = currenciesToSearch.nextLine().toUpperCase();
        System.out.println("Introduce la moneda 2:");
        String pairSearch2 = currenciesToSearch.nextLine().toUpperCase();
        handleConversion(pairSearch1, pairSearch2);
    }
}
