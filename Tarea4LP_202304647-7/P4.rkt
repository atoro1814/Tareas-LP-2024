#lang scheme

;; Devuelve el primer elemento de la lista.
;;
;; lista : Una lista de elementos.
;; devuelve : El primer elemento de la lista.
(define (primer lista)
  (car lista))


;; Devuelve el primer elemento de la lista.
;;
;; lista : Una lista de elementos.
;; devuelve : El primer elemento de la lista.
(define (segundo lista)
  (cdr lista))

;; Función principal que calcula las profundidades de los tesoros en un árbol.
;;
;; arbol : Representación de un árbol como una lista.
;; devuelve : Una lista con las profundidades de los tesoros, ordenada de menor a mayor.
(define (profundidades arbol)
  (sort (recorrer arbol 0) <))


;; Función principal que calcula las profundidades de los tesoros en un árbol.
;;
;; arbol : Representación de un árbol como una lista.
;; devuelve : Una lista con las profundidades de los tesoros, ordenada de menor a mayor.
(define (recorrer lista nivel)
  (if (null? lista)
      '() 
      (let ((raiz (primer lista))
            (cola (segundo lista)))
        (let ((resultados (if (eq? raiz 'T)
                              (list nivel) 
                              '()))) 

          
          (append resultados
                  (recorrer2 cola (+ nivel 1)))))))
;; Recorre la lista de subárboles y acumula las profundidades de los tesoros.
;;
;; lista : Lista de subárboles.
;; nivel : Profundidad actual en el árbol.
;; devuelve : Una lista de profundidades de los tesoros encontrados en los subárboles.
(define (recorrer2 lista nivel)
  
  (if (null? lista)
      '()
      (let ((subarbol (primer lista))
            (resto (segundo lista)))
        
        (append (if (list? subarbol)
                    (recorrer subarbol nivel) 
                    '()) ;; 
                (recorrer2 resto nivel))))) ;;

