/**
 * 
 */
package com.gyy.lifetime;

import java.util.ArrayList;
import java.util.Iterator;
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
public class EliminationOperator extends BaseGeneticOperator implements Comparable {

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
     * elimination 涉及到 lifetime 参数 因此在org.jap里的population.java文件进行了修改
     * 添加了用于计算lifetime的参数，其他不作任何修改，可以直接使用 加在population.java文件的开头和末尾
     * 因此在org.jap里的IChromosome.java文件进行了修改 加在IChromosome.java文件的末尾
     */
    public void operate(Population a_population, List a_candidateChromosomes) {
        // TODO Auto-generated method stub

        double bestFit = a_population.getBestFit();
        double worstFit = a_population.getWorstFit();
        double avgFit = a_population.getAvgFit();

        System.out.println("current population size: " + a_population.size());

        
        //记录删除的个数
        int delete = 0;
        
        /*
         * 根据lifetime和age值进行消除个体 age=0 表示是刚生成的个体，因为lifetime在每个个体出生时只计算一次
         */
        for (int i = 0; i < a_population.size();) {
            IChromosome a_chromosome = a_population.getChromosome(i);
            int indivLifetime = a_chromosome.getLifetime();
            int indivAge = a_chromosome.getAge();

           // System.out.println("indiv [" + i + "]" + " lifetime: " + indivLifetime + " age: " + indivAge);
  
            
            // 不是第一次产生的个体
            if (indivAge != 0 && indivLifetime == 0) {
                // 如果lifetime为0，说明刚被初始化，需要计算lifetime值
                indivLifetime = a_population.lifetimeCal(a_chromosome, indivLifetime, bestFit, avgFit, worstFit);
                a_chromosome.setLifetime(indivLifetime);
             //   System.out.println("重新计算过的：indiv [" + i + "]" + " lifetime: " + indivLifetime + " age: " + indivAge);
                // 执行删除机制
                if (indivAge >= indivLifetime) {
                    a_population.getChromosomes().remove(i);
                    delete = delete+1;
               //     System.out.println("indiv [" + i + "]" + " lifetime: " + indivLifetime + " is removed...");
                } else {
                    i++;
                //    System.out.println("lifetime >= age, cannot be removed...");
                }
            } else if (indivAge == 0)
            // 是第一次产生的个体，都需要计算lifetime值
            {
                indivLifetime = a_population.lifetimeCal(a_chromosome, indivLifetime, bestFit, avgFit, worstFit);
                a_chromosome.setLifetime(indivLifetime);
              //  System.out.println("刚产生的个体计算过的值：indiv [" + i + "]" + " lifetime: " + indivLifetime + " age: "
               //         + indivAge);
                // 执行删除机制
                if (indivAge >= indivLifetime) {
                    a_population.getChromosomes().remove(i);
                    delete = delete+1;
                  //  System.out.println("indiv [" + i + "]" + " lifetime: " + indivLifetime + "< age "+indivAge +" is removed...");
                } else {
                    i++;
                  //  System.out.println("lifetime >= age, cannot be removed...");
                }
            }
            else if(indivAge !=0 && indivLifetime !=0){
                // 执行删除机制
                if (indivAge >= indivLifetime) {
                    a_population.getChromosomes().remove(i);
                    delete = delete+1;
                  //  System.out.println("indiv [" + i + "]" + " lifetime: " + indivLifetime + "< age "+indivAge +" is removed...");
                } else {
                    i++;
                   // System.out.println("lifetime >= age, cannot be removed...");
                }
            }
            continue;
        }
        System.out.println("delete individuals: "+delete);
        System.out.println("after lifetime, current poplation size:" + a_population.size());
    }
    

    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
