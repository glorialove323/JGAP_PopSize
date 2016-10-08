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
public class DeJong5Function extends FitnessFunction{

    @Override
    protected double evaluate(IChromosome a_chromosome) {
        // TODO Auto-generated method stub
        
        int numOfGens = a_chromosome.size();
        if(numOfGens !=2){
           throw new IllegalArgumentException("Chromosome for "
                    + "MaximizingFunction must have " + "exactly 2 genes.");
        }
        Double x1Val = (Double) a_chromosome.getGene(0).getAllele();
        Double x2Val = (Double) a_chromosome.getGene(1).getAllele();  
        
        Integer st1 = 16;
        Integer st2 = -32;
        Integer a1 = -32;
        Integer a2 = a1;
        Integer incr = 0;
        
        double result = -0.002;
        
        for(int j=1;j<=25;j++){
            Double tt = (j+Math.pow((x1Val-a1), 6)+Math.pow((x2Val-a2), 6));
            tt = 1/tt;
            result = result+tt;
            a1 = a1+st1;
            if(a1 > 32){
                a1 = -32;
            }
            incr = j/5;
            a2 = st2+st1*incr;
        }      
        return result;
    }
}
