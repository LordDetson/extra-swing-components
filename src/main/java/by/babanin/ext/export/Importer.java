package by.babanin.ext.export;

@FunctionalInterface
public interface Importer<T> {

    T doImport();
}
