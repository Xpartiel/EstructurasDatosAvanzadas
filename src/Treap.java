/**
 * Implementacion de Treaps - Hecha con Java8
 */
import java.util.Random;

public class Treap {

    /** Generador usado para los nodos */
    private Random generador;

    /** {@link NodoTreap} a la raiz del {@link Treap} */
    private NodoTreap raiz;

    /** Contador absoluto de elementos en la estructura {@link Treap} */
    private int size;
    
    /**
     * Implementacion especifica para {@link Nodo} en la estructura de
     * {@link Treap}
     */
    protected class NodoTreap extends Nodo<Integer>{
        
        /** Prioridad asignada al Nodo Treap */
        private float prioridad;

        /** Cantidad de nodos "debajo" del actual */
        private int descendientes;

        /**
         * Constructor que asigna como valor almacenado la clave indicada
         * @param clave - {@code int} valor de key unico asignado al nodo
         */
        public NodoTreap( int clave ){
            super( new Integer(clave) , 3 );
            this.prioridad = generador.nextFloat();
            this.descendientes = 0;
        }

        /**
         * Constructor que asigna como valor almacenado la clave indicada
         * @param clave - {@code int} valor de key unico asignado al nodo
         * @param prioridad - {@code float} valor de prioridad que se usara
         *                  para el ordenamiento
         */
        public NodoTreap( int clave , float prioridad ){
            super( new Integer(clave) , 3 );
            this.prioridad = prioridad;
            this.descendientes = 0;
        }

        public int getDescendientes(){
            return this.descendientes;
        }

        /** Getter para el padre del nodo */
        public NodoTreap getPadre(){
            return (NodoTreap) this.getReferencias().get(0);
        }

        /** Setter para el padre del nodo */
        public void setPadre( NodoTreap n ){
            this.setReferencia( 0 , n );
        }

        /** Getter del hijo izquierdo del nodo
         * Aprovechando la implementacion del nodo, se asume siempre que el
         * nodo en la posicion {@code 1} sera el hijo izquierdo, puede adquirir
         * el valor {@code null} si no posee hijo izquierdo.
         */
        public NodoTreap getHijoIzquierdo(){
            if( this.getReferencias().size()<1 ){
                return null;
            }
            return (NodoTreap) this.getReferencias().get(1) ;
        }

        /**
         * Setter del hijo izquierdo del nodo.
         * @param n - El {@link NodoTreap} que sera referenciado como hijo
         * derecho en la posicion {@code 1} del {@link ArrayList} interno.
         */
        public void setHijoIzquierdo( NodoTreap n ){
            this.setReferencia( 1 , n);
            this.descendientes += ( 1 + n.getDescendientes());
        }

        /** Getter del hijo derecho del nodo.
         * Aprovechando la implementacion del nodo, se asume siempre que el
         * nodo en la posicion {@code 2} sera el hijo derecho, puede adquirir
         * el valor {@code null} si no posee hijo derecho.
         */
        public NodoTreap getHijoDerecho(){
            return (NodoTreap) this.getReferencias().get(2) ;
        }

        /**
         * Setter del hijo derecho del nodo.
         * @param n - El {@link NodoTreap} que sera referenciado como hijo
         * derecho en la posicion {@code 2} del {@link ArrayList} interno.
         */
        public void setHijoDerecho( NodoTreap n ){
            this.setReferencia( 2 , n );
            this.descendientes += ( 1 + n.getDescendientes());
        }
    }

    /** Constructor vacio */
    public Treap(){
        this.generador = new Random();
        this.raiz = null;
        this.size = 0;
    }

    /**
     * Constructor que inicializa con un unico elemento
     * @param valor - Valor de la clave con que se va a dar la
     *                inicializacion al nodo.
     */
    public Treap( int valor ){
        this.generador = new Random();
        this.raiz = new NodoTreap(valor);
        this.size = 1;
    }

    /**
     * Solo obtener el nodo a la raiz del {@link Treap}
     */
    public NodoTreap peek(){
        return this.raiz;
    }

    /**
     * Metodo con que se busca el {@link NodoTreap} que contiene el valor
     * indicado, partiendo desde la raiz.
     * @param val - El valor que se utiliza para buscar el nodo correspondiente
     * en el {@link Treap}
     * @return
     * <ul>
     * <li>{@link NodoTreap} dentro de la estructura que contiene el valor
     *      indicado</li>
     * <li>{@code null} Si no el valor no se encuentra en la estructura</li>
     * </ul>
     */
    public NodoTreap getValor( int valor ){

        NodoTreap iterando = this.raiz;
        int valorActual;

        while ( iterando!=null && !(iterando.getValor().intValue() == valor) ) {
            valorActual = iterando.getValor().intValue();
            if( valor < valorActual ){
                iterando = iterando.getHijoIzquierdo();
            }else if( valorActual < valor ){
                iterando = iterando.getHijoDerecho();
            }
        }
        // Si es o no encontrado, ambas posibilidades estan cubiertas
        // por el paro del while.
        return iterando;
    }

    /**
     * Metodo con que se busca el {@link NodoTreap} en la i-esima posicion
     * @param indice - {@code int} La posicion del nodo buscado.
     * @return El nodo encontrado en la posicion indicada.
     * <ul>
     * <li>{@link NodoTreap} correspondiende al indice indicado</li>
     * <li>{@code null} Si no se encuentra algun valor</li>
     * </ul>
     */
    public NodoTreap getIndex( int indice ){
        return null;
    }
}
