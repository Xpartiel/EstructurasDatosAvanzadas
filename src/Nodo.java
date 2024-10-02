import java.util.ArrayList;

/**
 * Implementacion de Nodo para tipos genericos, se espera que la super-estructura
 * que los utilice no haga muchas cosas raras, porque la implementacion es mas
 * bien simple.
 */
public class Nodo<T> {
    
    /** El valor especifico contenido en el nodo */
    private T valor;

    /** Nodos con los que el nodo esta relacionado de alguna forma */
    private ArrayList<Nodo<T>> referencias;

    /**
     * Constructor por defecto
     * @param valor - {@code T} Es una implementacion generica de Nodos, se espera
     *         que se utilicen nodos de un mismo tipo en la estructura que les use.
     */
    public Nodo( T valor ){
        this.valor = valor;
        this.referencias = new ArrayList<Nodo<T>>();
    }

    /**
     * Constructor por defecto
     * @param valor - {@code T} Es una implementacion generica de Nodos, se espera
     *         que se utilicen nodos de un mismo tipo en la estructura que les use.
     */
    public Nodo( T valor , int referenciasTotales ){
        this.valor = valor;
        this.referencias = new ArrayList<Nodo<T>>( (int) Math.abs( referenciasTotales ) );
        for (int i = 0; i < referenciasTotales; i++) {
            this.referencias.add(null);
        }
    }

    /**
     * Setter para modificar el valor almacenado internamente
     * @param valor - {@code Tipo} El nuevo valor a almacenar dentro del Nodo.
     */
    public void setValor( T valor) {
        this.valor = valor;
    }

    /**
     * Getter para obtener el valor almacenado internamente en el nodo
     * @return - {@code Tipo} Valor almacenado
     */
    public T getValor() {
        return valor;
    }

    public ArrayList<Nodo<T>> getReferencias(){
        return this.referencias;
    }

    public void setReferencia( int indice , Nodo<T> nuevo ){
        if( indice<0 ){
            return;
        }
        this.referencias.set(indice, nuevo);
    }


}
