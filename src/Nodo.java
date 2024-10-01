import java.util.ArrayList;

public class Nodo<T> {
    
    /** El valor especifico contenido en el nodo */
    private T valor;

    /** Nodos con los que el nodo esta relacionado de alguna forma */
    private ArrayList<Nodo<T>> referencias;

    /**
     * Constructor por defecto
     * @param valor - {@code T} Es una implementacion generica de Nodos, se espera que
     *                  se utilicen nodos de un mismo tipo en la estructura que les use.
     */
    public Nodo( T valor ){
        this.valor = valor;
        this.referencias = new ArrayList<Nodo<T>>();
    }

    /**
     * 
     * @param valor
     */
    public void setValor( T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }
}
