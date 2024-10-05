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

        /**
         * Metodo privado con que se verifica que dos prioridades tengan una diferencia
         * aceptable, ya que float u double suelen tener problemas con la comparacion
         * estricta
         * @param a
         * @param b
         * @return
         */
        private boolean aprox( float a , float b ){
            return Math.abs( a-b )<0.1f;
        }

        @Override
        public boolean equals( Object otro ){
            NodoTreap nodoO = (NodoTreap) otro;
            return super.equals(otro)? this.aprox( this.getPrioridad() , nodoO.getPrioridad() ) : false;
        }

        public int getDescendientes(){
            return this.descendientes;
        }

        public void setDescendientes( int nuevo ){
            this.descendientes = nuevo;
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

            n.setPadre(this);
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

            n.setPadre(this);
        }

        public float getPrioridad() {
            return prioridad;
        }

        public void setPrioridad(float prioridad) {
            this.prioridad = prioridad;
        }

        /**
         * Implementacion de rotaciones para {@link NodoTreap}
         */
        public void rotar( String modo ){
            char eleccion = modo.toLowerCase().charAt(0);
            switch ( eleccion ) {
                case 'l':
                case 'i':

                    NodoTreap R = this.getHijoDerecho();// 1 TreapNod* R = root->r;  
                    NodoTreap X = this.getHijoDerecho().getHijoIzquierdo();// 2 TreapNod* X = root->r->l;  

                    R.setHijoIzquierdo( this ); // 3 R->l = root;
                    this.setHijoDerecho(X); // 4 root->r= X;
                    break;
                
                case 'r':
                case 'd':
                    NodoTreap L = this.getHijoIzquierdo();
                    NodoTreap Y = this.getHijoIzquierdo().getHijoDerecho();

                    L.setHijoDerecho( this );
                    this.setHijoIzquierdo(Y);
                    break;

                default:
                    break;
            }
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

    public int size(){
        return this.size;
    }

    public void insertValue( int valor ){
        this.insertValue( valor , generador.nextFloat() );
    }

    public void heapify( NodoTreap iterando , NodoTreap nuevo ){
        while( !this.peek().equals(nuevo) && nuevo.getPadre().getPrioridad()<nuevo.getPrioridad() ){
            iterando = nuevo.getPadre();
            if( iterando.getHijoDerecho()!=null && iterando.getHijoDerecho().equals( nuevo ) ){
                iterando.rotar("izquierda");
            }else if( iterando.getHijoIzquierdo()!=null && iterando.getHijoIzquierdo().equals( nuevo ) ){
                iterando.rotar("derecha");
            }
        }
    }

    /**
     * Metodo con que se inserta al a estructura un nuevo Nodo con el valor
     * y prioridad indicados, usando la estrategia tipica de un Binary Search
     * Tree; posterior a la insercion, se relaizan rotaciones si es que son
     * necesaras.
     * @param valor - {@code int} el valor asignado al {@link NodoTreap}
     *                  a insertar, necesario para la insercion BST.
     * @param prioridad - {@code float} el valor de prioridad asignado al
     *                  {@link NodoTreap} a insertar, necesario para las
     *                  rotaciones
     */
    public void insertValue( int valor , float prioridad ){

        NodoTreap nuevo = new NodoTreap( valor , prioridad );
        
        if( this.size() < 1 ){
            this.raiz = nuevo;
            this.size += 1;
            return;
        }

        NodoTreap iterando = this.raiz;
        int val = 0;

        // Insercion como en BST
        while ( iterando!=null ) {

            val = iterando.getValor().intValue();
            
            if( val < valor ){
                if( iterando.getHijoDerecho() != null ){
                    iterando = iterando.getHijoDerecho();
                }else{
                    iterando.setHijoDerecho( nuevo );
                    this.size += 1;

                    //Rotar hacia arriba hasta que el padre tenga una prioridad superior, o se haya vuelto
                    //la raiz del Treap
                    this.heapify(iterando, nuevo);
                    break;
                }
            }else if( valor < val ){
                if( iterando.getHijoIzquierdo() != null ){
                    iterando = iterando.getHijoIzquierdo();
                }else{
                    iterando.setHijoIzquierdo(nuevo);
                    this.size += 1;

                    // Ya que se ha insertado al fondo del arbol, se realizan las rotaciones que sean necesarias
                    //if( iterando.getPrioridad() < iterando.getHijoIzquierdo().getPrioridad() ){
                    //    iterando.rotar("derecha");
                    //}
                    this.heapify(iterando, nuevo);

                    break;
                }
            }
        }
    }

    //TODO
    public boolean deleteValue( int valor ){
        // Buscar el nodo dentro de la estructura
        NodoTreap nodo = this.getValue(valor);
        // Valor no encontrado
        if( nodo == null ){
            return false;
        }
        // Rotar el nodo hacia abajo hasta que sea una hoja
        while (nodo.getHijoIzquierdo() != null || nodo.getHijoDerecho() != null) {
            if (nodo.getHijoIzquierdo() == null) {
                nodo.rotar("izquierda");
            } else if (nodo.getHijoDerecho() == null) {
                nodo.rotar("derecha");
            } else if (nodo.getHijoIzquierdo().getPrioridad() < nodo.getHijoDerecho().getPrioridad()) {
                nodo.rotar("izquierda");
            } else {
                nodo.rotar("derecha");
            }
        }
        // Ya que el nodo es hoja, eliminarlo de la raiz
        NodoTreap padre = nodo.getPadre();
        if (padre != null) {
            if (padre.getHijoIzquierdo() == nodo) {
                padre.setHijoIzquierdo(null);
            } else {
                padre.setHijoDerecho(null);
            }
        } else {
            raiz = null;
        }
        size--;
        return true;
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
    public NodoTreap getValue( int valor ){

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
        return this.getIndexAux( this.raiz , indice );
    }
    
    private NodoTreap getIndexAux(NodoTreap nodo, int indice) {
        if (nodo == null){
            return null;
        }
        int leftCount = (nodo.getHijoIzquierdo() != null) ? nodo.getHijoIzquierdo().getDescendientes() + 1 : 0;
    
        if (indice < leftCount) {
            return this.getIndexAux(nodo.getHijoIzquierdo(), indice);
        } else if (indice == leftCount) {
            return nodo;
        } else {
            return this.getIndexAux(nodo.getHijoDerecho(), indice - leftCount - 1);
        }
    }

    /**
     * Solo obtener el nodo a la raiz del {@link Treap}
     */
    public NodoTreap peek(){
        return this.raiz;
    }

    public NodoTreap pop(){
        if( this.raiz==null ){
            return null;
        }
        NodoTreap resultado = this.raiz;
        this.deleteValue( this.raiz.getValor() );
        return resultado;
    }
}
