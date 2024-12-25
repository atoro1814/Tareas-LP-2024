#lang scheme
;; Busca un elemento en una lista y devuelve su posición (1-indexada).
;;
;; Lista : Una lista en la que se busca el elemento.
;; elemento : El elemento que se desea encontrar en la lista.
;; devuelve : La posición del elemento en la lista (1-indexada), o -1 si no se encuentra.
(define (buscador Lista elemento)
  (let ciclo ((lista Lista) (pos 1));
   (cond
      ((null? lista) -1)
      ((equal? (car lista) elemento) pos)
      (else(ciclo (cdr lista ) (+ pos 1))))))


