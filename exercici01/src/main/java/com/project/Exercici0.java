package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercici0 {

    public static void main(String[] args) {
        int numMicroserveis = 3;
        
        // Creem un CyclicBarrier que s'activarà quan els 3 microserveis hagin acabat
        CyclicBarrier barrier = new CyclicBarrier(numMicroserveis, () -> {
            System.out.println("Totes les tasques han acabat.");
        });

        // Executem tasques de forma concurrent
        ExecutorService executor = Executors.newFixedThreadPool(numMicroserveis);

        // Resultats 
        Future<String> microservei1 = executor.submit(new Microservei(barrier, "Microservei 1", 2000));
        Future<String> microservei2 = executor.submit(new Microservei(barrier, "Microservei 2", 3000));
        Future<String> microservei3 = executor.submit(new Microservei(barrier, "Microservei 3", 1000));

        try {
            // Guardem els resultats i els combinem
            String resultatFinal = microservei1.get() + " + " + microservei2.get() + " + " + microservei3.get();
            System.out.println("Resultat final: " + resultatFinal);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Tanquem executor
            executor.shutdown();
        }
    }

    // Classe que simula un microservei
    static class Microservei implements Callable<String> {
        private CyclicBarrier barrier;
        private String nom;
        private int tempsProcessament;

        public Microservei(CyclicBarrier barrier, String nom, int tempsProcessament) {
            this.barrier = barrier;
            this.nom = nom;
            this.tempsProcessament = tempsProcessament;
        }

        public String call() throws Exception {
            System.out.println(nom + " està processant...");
            Thread.sleep(tempsProcessament);
            String resultat = nom + " ha acabat.";
            System.out.println(resultat);

            // Esperem que els altres microserveis acabin
            barrier.await();
            return resultat;
        }
    }
}
