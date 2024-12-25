#include <stdio.h>
#include "tablero.h"
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>
#include "cartas.h"



void ***tablero;

/**
 * Inicializa el tablero con un tamaño especificado.
 * Cada celda del tablero se inicializa con una cadena constante " ".
 * 
 * parametro: entero tamano (Tamaño del tablero (número de filas y columnas).)
 */

void inicializar_tablero(int tamano){
    
    tablero = (void ***) malloc(tamano * sizeof(void **));

    for (int i = 0; i < tamano; i++) {
        tablero[i] = (void **) malloc(tamano * sizeof(void *));
        for (int j = 0; j < tamano; j++) {
            tablero[i][j] = " ";
        }
    }

}

/**
 * Muestra el tablero en la consola.
 * 
 * parametro: entero tamano (Tamaño del tablero (número de filas y columnas))
 */

void mostrarTablero(int tamano){
    for(int i = 0; i < tamano; i++){
        for(int j = 0; j < tamano; j++){
            if(strcmp(tablero[i][j],"X") == 0){
                printf("|%s", (char *)tablero[i][j]);
            } else if(strcmp(tablero[i][j]," ") == 0){
                printf("|%s", (char *)tablero[i][j]);
            }else if(strcmp(tablero[i][j],"B") == 0){
                printf("| ");
            }else if(strcmp(tablero[i][j],"O")== 0){
                printf("|%s",(char*)tablero[i][j]);
            } 
        }
        printf("|\n");  
    }
}

/**
 * Genera una orientación aleatoria para un barco.
 * 
 * return : Una cadena que indica la orientación del barco ("Vertical" o "Horizontal").
 */
char * orientacion_barco(){
    char * orientacion;
    int random_number = rand() % 2;

    if(random_number == 0){
        orientacion = "Vertical";
    }else{
        orientacion = "Horizontal";
    }
    return orientacion;
}

/**
 * Genera una coordenada aleatoria dentro de un tablero de tamaño especificado.
 * 
 * parametro: tamano (Tamaño del tablero (número de filas y columnas)).
 * return:  Un puntero a un arreglo de dos enteros que representan la coordenada (x, y).
 */
int* coordenada_aleatoria(int tamano){
    int* coordenada = malloc(2 * sizeof(int));
    coordenada[0] = rand() % tamano;
    coordenada[1] = rand() % tamano;
    return coordenada;
}

/**
 * Verifica si una coordenada y orientación dadas son válidas para colocar un barco en el tablero.
 * 
 * parametro: arreglo_barcos - Cadena que representa el tamaño del barco (ej. "1X3").
 * parametro: coordenada - Puntero a un arreglo de dos enteros que representan la coordenada (x, y).
 * parametro: orientacion - Cadena que indica la orientación del barco ("Vertical" o "Horizontal").
 * parametro: tamano - Tamaño del tablero (número de filas y columnas).
 * return: 1 si la posición es válida, 0 si no es válida.(entero)
 */

int posicion_valida(char * arreglo_barcos,int* coordenada, char * orientacion,int tamano){
        
        int limite_y = arreglo_barcos[2] - '0';
        int bandera;
        if(strcmp(orientacion,"Vertical") == 0){
            if (coordenada[1] + limite_y -1 < tamano){
                
                bandera = 1;
                
               
            }else{
                bandera = 0;
                
            }
        }
        if(strcmp(orientacion,"Horizontal") == 0){
            if(coordenada[0] + limite_y  - 1< tamano){
                bandera = 1;    
            }else {
                bandera = 0;
                
            }
        }
        
        return bandera;
}

/**
 * Inserta un barco en el tablero en una posición y orientación dadas.
 * 
 * parametro: tablero - Puntero al tablero donde se insertará el barco.
 * parametro: arreglo_barcos - Cadena que representa el tamaño del barco (ej. "1X3").
 * parametro: coordenada-  Puntero a un arreglo de dos enteros que representan la coordenada (x, y).
 * parametro: orientacion -  Cadena que indica la orientación del barco ("Vertical" o "Horizontal").
 * return 1 si el barco se inserta exitosamente, 0 si no se puede insertar.(entero)
 */
