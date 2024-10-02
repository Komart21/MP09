package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Exercici3 {

    public static void main(String[] args) {
        // Definim una pàgina web amb un límit de 3 connexions simultànies
        WebPage webPage = new WebPage(3);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Simulem 10 connexions d'usuaris
        for (int i = 1; i <= 10; i++) {
            final int userId = i;
            executor.submit(() -> {
                try {
                    webPage.connect(userId);
                    // Simulem el temps que l'usuari està connectat
                    TimeUnit.SECONDS.sleep(2);
                    webPage.disconnect(userId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("La connexió de l'usuari " + userId + " ha estat interrompuda.");
                }
            });
        }

        // Tanquem l'executor 
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Classe WebPage que gestiona el nombre de connexions simultànies amb un semàfor
class WebPage {
    private final Semaphore semaphore;

    public WebPage(int maxConnections) {
        this.semaphore = new Semaphore(maxConnections); 
    }

    // Mètode per simular una connexió a la pàgina
    public void connect(int userId) throws InterruptedException {
        System.out.println("L'usuari " + userId + " està intentant connectar-se.");
        semaphore.acquire(); 
        System.out.println("L'usuari " + userId + " s'ha connectat.");
    }

    // Mètode per simular la desconnexió d'un usuari
    public void disconnect(int userId) {
        System.out.println("L'usuari " + userId + " s'ha desconnectat.");
        semaphore.release(); 
    }
}
