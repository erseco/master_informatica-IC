package fblupi.evolutionaryalgorithm.qap;

import java.util.Random;

/**
 * Single individual in the evolutionary algorithm
 */
public class Individual {
    /**
     * QAP input matrices
     */
    private QAPMatrices matrices;

    /**
     * Fitness of current solution
     */
    private int fitness;

    /**
     * Current solution
     */
    private int[] solution;

    /**
     * Constructor. Copy input matrices and generate random solution
     * @param matrices input matrices
     */
    public Individual(QAPMatrices matrices) {
        this.matrices = matrices;
        fitness = 0;
        solution = new int[matrices.getSize()];
    }

    /**
     * Generate random solution
     */
    public void initialize() {
        generateRandomSolution();
    }

    /**
     * Calculate fitness of current solution
     */
    public void calculateFitness() {
        fitness = 0;
        for (int i = 0; i < matrices.getSize(); i++) {
            for (int j = 0; j < matrices.getSize(); j++) {
                fitness += matrices.getFlowMatrix()[i][j] * matrices.getDistanceMatrix()[solution[i]][solution[j]];
            }
        }
    }

    /**
     * Get fitness of current solution
     * @return fitness of current solution
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * Get current solution
     * @return current solution
     */
    public int[] getSolution() {
        return solution;
    }

    /**
     * Mutate a solution
     * @param pos1 a position to mutate
     * @param pos2 another position to mutate
     */
    public void mutate(int pos1, int pos2) {
        int swap = solution[pos1];
        solution[pos1] = solution[pos2];
        solution[pos2] = swap;
    }

    /**
     * Generate random solution
     */
    private void generateRandomSolution() {
        // Initialize ordered array
        for (int i = 0; i < solution.length; i++) {
            solution[i] = i;
        }
        // Shuffle it
        shuffleSolution();
    }

    /**
     * Shuffle solution
     */
    private void shuffleSolution() {
        Random r = new Random();
        for (int i = 0; i < solution.length; i++) {
            int swap = solution[i]; // value of gene i
            int pos = r.nextInt(matrices.getSize()); // position of gene to swap
            solution[i] = solution[pos];
            solution[pos] = swap;
        }
    }

}