/**
 * 
 */
package com.gyy.lifetime;

import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.InvalidConfigurationException;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.GABreeder;
import org.jgap.impl.MutationOperator;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.util.ICloneable;

/**
 * @author Gloria
 *
 */
@SuppressWarnings("serial")
public class LifeTimeConfiguration extends Configuration implements ICloneable{

    public LifeTimeConfiguration(){
        this("","");
    }
    
    public LifeTimeConfiguration(String id, String name){
        super(id,name);
        try{
            setBreeder(new GABreeder());
            setRandomGenerator(new StockRandomGenerator());
            setEventManager(new EventManager());
            /*
             * 因为存在lifetime的消除机制，所有在本次算法中，不需要每次在运行自然选择，来挑选出个体
             * 在configuration.java文件中的1126行删除了natural selector 必须大于等于0的判定条件
             * 运行成功了，但是结果跑不对了
             */
           /* BestChromosomesSelector bestChromsSelector = new BestChromosomesSelector(
                this, 0.90d);
            bestChromsSelector.setDoubletteChromosomesAllowed(true);
            addNaturalSelector(bestChromsSelector, false);*/
            setMinimumPopSizePercent(0);
            setSelectFromPrevGen(1.0d);
            setKeepPopulationSizeConstant(false);//dynamic population size
            setFitnessEvaluator(new DefaultFitnessEvaluator());
            setChromosomePool(new ChromosomePool());
           // addGeneticOperator(new CrossoverOperator(this, 0.65d));//crossover rate = 0.65
           // addGeneticOperator(new MutationOperator(this, 66)); //mutation rate = 0.015
            addGeneticOperator(new RecombineOperator(this, 0.4,0.65d,66));//reproduction ratio = 0.4
            addGeneticOperator(new EliminationOperator(this));//elimination
        }
        catch(InvalidConfigurationException e){
            throw new RuntimeException(
                    "Fatal error: DefaultConfiguration class could not use its "
                    + "own stock configuration values. This should never happen. "
                    + "Please report this as a bug to the JGAP team.");
        }
    }
}
