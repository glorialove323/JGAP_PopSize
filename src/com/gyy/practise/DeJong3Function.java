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
@SuppressWarnings("serial")
public class DeJong3Function extends FitnessFunction{
    @Override
    protected double evaluate(IChromosome a_chromosome) {
        // TODO Auto-generated method stub

        int numOfGens = a_chromosome.size();
        if (numOfGens != 5) {
            throw new IllegalArgumentException("Chromosome for " + "MaximizingFunction must have " + "exactly 5 genes.");
        }

        Double x1Val = (Double) a_chromosome.getGene(0).getAllele();
        Double x2Val = (Double) a_chromosome.getGene(1).getAllele();
        Double x3Val = (Double) a_chromosome.getGene(2).getAllele();
        Double x4Val = (Double) a_chromosome.getGene(1).getAllele();
        Double x5Val = (Double) a_chromosome.getGene(2).getAllele();

        Double res = (double) 0;
        //Math.floor（double a）函数表示向下取整，Math.ceil(double a)表示向上取整
        res = (Double) res + Math.floor(x1Val) + Math.floor(x2Val) + Math.floor(x3Val) + Math.floor(x4Val)
                + Math.floor(x5Val);

        return -res;
    }
}
