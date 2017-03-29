(machine prueba
(var random := [2 atRandom])
(var lAzules :=[0])
(var lAmarillos := [0])
(var comenzar := [false])
(var numero := [0])
(var resta := [0])

(state inicial (onexit [numero := lAzules+lAmarillos. resta:= lAmarillos-lAzules]))
(state repartidor )
(state destrabar (onentry [random := 2 atRandom]))
(state amarillo (onentry [random := 2 atRandom. numero := numero -1. lAmarillos := lAmarillos-1]))
(state azul (onentry [random := 2 atRandom. numero := numero-1. lAzules := lAzules-1]))
(state standBy)
(state verde)
(state rojo)

(event iguales [resta == 0])
(event diferentes [(resta == 0) not])
(event fin [numero == 0])
(event left [random == 1 & lAmarillos > 0])
(event right [random == 2 & lAzules > 0])
(event iniciar [comenzar == true])


(on iniciar inicial -> repartidor)
(on fin repartidor -> standBy)
(on left repartidor -> amarillo)
(ontime 100 amarillo -> repartidor)
(on right repartidor -> azul)
(ontime 100 azul -> repartidor)
(on iguales standBy -> verde)
(on diferentes standBy -> rojo)
(ontime 200 repartidor -> destrabar)
(on iniciar destrabar -> repartidor)

) (spawn prueba inicial)