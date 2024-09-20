package com.procesos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici0 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> {
            try {
                System.out.println("Iniciant tasca 1: Registrar esdeveniments de sistema.");
                Thread.sleep(2000);
                System.out.println("Tasca 1 completada: Esdeveniments de sistema registrats.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Tasca 1 interrompuda.");
            }
        };

        Runnable task2 = () -> {
            try {
                System.out.println("Iniciant tasca 2: Comprovar l'estat de la xarxa.");
                Thread.sleep(3000); 
                System.out.println("Tasca 2 completada: Estat de la xarxa comprovat.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Tasca 2 interrompuda.");
            }
        };

        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }
}
