package com.project;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici1 {

    public static void main(String[] args) {
        double[] dades = {1.5, 2.0, 3.5, 5.0, 7.5};

        // CyclicBarrier per esperar que tots els càlculs acabin
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Tots els càlculs han acabat.");
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Variables per guardar els resultats finals
        double[] suma = new double[1];
        double[] mitjana = new double[1];
        double[] desviacioEst = new double[1];

        // Executem les tasques
        executor.submit(new TascaSuma(barrier, dades, suma));
        executor.submit(new TascaMitjana(barrier, dades, mitjana));
        executor.submit(new TascaDesviacio(barrier, dades, mitjana, desviacioEst));

        // Tanquem l'executor i esperem que finalitzin
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Esperem que totes les tasques acabin
        }

        // Mostrem els resultats finals
        System.out.println("Suma: " + suma[0]);
        System.out.println("Mitjana: " + mitjana[0]);
        System.out.println("Desviació estàndard: " + desviacioEst[0]);
    }

    // Tasca per calcular la suma
    static class TascaSuma implements Runnable {
        private CyclicBarrier barrier;
        private double[] dades;
        private double[] suma;

        public TascaSuma(CyclicBarrier barrier, double[] dades, double[] suma) {
            this.barrier = barrier;
            this.dades = dades;
            this.suma = suma;
        }

        public void run() {
            suma[0] = Arrays.stream(dades).sum();
            System.out.println("Càlcul de la suma finalitzat.");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // Tasca per calcular la mitjana
    static class TascaMitjana implements Runnable {
        private CyclicBarrier barrier;
        private double[] dades;
        private double[] mitjana;

        public TascaMitjana(CyclicBarrier barrier, double[] dades, double[] mitjana) {
            this.barrier = barrier;
            this.dades = dades;
            this.mitjana = mitjana;
        }

        public void run() {
            mitjana[0] = Arrays.stream(dades).average().orElse(0.0);
            System.out.println("Càlcul de la mitjana finalitzat.");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // Tasca per calcular la desviació estàndard
    static class TascaDesviacio implements Runnable {
        private CyclicBarrier barrier;
        private double[] dades;
        private double[] mitjana;
        private double[] desviacioEst;

        public TascaDesviacio(CyclicBarrier barrier, double[] dades, double[] mitjana, double[] desviacioEst) {
            this.barrier = barrier;
            this.dades = dades;
            this.mitjana = mitjana;
            this.desviacioEst = desviacioEst;
        }

        public void run() {
            double sumQuadrats = Arrays.stream(dades)
                .map(x -> Math.pow(x - mitjana[0], 2))
                .sum();
            desviacioEst[0] = Math.sqrt(sumQuadrats / dades.length);
            System.out.println("Càlcul de la desviació estàndard finalitzat.");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
