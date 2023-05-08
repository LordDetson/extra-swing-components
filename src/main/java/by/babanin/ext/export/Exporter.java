package by.babanin.ext.export;

@FunctionalInterface
public interface Exporter<T> {

    void doExport(T object);
}
