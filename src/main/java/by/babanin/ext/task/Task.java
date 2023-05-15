package by.babanin.ext.task;

import by.babanin.ext.task.listener.ExceptionListener;
import by.babanin.ext.task.listener.FinishListener;
import by.babanin.ext.task.listener.StartListener;

public interface Task<R> {

    void execute();

    String getName();

    void setName(String name);

    void addStartListener(StartListener listener);

    void removeStartListener(StartListener listener);

    void addFinishListener(FinishListener<R> listener);

    void removeFinishListener(FinishListener<R> listener);

    void addExceptionListener(ExceptionListener listener);

    void removeExceptionListener(ExceptionListener listener);
}
