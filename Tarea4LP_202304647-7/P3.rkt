#lang scheme
;; Rota la lista, moviendo el primer elemento al final.
;;
;; lista : Una lista de elementos a rotar.
;; devuelve : Una nueva lista con el primer elemento movido al final.
(define (rotar lista )
  (if (null? lista)
      '()
      (append (cdr lista) (list (car lista)))))

;; Aplica n rotaciones a la lista de funciones.
;;
;; funciones : Una lista de funciones a rotar.
;; n : Número de rotaciones a realizar.
;; devuelve : La lista de funciones después de aplicar n rotaciones.
(define (iterar_rotaciones funciones n)
  (if (= n 0)
      funciones
      (iterar_rotaciones  (rotar funciones) (- n 1))))





;; Aplica una composición de funciones a un valor dado.
;;
;; funciones : Una lista de funciones a aplicar.
;; valor : El valor al que se le aplicarán las funciones.
;; devuelve : El resultado de aplicar las funciones a valor.
(define(aplicar_composicion funciones valor)
  (if(null? funciones)
     valor
     (let ((nuevo_valor (aplicar_funcion funciones valor)))
     (aplicar_composicion (cdr funciones) nuevo_valor))))




;; Aplica la primera función en la lista de funciones a un valor.
;;
;; funciones : Una lista de funciones, donde se aplicará la primera función.
;; valor : El valor al que se le aplicará la función.
;; devuelve : El resultado de aplicar la primera función a valor.
(define (aplicar_funcion funciones valor)
  ((car funciones) valor ))




;; Evalúa una lista de funciones para una lista de números.
;;
;; funciones : Una lista de funciones a aplicar.
;; numeros : Una lista de números a los que se les aplicarán las funciones.
;; devuelve : Una lista con los resultados de aplicar las funciones a los números.
(define (evaluador funciones numeros)
  (define(evaluador2 funciones numeros contador resultados)
    
    (if(null? numeros)
       resultados
       (let* ((lista_rotada (iterar_rotaciones funciones contador))
              (valor (car numeros))
              (nuevo_valor(aplicar_composicion lista_rotada valor))
              (resultados (append resultados (list nuevo_valor))))
       (evaluador2 funciones (cdr numeros) (+ contador 1) resultados))))  
              
   
    (evaluador2 funciones numeros 0 '()))


 
  