% Hechos: puente(Inicio, Fin, Costo)
% Representa un puente entre dos planetas. Cada puente tiene un costo
% asociado en términos de combustible necesario para recorrerlo.
% Parámetros:
%   - Inicio: Planeta desde el cual parte el puente.
%   - Fin: Planeta al que llega el puente.
%   - Costo: Cantidad de combustible necesario para usar el puente.
puente(p1,p4,1).
puente(p1,c,4).
puente(p2,p3,7).
puente(p2,p1,3).
puente(p3,c,2).
puente(p4,c,3).
puente(p5,p1,7).
puente(p6,p2,2).
puente(p7,p6,4).
puente(p7,p2,3).
puente(p8,p3,8).
puente(p8,p9,3).
puente(p9,p10,10).
puente(p10,p4,6).
puente(p10,p3,3).
puente(p11,p12,3).
puente(p11,p4,7).
puente(p12,p5,2).

% Predicado camino(S, Res)
% Encuentra un camino desde un planeta S hasta el centro galáctico c.
% Parámetros:
%   - S: Planeta de inicio.
%   - Res: Lista de planetas que forman el camino desde S hasta c.
% Función:
%   - Si existe un puente directo desde S hasta c, el camino es [S, c].
%   - Si no hay un puente directo, busca un puente hacia otro planeta X,
%     y recursivamente encuentra el camino desde X hasta c.

camino(S,[S | [c]]) :- puente(S,c,_).
camino(S, [S | T]) :-  puente(S,X,_), camino(X,T).




% Predicado combustible(S, N, Res)
% Encuentra un camino desde un planeta S hasta el centro galáctico c,
% asegurando que el combustible restante nunca sea menor a 0.
% Parámetros:
%   - S: Planeta de inicio.
%   - N: Cantidad inicial de combustible.
%   - Res: Lista de listas con el formato [Planeta, Combustible Restante]
%          que describe el camino desde S hasta c.
% Función:
%   - Si S es el centro galáctico c, el camino termina y el combustible restante es N.
%   - Si no, busca un puente desde S hacia otro planeta X, verifica que el combustible
%     sea suficiente para cruzar el puente, y continúa recursivamente desde X.


combustible(S,N,[[S,N]|Cola]) :- puente(S,X,Y), N >= Y , Nuevo_combustible is N - Y , combustible(X,Nuevo_combustible,Cola).
combustible(c,N,[[c,N]]) :- N >= 0.

