package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Exercici2 {
    public static void main(String[] args) {
        // Definim l'aparcament amb una capacitat de 3 cotxes
        ParkingLot parkingLot = new ParkingLot(3);
        ExecutorService executor = Executors.newFixedThreadPool(10); 

        // Simulem 10 cotxes
        for (int i = 1; i <= 10; i++) {
            final int carId = i;
            executor.submit(() -> {
                try {
                    parkingLot.enterParking(carId);
                    // Posem un sleep per simular el temps que el cotxe esta aparcat
                    TimeUnit.SECONDS.sleep(2);
                    parkingLot.leaveParking(carId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("El cotxe " + carId + " ha estat interromput.");
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

// Fem el semáfor
class ParkingLot {
    private final Semaphore semaphore;

    public ParkingLot(int capacity) {
        this.semaphore = new Semaphore(capacity); 
    }

    // Mètode per simular l'entrada d'un cotxe
    public void enterParking(int carId) throws InterruptedException {
        System.out.println("El cotxe " + carId + " està intentant entrar a l'aparcament.");
        semaphore.acquire(); 
        System.out.println("El cotxe " + carId + " ha entrat a l'aparcament.");
    }

    // Mètode per simular la sortida d'un cotxe
    public void leaveParking(int carId) {
        System.out.println("El cotxe " + carId + " ha sortit de l'aparcament.");
        semaphore.release(); 
    }
}
