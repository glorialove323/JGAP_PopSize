/**
 * 
 */
package com.gyy.practise;

import java.util.ArrayList;
import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 * @author Gloria
 *
 */
@SuppressWarnings("serial")
public class DeJong4Function extends FitnessFunction{

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected double evaluate(IChromosome a_chromosome) {
        // TODO Auto-generated method stub
        
        int numOfGens = a_chromosome.size();
        if(numOfGens !=30){
           throw new IllegalArgumentException("Chromosome for "
                    + "MaximizingFunction must have " + "exactly 30 genes.");
        }
        
        List xList = new ArrayList();
      
        for(int i = 0;i<30;i++){
           Double xVal = (Double) a_chromosome.getGene(i).getAllele();
           xList.add(xVal);
        }
        
        Double res = (double) 0;
        for(int j=0;j<30;j++){
            double[] d = new double[30];
            d[j] = (Double) xList.get(j);
            res = (Double) res+j*d[j]*d[j]*d[j]*d[j];
        }
      
        return -res;
    }

}
