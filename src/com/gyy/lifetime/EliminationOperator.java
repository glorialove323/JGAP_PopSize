/**
 * 
 */
package com.gyy.lifetime;

import java.util.List;

import org.jgap.BaseGeneticOperator;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;

/**
 * @author Gloria
 *
 */
@SuppressWarnings("rawtypes")
public class EliminationOperator extends BaseGeneticOperator implements Comparable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public EliminationOperator() throws InvalidConfigurationException {
        super(Genotype.getStaticConfiguration());
    }
    public EliminationOperator(Configuration a_configuration) throws InvalidConfigurationException {
        super(a_configuration);
        // TODO Auto-generated constructor stub
    }

    public EliminationOperator(final Configuration a_configuration, final int a_desiredReproductionRatio)
            throws InvalidConfigurationException {
        super(a_configuration); 
    }
    /*
     * elimination 涉及到 lifetime 参数
     * 因此在org.jap里的population.java文件进行了修改
     * 添加了用于计算lifetime的参数，其他不作任何修改，可以直接使用
     * 加在population.java文件的开头和末尾
     * 因此在org.jap里的IChromosome.java文件进行了修改
     * 加在IChromosome.java文件的末尾
     */
    public void operate(Population a_population, List a_candidateChromosomes) {
        // TODO Auto-generated method stub
        
        double bestFit = a_population.getBestFit();
        double worstFit = a_population.getWorstFit();
        double avgFit = a_population.getAvgFit();
         
        System.out.println("current population size: "+a_population.size());
        System.out.println("start calculate lifetime......");
        /*
         * 依次显示indiv的lifetime和age
         */
        for(int i=0;i<a_population.size();i++){
            IChromosome a_chromosome = a_population.getChromosome(i);
            int indivLifetime = a_chromosome.getLifetime();
            int indivAge = a_chromosome.getAge();
            System.out.println("indiv ["+i+"]: lifetime = "+indivLifetime+" age = "+indivAge);
        }
        for(int i = 0;i<a_population.size();i++){  
            IChromosome a_chromosome = a_population.getChromosome(i);
            int indivLifetime = a_chromosome.getLifetime();
            int indivAge = a_chromosome.getAge();
            //age=0 表示是刚生成的个体，因为lifetime在每个个体出生时只计算一次
            if (indivAge == 0)
            {
                indivLifetime = a_population.getLifetime(a_chromosome, bestFit,
                    avgFit, worstFit);
                a_chromosome.setLifetime(indivLifetime);
                System.out.println("indiv ["+i+"]"+"lifetime: "+indivLifetime);
            }//刚出生的个体是不可能被删除的
            else{              
                if(indivAge>indivLifetime){
                    a_population.getChromosomes().remove(i);
                    System.out.println("indiv ["+i+"]"+"lifetime: "+indivLifetime+" is removed...");
                }
            }         
        }
        System.out.println("after lifetime, current poplation size:"
                + a_population.size());   
    }

    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
