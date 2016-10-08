/**
 * The simplest test function is De Jong's function 1. It is continuos, convex and unimodal.
 * function definition
 * f1(x)=sum(x(i)^2), i=1:n;
 * -5.12<=x(i)<=5.12.
 * global minimum
 * f(x)=0; x(i)=0, i=1:n.
 */
package com.gyy.practise;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 * @author Gloria
 * 
 */
public class DeJong1Function extends FitnessFunction {

    private static final long serialVersionUID = 5870528846902620828L;

    @Override
    protected double evaluate(IChromosome a_chromosome) {
        // TODO Auto-generated method stub
        
        int numOfGens = a_chromosome.size();
        if(numOfGens !=3){
           throw new IllegalArgumentException("Chromosome for "
					+ "MaximizingFunction must have " + "exactly 3 genes.");
        }
        Double x1Val = (Double) a_chromosome.getGene(0).getAllele();
        Double x2Val = (Double) a_chromosome.getGene(1).getAllele();
        Double x3Val = (Double) a_chromosome.getGene(2).getAllele();
        return -(x1Val*x1Val+x2Val*x2Val+x3Val*x3Val);
    }
}