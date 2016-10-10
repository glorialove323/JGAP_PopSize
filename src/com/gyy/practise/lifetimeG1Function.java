/**
 * 
 */
package com.gyy.practise;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 * @author Gloria
 *
 */
public class lifetimeG1Function extends FitnessFunction{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected double evaluate(IChromosome a_chromosome) {
        // TODO Auto-generated method stub
        int numOfGens = a_chromosome.size();
        if(numOfGens !=1){
           throw new IllegalArgumentException("Chromosome for "
                    + "MaximizingFunction must have " + "exactly 1 genes.");
        }
        Double x = (Double) a_chromosome.getGene(0).getAllele();
        Double y = -x*(Math.sin(10*(Math.PI)*x))+1;
        return y;
    }

}
