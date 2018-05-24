package Command;

/**
 * Created by zhouyu on 11/12/2016.
 * 命令接口
 */
public interface Command
{
    public abstract void execute(String name);
    public abstract void undo();

}
