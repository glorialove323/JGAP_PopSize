/**
 * 
 */
package com.gyy.lifetime;

import java.util.List;

import org.jgap.BaseGeneticOperator;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;

/**
 * @author Gloria
 * 
 */
@SuppressWarnings("rawtypes")
public class RecombineOperator extends BaseGeneticOperator implements Comparable {

    private static final long serialVersionUID = 1L;

    private double m_reproductionRatio;// p = 0.4

    public RecombineOperator() throws InvalidConfigurationException {
        super(Genotype.getStaticConfiguration());
    }
    public RecombineOperator(Configuration a_configuration) throws InvalidConfigurationException {
        super(a_configuration);
        // TODO Auto-generated constructor stub
    }

    public RecombineOperator(final Configuration a_configuration, 
                             final double a_desiredReproductionRatio)
            throws InvalidConfigurationException {
        super(a_configuration);
        m_reproductionRatio = a_desiredReproductionRatio;
    }

    /*
     * recombine操作过程
     * pop(t),每个个体被选中的概率是一样的，用于交叉和变异，那就所有的个体都进行交叉和变异
     * aux(t),在交叉和变异后的个体，选择ratio * Popsize 个个体
     */
    //recombine: 生成AuxPopulation
    public void operate(Population a_population, List a_candidateChromosomes) {
        // TODO Auto-generated method stub
        
        System.out.println("every time a_population size: "+a_population.size());
        
        int auxPopSize = (int)(a_population.size()*m_reproductionRatio);
        for(int i = 0;i<auxPopSize;i++){
            a_population.addChromosome(a_population.getChromosome((int) Math.random()*a_population.size()));
        }
        
        System.out.println("AuxPopSize: "+ auxPopSize+" "+"newPopSize: "+a_population.size());
    }

    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    class RecombineOperatorConfigurable implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        
        public double m_reproductionRatio;
    }
}
