package Command;

/**
 * Created by zhouyu on 11/12/2016.
 * 请求者
 */
public class RequestMakedir
{
    Command command;
    public void  setCommand (Command command)
    {
        this.command=command;
    }
    public void startExcuteCommand(String name)
    {
        command.execute(name);
    }
    public void undoCommand()
    {
        command.undo();
    }
}
