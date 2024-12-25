#lang scheme
;; Calcula el valor de la serie de Taylor para el seno de x.
;;
;; n : Número de términos de la serie a calcular.
;; x : Valor en el que se evalúa el seno.
;; devuelve : Aproximación del seno de x usando n términos de la serie de Taylor.
(define (taylorSenoSimple n x)
  (if (= n 0)
      x  
      (let* ((termino (expt -1 n))  ;
             (factorial (factorial (+ (* 2 n) 1)))  
             (exponente (expt x (+ (* 2 n) 1))))  
        (+ (/ (* termino exponente) factorial) 
           (taylorSenoSimple (- n 1) x)))))


;; Calcula el factorial de n de forma recursiva.
;;
;; n : Número del cual se desea calcular el factorial.
;; devuelve : El factorial de n.
(define factorial
 (lambda (n)
   (let fact ((i n))
     (if (= i 0)
         1
           (* i (fact (- i 1)))))))


;; Calcula el factorial de n usando recursión en cola.
;;
;; n : Número del cual se desea calcular el factorial.
;; devuelve : El factorial de n usando un acumulador para optimizar.
(define factorial_cola
 (lambda (n)
 (let fact ((i n) (a 1))
 (if (= i 0)
 a
 (fact (- i 1) (* a i))))))


;; Calcula el valor de la serie de Taylor para el coseno de x.
;;
;; n : Número de términos de la serie a calcular.
;; x : Valor en el que se evalúa el coseno.
;; devuelve : Aproximación del coseno de x usando n términos de la serie de Taylor.
(define (taylorCosenoCola n x)
  (define (fun_auxiliar n  acumulador)
    (if(= n 0)
       acumulador
       (let* ((resultado (/ (* (expt -1 n) (expt x (* 2 n))) (factorial_cola (* 2 n)))))
          (fun_auxiliar (- n 1) (+ acumulador resultado)))))
  (fun_auxiliar n 1))
  
     