int insertar_barco(void ***tablero,char * arreglo_barcos,int * coordenada, char* orientacion){
    int limite = arreglo_barcos[2] - '0';
    int contador = 0;
    
    if(strcmp(orientacion,"Horizontal") == 0){

        if (coordenada[0] + limite > tamano) {
            return 0;  
        }

        while (contador < limite){
           
            if (strcmp(tablero[coordenada[0]+contador][coordenada[1]]," ") == 0){
                
                tablero[coordenada[0]+contador][coordenada[1]] = "B";
                contador = contador + 1;
            }else{
                return 0;
                
            }
        }
        
    }
    if(strcmp(orientacion,"Vertical") == 0){

        if (coordenada[1] + limite > tamano) {
            return 0;  // El barco se sale del tablero
        }

        while (contador < limite){
            //agregar coordenada al tablero
            if (strcmp(tablero[coordenada[0]][coordenada[1]+contador]," ") == 0){ //era con A
                tablero[coordenada[0]][coordenada[1]+contador] = "B";
                contador = contador + 1;
            }else{
                return 0;
                
            }
        }
    }

    return 1 ;
    
}

/**
 * Coloca barcos en el tablero en una configuración fácil.
 * 
 * parametro: tablero - Puntero al tablero donde se colocarán los barcos.
 * parametro: tamano - Tamaño del tablero (número de filas y columnas).
 */


void tablero_facil(void ***tablero,int tamano){
    char* arreglo_barcos[5];
    arreglo_barcos[0] = "1X2";
    arreglo_barcos[1] = "1X2";
    arreglo_barcos[2] = "1X3";
    arreglo_barcos[3] = "1X4";
    arreglo_barcos[4] = "1X5";

    for(int i = 0; i < 5; i++){
        //generar la orientacion del barco
        //printf("Este es el barco analiza %s \n ", arreglo_barcos[i]);
        char * orientacion;
        orientacion = orientacion_barco();

        // obtener coordenada de inicio
        int* coordenada; 
        coordenada = coordenada_aleatoria(tamano);
        //printf("La coordenada es (%d,%d) \n", coordenada[0],coordenada[1]);
        //validar que este en los limites del tablero
        int valido = posicion_valida(arreglo_barcos[i],coordenada,orientacion,tamano);
        /*
        if(valido == 1){
            printf("Es valido la coordenada \n");
        }else{
            printf("no es valido la coordenada \n");
        }
        */
        while(valido == 0){
            free(coordenada);
            orientacion = orientacion_barco();
            coordenada = coordenada_aleatoria(tamano);
            valido = posicion_valida(arreglo_barcos[i],coordenada,orientacion,tamano);
            
        }
        //verificar si existe colisiones
        // valido = 0 o 1
        valido = insertar_barco(tablero,arreglo_barcos[i],coordenada,orientacion);
        //printf("el valido es %d ", valido);
        while(valido == 0){
            free(coordenada);
            orientacion = orientacion_barco();
            coordenada = coordenada_aleatoria(tamano);
            valido = insertar_barco(tablero,arreglo_barcos[i],coordenada,orientacion);
        }
        //mostrarTablero(tamano);

        free(coordenada);
    }
}
/**
 * Coloca barcos en el tablero en una configuración media.
 * 
 * parametro: tablero - Puntero al tablero donde se colocarán los barcos.
 * parametro: tamano - Tamaño del tablero (número de filas y columnas).
 */

void tablero_medio(void ***tablero,int tamano){
    char* arreglo_barcos[7];
    arreglo_barcos[0] = "1X2";
    arreglo_barcos[1] = "1X2";
    arreglo_barcos[2] = "1X2";
    arreglo_barcos[3] = "1X3";
    arreglo_barcos[4] = "1X3";
    arreglo_barcos[5] = "1X4";
    arreglo_barcos[6] = "1X5";

    for(int i = 0; i < 7; i++){
        //generar la orientacion del barco
        //printf("Este es el barco analiza %s \n ", arreglo_barcos[i]);
        char * orientacion;
        orientacion = orientacion_barco();

        // obtener coordenada de inicio
        int* coordenada; 
        coordenada = coordenada_aleatoria(tamano);
        //printf("El x es %d y el y es %d \n", coordenada[0],coordenada[1]);
        //validar que este en los limites del tablero
        int valido = posicion_valida(arreglo_barcos[i],coordenada,orientacion,tamano);
        /*
        if(valido == 1){
            printf("Es valido la coordenada \n");
        }else{
            printf("no es valido la coordenada \n");
        }
        */
        while(valido == 0){
            free(coordenada);
            orientacion = orientacion_barco();
            coordenada = coordenada_aleatoria(tamano);
            valido = posicion_valida(arreglo_barcos[i],coordenada,orientacion,tamano);
        }
        //verificar si existe colisiones
        // valido = 0 o 1
        valido = insertar_barco(tablero,arreglo_barcos[i],coordenada,orientacion);
        while(valido == 0){
            free(coordenada);
            orientacion = orientacion_barco();
            coordenada = coordenada_aleatoria(tamano);
            valido = insertar_barco(tablero,arreglo_barcos[i],coordenada,orientacion);
        }
        //mostrarTablero(tamano);
        free(coordenada);
    }
}

