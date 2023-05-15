package by.babanin.ext.task.listener;

import java.util.function.Consumer;

@FunctionalInterface
public interface FinishListener<R> extends Consumer<R> {

}
