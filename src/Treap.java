
/**
 * Implementacion de Treaps
 */
public class Treap {
    
    protected class NodoTreap extends Nodo<Integer>{

        /** Clave unica asignada al Nodo Treap */
        /** Prioridad asignada al Nodo Treap */
        private float prioridad = 0.0f;

        /**
         * Constructor que asigna como valor almacenado la clave indicada
         * @param clave - {@code int} valor de key unico asignado al nodo
         */
        public NodoTreap( int clave ){
            super( new Integer(clave) );
            this.prioridad = 0.0f;
        }

        /**
         * Constructor que asigna como valor almacenado la clave indicada
         * @param clave - {@code int} valor de key unico asignado al nodo
         * @param prioridad - {@code float} valor de prioridad que se usara
         *                  para el ordenamiento
         */
        public NodoTreap( int clave , float prioridad ){
            super( new Integer(clave) );
            this.prioridad = prioridad;
        }
    }

}
