6. Demuestra o da un contraejemplo:
- Los nodos de cualquier arbol AVL pueden colorearse de rojo y negro para obtener un ́arbol rojo-negro valido.

Esto es cierto, y para demostrarlo, primero hay que mencionar que ambos son arboles BST, por lo que ya tienen el mismo "orden implicito".
Además, verificando las propiedades de arboles AVL y rojinegros, notamos que la de AVL es mas restrictiva respecto altura; puesto que la mayor diferencia de alturas entre 2 sub-arboles hijos de un mismo nodo, será a lo mas 1. Así, propagandose ésta propiedad por todos los nodos, se tiene que tambien se cumple para la raíz.

Entonces, ya que conocemos que en todo momento hay, a lo más 1 nivel de diferencia entre las hojas, procedemos a analizar las propiedades de Rojinegros:
 Todo nodo es rojo o negro
 La raiz es negra
 Todas las hojas (null) son negras
 Todo nodo rojo ha de tener dos hijos negros/No hay dos nodos rojos contiguos (padre-hijo)
 Cada camino desde un dado nodo a sus hojas contiene la misma cantidad de nodos negros.

Ahora que las conocemos, primero dire que todos los nodos del arbol AVL seran negros, y con esto casi cumplimos las restricciones, excepto por la ultima, debido a que la diferencia de altura puede hacer que haya un nodo negro mas que en el del vecino en algun punto.

Por esto, si el arbol tiene un factor-balance 0, al haber pintado de negro los nodos acabamos, pero en otro caso (factor-balance 1 o -1), la solución que propongo es todos los nodos en el nivel mas profundo sean re-coloreados de rojo, y además los hijos izquierdo y derecho del nodo raíz también sean re-coloreados de rojo.

- Cualquier  ́arbol rojo-negro satisface las propiedades de  ́arbol AVL.
Esto es falso, debido a que el arbol AVL tiene una "tolerancia" de diferencia de alturas en sus sub-arboles claramente marcada como máximo 1, mientras que el arbol rojinegro fácilmente puede sobrepasar esta limitación, un ejemplo de lo mencionado es el siguiente:

[aqui la imagen chida]

7. 

8. Supongase T como un arbol 2-4, con n_l hojas y n_i nodos internos...
- ¿Cuál es el mínimo valor de n_i, como funcion de n_l?
Ya que se estan considerando la menor cantidad de nodos n_i, se asume que, si no todos, la mayoría dominante de estos serán nodos-4 (ya que de no ser así, se requieren mas nodos como padres de los nodos n_l, lo que resultaría en un aumento mas notable de nodos), entonces, suponiento que se tienen 4k + c nodos n_l, con 0 <= c < 4, se tendrán a lo más k+1 nodos padres de n_l, luego, en nuestro afan de ahorrar nodos, supondré que se siguen usando nodos 4 en n_i, así, notamos que se van reduciendo los nodos padre en razon de 4, lo cual nos recomienda el comportamiento logaritmico observado en los arboles binarios ya estudiados previamente.

Entonces, si se tienen 64 hojas, hay 16 padres de estos, que a su vez tienen 4 padres, que a su vez tienen 1 padre (raiz), lo que corresponde a que, si se tienen 64 n_l, se tienen 16 + 4 + 1 => 4^3 nl dan 4^2 + 4^1 + 4^0 ni
Así, si tengo 4k+c = nl => Sigma_{x=0}^{log4 (4k+c) -1} 4^x = ni
con lo que tengo que ni = 4^{log4(4k+c)-1+1} -1/4 - 1 = (4k+c) -1 / 3

nl = 64 => k=16 , c=0 => ni = (4*16 + 0)-1/3 = 64 -1/3 = 63/3 = 21
Lo que concuerda con el valor esperado, por lo que concluyo que
n_i = (4k + c)-1/3, con n_l = 4k + c & 0<= c <4 

- ¿Cuál es el maximo valor de n_l, como funcion de n_i?
Ahora queremos maximizar el valor de nl en base ni...
para esto, asumo que los padres de los nodos nl siempre son nodos4, sin embargo tengo que analizar como considerar el resto de nodos ni.
En este analisis visual, el circulo izquierdo corresponde a la cantidad de nodos ni en ese nivel, mientras que el circulo derecho representa la cantidad de nodos nl total que se obtendria de maximizar en ese punto los nodos nl, finalmente, el cuadrado representa la cantidad total de nodos ni acumulados en el arbol, de modo que se puede notar la cantidad de nodos ni y cuantos nodos nl producen con el razonamiento dado.
[imagen chida aqui]

Del previo analisis, notamos que tomando que todos los nodos ni sean nodos4, maximizamos la cantidad de nodos li, ademas que requerimos de unna proporcion menor de nodos ni que con otras configuraciones.

Entonces, notamos que los nodos nl aumentan en forma de potencias de 4, mientras que los nodos n_i aumentan por capas, es decir, que la cantidad de nodos n_l depende del nivel alcanzado por los nodos n_i.
Así, conocemos que la cantidad de nodos n_l será de la forma 4^{floor( log4( n_i ) ) + 1 }. 

- Si T' fuese un arbol rojinegro que representa T, ¿Cuántos nodos rojos tendría?
