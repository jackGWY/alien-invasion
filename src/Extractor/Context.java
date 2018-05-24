package Extractor;

/**
 * Created by zhouyu on 11/12/2016.
 * 策略模式上下文
 */
public class Context
{
    Strategy strategy;
    public void setStrategy(Strategy strategy)
    {
        this.strategy=strategy;
    }

    public String getStrategy(String Name)
    {
        return strategy.getText(Name);
    }
}
