% Predicado principal para verificar si un segmento de una lista es palíndromo.
% esPalindroma(Lista, [L, R]).
%
% Parámetros:
%   - Lista: La lista de elementos en la que buscamos el segmento.
%   - [L, R]: Los índices que delimitan el inicio (L) y el final (R) del segmento.
%
% Función:
%   - Extrae el subsegmento desde los índices L a R usando obtener_sublista.
%   - Invierte el subsegmento utilizando reverse.
%   - Compara el subsegmento original con su reverso para verificar si es palíndromo.

esPalindroma(Lista, [L, R]) :- 
    obtener_sublista(Lista, [L, R], Sublista),
    reverse(Sublista, SublistaReversa),  % Obtenemos el reverso de la sublista
    Sublista = SublistaReversa.  % Comparamos sublista y reverso





% Predicado para obtener un subsegmento de una lista delimitado por índices.
% obtener_sublista(Lista, [L, R], Sublista).
%
% Parámetros:
%   - Lista: La lista de elementos de la que extraeremos el subsegmento.
%   - [L, R]: Los índices que delimitan el inicio (L) y el final (R) del subsegmento.
%   - Sublista: La sublista resultante entre los índices L y R.
%
% Función:
%   - Llama al predicado auxiliar sublista_aux/5 para realizar la extracción,
%     iniciando el contador en 1 para recorrer la lista
obtener_sublista(Lista, [L, R], Sublista) :- 
    sublista_aux(Lista, L, R, 1, Sublista).





% Predicado auxiliar recursivo para construir el subsegmento de la lista.
% sublista_aux(Lista, L, R, Contador, Sublista).
%
% Parámetros:
%   - Lista: La lista de elementos de la que extraeremos el subsegmento.
%   - L: Índice de inicio del segmento.
%   - R: Índice de fin del segmento.
%   - Contador: Índice actual que estamos procesando en la lista.
%   - Sublista: La sublista parcial que se está construyendo.
%
% Casos:
%   1. Si el Contador supera el índice R, terminamos la sublista.
%   2. Si el Contador está dentro del rango [L, R], incluimos el elemento en la sublista.
%   3. Si el Contador es menor que L, omitimos el elemento y seguimos avanzando.

sublista_aux(_, _, R, Contador, []) :- Contador > R, !.


sublista_aux([X|T], L, R, Contador, [X|Sublista]) :-
    Contador >= L,
    Contador =< R,
    Contador1 is Contador + 1,
    sublista_aux(T, L, R, Contador1, Sublista).


sublista_aux([_|T], L, R, Contador, Sublista) :-
    Contador < L,
    Contador1 is Contador + 1,
    sublista_aux(T, L, R, Contador1, Sublista).

