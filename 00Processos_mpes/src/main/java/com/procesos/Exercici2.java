package com.procesos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici2 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<Integer> validationFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Validant dades...");
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10; 
        }, executor);

        CompletableFuture<Integer> processingFuture = validationFuture.thenApplyAsync(validatedData -> {
            System.out.println("Processant dades...");
            return validatedData * 2; 
        }, executor);

        processingFuture.thenAccept(finalResult -> {
            System.out.println("Resultat final: " + finalResult);
        });

        processingFuture.join();
        
        executor.shutdown();  
    }
}