/**
 * Coloca barcos en el tablero en una configuración dificil.
 * 
 * parametro: tablero - Puntero al tablero donde se colocarán los barcos.
 * parametro: tamano - Tamaño del tablero (número de filas y columnas).
 */

void tablero_dificil(void ***tablero,int tamano){
    char* arreglo_barcos[9];
    arreglo_barcos[0] = "1X2";
    arreglo_barcos[1] = "1X2";
    arreglo_barcos[2] = "1X2";
    arreglo_barcos[3] = "1X3";
    arreglo_barcos[4] = "1X3";
    arreglo_barcos[5] = "1X4";
    arreglo_barcos[6] = "1X4";
    arreglo_barcos[7] = "1X5";
    arreglo_barcos[8] = "1X5";

    for(int i = 0; i < 9; i++){
        //generar la orientacion del barco
        //printf("Este es el barco analiza %s \n ", arreglo_barcos[i]);
        char * orientacion;
        orientacion = orientacion_barco();

        // obtener coordenada de inicio
        int* coordenada; 
        coordenada = coordenada_aleatoria(tamano);
        //printf("El x es %d y el y es %d \n", coordenada[0],coordenada[1]);
        //validar que este en los limites del tablero
        int valido = posicion_valida(arreglo_barcos[i],coordenada,orientacion,tamano);
        /*
        if(valido == 1){
            printf("Es valido la coordenada \n");
        }else{
            printf("no es valido la coordenada \n");
        }
        */
        while(valido == 0){
            free(coordenada);
            orientacion = orientacion_barco();
            coordenada = coordenada_aleatoria(tamano);
            valido = posicion_valida(arreglo_barcos[i],coordenada,orientacion,tamano);
        }
        //verificar si existe colisiones
        // valido = 0 o 1
        valido = insertar_barco(tablero,arreglo_barcos[i],coordenada,orientacion);
        while(valido == 0){
            free(coordenada);
            orientacion = orientacion_barco();
            coordenada = coordenada_aleatoria(tamano);
            valido = insertar_barco(tablero,arreglo_barcos[i],coordenada,orientacion);
        }
        //mostrarTablero(tamano);
        free(coordenada);
    }
}

/**
 * Muestra el tablero con las posiciones de los botes.
 * 
 * parametro: tablero - Puntero al tablero que se mostrará.
 * parametro: tamano -  Tamaño del tablero (número de filas y columnas).
**/
void tablero_final(void *** tablero, int tamano){
    for(int i = 0; i < tamano; i++){
        for(int j = 0; j < tamano; j++){
            if(strcmp(tablero[i][j],"X") == 0){
                printf("|%s", (char *)tablero[i][j]);
            } else if(strcmp(tablero[i][j]," ") == 0){
                printf("|%s", (char *)tablero[i][j]);
            }else if(strcmp(tablero[i][j],"B") == 0){
                printf("|X");
            }else if(strcmp(tablero[i][j],"O")== 0){
                printf("| ");
            }

            
            //printf("|%s|", (char *)tablero[i][j]);
        }
        printf("\n");  // Salto de línea después de cada fila
    }    
}
/**
 * Libera la memoria asignada para el tablero.
 * 
 * parametro: tamano - Tamaño del tablero (número de filas y columnas).
 */

void liberarTablero(int tamano) {
    for (int i = 0; i < tamano; i++) {
        free(tablero[i]);  // Luego liberar cada fila
    }
    free(tablero);  // Finalmente, liberar el tablero completo

}


