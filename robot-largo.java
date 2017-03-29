(machine prueba
(var random := [2 atRandom])
(var lAzules :=[5])
(var lAmarillos := [5])
(var comenzar := [false])
(var numero := [0])
(var resta := [0])

(state inicial (onexit [numero := lAzules+lAmarillos. resta:= lAmarillos-lAzules]))
(state repartidor )
(state amarillo (onentry [numero := numero -1. lAmarillos := lAmarillos-1]))
(state comprobarY (onentry [random := 2 atRandom]))
(state azul (onentry [random := 2 atRandom. numero := numero-1. lAzules := lAzules-1]))
(state comprobarB (onentry [random := 2 atRandom]))
(state standBy)
(state verde)
(state rojo)

(event faltanY [lAmarillos > 0])
(event finY [lAmarillos == 0])
(event faltanB [lAzules > 0])
(event finB [lAzules == 0])

(event iguales [resta == 0])
(event diferentes [(resta == 0) not])
(event fin [numero == 0])

(event left [random == 1])
(event right [random == 2])

(event iniciar [comenzar == true])


(on iniciar inicial -> repartidor)
(on fin repartidor -> standBy)

(on left repartidor -> comprobarY)
(on faltanY comprobarY -> amarillo)
(on finY comprobarY -> repartidor)
(ontime 100 amarillo -> repartidor)

(on right repartidor -> azul)
(on faltanB comprobarB -> azul)
(on finB comprobarB -> repartidor)
(ontime 100 azul -> repartidor)

(on iguales standBy -> verde)
(on diferentes standBy -> rojo)

) (spawn prueba inicial)