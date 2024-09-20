package com.procesos;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercici1 {

    private static ConcurrentHashMap<String, Double> accountData = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable initTask = () -> {
            accountData.put("balance", 1000.0); 
            System.out.println("Dades inicials introduïdes: " + accountData);
        };

        Runnable modifyTask = () -> {
            Double currentBalance = accountData.get("balance");
            if (currentBalance != null) {
                Double interest = currentBalance * 0.05; 
                accountData.put("balance", currentBalance + interest);
                System.out.println("Interès aplicat: " + interest + ", nou saldo: " + accountData.get("balance"));
            }
        };

        Callable<Double> resultTask = () -> {
            return accountData.get("balance");
        };

        executor.execute(initTask);
        executor.execute(modifyTask);
        
        try {
            Thread.sleep(100); 
            Future<Double> resultFuture = executor.submit(resultTask);
            Double finalBalance = resultFuture.get(); 
            System.out.println("Saldo actualitzat final: " + finalBalance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); 
        }
    }
}
