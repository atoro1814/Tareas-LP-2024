#include "cartas.h"
#include "tablero.h"
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h> 


int valido;
//mano Cartas;

/**
 * Verifica si las coordenadas (x, y) están dentro del tablero.
 * 
 * parametro: tamano - Tamaño del tablero (número de filas y columnas).
 * parametro: x - Coordenada x para verificar.
 * parametro: y - Coordenada y para verificar.
 * 
 * devuelve: 1 si las coordenadas están dentro del tablero, 0 en caso contrario.
 */


int verificar_disparo(int tamano,int x,int y){
    if(y < 0){
        return 0;
    }
    if(x < 0){
        return 0;
    }
    if(y >= tamano){
        return 0;
    }
    if (x >= tamano)
    {
        return 0;
    }
    return 1;
}

/**
 * Realiza un disparo en la coordenada (x, y) y marca el impacto en el tablero.
 * 
 * parametro: x - Coordenada x donde se realiza el disparo.
 * parametro: y - Coordenada y donde se realiza el disparo.
 */
void disparo_barco(int x, int y){
    if(strcmp(tablero[y][x],"B") == 0){
        tablero[y][x] = "X";
        contador_barco--;
    }else if(strcmp(tablero[y][x]," ") == 0){
        tablero[y][x] = "O";
    }
}

/**
 * Ejecuta el disparo simple en la coordenada (x, y) y determina el tipo de disparo siguiente.
 * 
 * parametro: x - Coordenada x donde se realiza el disparo.
 * parametro: y - Coordenada y donde se realiza el disparo.
 * 
 * devuelve: Un puntero a la función correspondiente al siguiente tipo de disparo.
 */


void* disparoSimple(int x, int y){
    x = x-1;
    y = y-1;
    disparo_barco(x,y);
    

    int numero_aleatorio = rand() % 100;
    //printf("El numero es %d ", numero_aleatorio);

    if(numero_aleatorio < 65){
        return disparoSimple;
    }
    if (numero_aleatorio < 85){
        return disparoGrande;
    }
    if (numero_aleatorio < 90){
        return disparoLineal;
    }
    if (numero_aleatorio < 100){
        return disparoRadar;
    }

    return NULL;
}

/**
 * Ejecuta el disparo grande en la coordenada (x, y) y determina el tipo de disparo siguiente.
 * 
 * parametro: x - Coordenada x donde se realiza el disparo.
 * parametro: y - Coordenada y donde se realiza el disparo.
 * 
 * devuelve: Un puntero a la función correspondiente al siguiente tipo de disparo.
 */

void * disparoGrande(int x , int y){
    x = x-1;
    y = y-1;
    
    //printf("(%d,%d) coordenada original\n",x,y);

    

    for (int i = -1; i < 2; i++) {
        int x_evaluar = x + i;  // Evaluamos el desplazamiento en x
        
        for (int j = -1; j < 2; j++) {
            int y_evaluar = y + j;  // Evaluamos el desplazamiento en y

            //printf("Evaluando (%d,%d)\n", x_evaluar, y_evaluar);
            
            //tablero[y_evaluar][x_evaluar] = "X";  // Marcamos el impacto
            valido = 1;
            //printf("%d el tamaño es: ",tamano);
            valido = verificar_disparo(tamano,y_evaluar,x_evaluar);

            //printf("%d valido \n", valido);
            if(valido == 1){
                disparo_barco(x_evaluar,y_evaluar);  // Marcamos el impacto
                
            }

            

        }
    }





    int numero_aleatorio = rand() % 100;
    //printf("El numero es %d ", numero_aleatorio);

    if(numero_aleatorio < 80){
        return disparoSimple;
    }
    if (numero_aleatorio < 83){
        return disparoGrande;
    }
    if (numero_aleatorio < 93){
        return disparoLineal;
    }
    if (numero_aleatorio < 98){
        return disparoRadar;
    }
    if (numero_aleatorio <=100){
        return disparo500KG;
    }

    return NULL;
}

/**
 * Ejecuta el disparo lineal en la coordenada (x, y) según la orientación seleccionada.
 * 
 * parametro: x - Coordenada x donde se realiza el disparo.
 * parametro: y - Coordenada y donde se realiza el disparo.
 * 
 * devuelve: Un puntero a la función correspondiente al siguiente tipo de disparo.
 */

void * disparoLineal(int x , int y){
    printf("Eliga la orientacion del barco lineal: 1X5 o 5X1 \n ");
    char orientacion_disparo_1X5[5] ;
    scanf("%s",orientacion_disparo_1X5);

    x = x-1;
    y = y - 1;



    

    if (strcmp(orientacion_disparo_1X5,"1X5") == 0){

        for(int i = -2;i < 3; i++){
            int x_evaluar = x + i;
            valido = verificar_disparo(tamano,x_evaluar,y);
            if(valido == 1){
                //tablero[y][x_evaluar] = "X";  // Marcamos el impacto
                disparo_barco(x_evaluar,y);
                
            }


        }
        //tablero[y][x-2] = "X";
        //tablero[y][x-1] = "X";
        //tablero[y][x] = "X";
        //tablero[y][x+1] = "X";
        //tablero[y][x+2] = "X";

    }
    if (strcmp(orientacion_disparo_1X5,"5X1")== 0){

        for(int i = -2;i < 3; i++){
            int y_evaluar = y + i;
            valido = verificar_disparo(tamano,x,y_evaluar);
            if(valido == 1){
                //tablero[y_evaluar][x] = "X";  // Marcamos el impacto
                disparo_barco(x,y_evaluar);
               
            }
        }

        //tablero[y-2][x] = "X";
        //tablero[y-1][x] = "X";
        //tablero[y][x] = "X";
        //tablero[y+1][x] = "X";
        //tablero[y+2][x] = "X";

    }
    int numero_aleatorio = rand() % 100;
    //printf("El numero es %d ", numero_aleatorio);

    if(numero_aleatorio < 85){
        return disparoSimple;
    }
    if (numero_aleatorio < 90){
        return disparoGrande;
    }
    if (numero_aleatorio < 92){
        return disparoLineal;
    }
    if (numero_aleatorio < 98){
        return disparoRadar;
    }
    if (numero_aleatorio<=100){
        return disparo500KG;
    }

    return NULL;

}
/**
 * Ejecuta el disparo radar en la coordenada (x, y) y proporciona un mensaje sobre barcos cercanos.
 * 
 * parametro: x - Coordenada x donde se realiza el disparo.
 * parametro: y - Coordenada y donde se realiza el disparo.
 * 
 * devuelve: Un puntero a la función correspondiente al siguiente tipo de disparo.
 */

void * disparoRadar(int x, int y){
    
    int mensaje;
    int valido;
    x = x-1;
    y = y - 1;
    for (int i = -1; i < 2; i++) {
        int x_evaluar = x + i;  // Evaluamos el desplazamiento en x
        
        for (int j = -1; j < 2; j++) {
            int y_evaluar = y + j;  // Evaluamos el desplazamiento en y

            //printf("Evaluando (%d,%d)\n", x_evaluar, y_evaluar);
            
            //tablero[y_evaluar][x_evaluar] = "X";  // Marcamos el impacto
            valido = 1;
            //printf("%d el tamaño es: ",tamano);
            valido = verificar_disparo(tamano,y_evaluar,x_evaluar);

            //printf("%d valido \n", valido);
            if(valido == 1){
                if(strcmp(tablero[y_evaluar][x_evaluar],"B")== 0){
                    mensaje = 1;
                } 
            }

        }
    }

    if (mensaje == 1){
        printf("Hay botes cercanos \n");
    }else{
        printf("No hay botes cercanos \n");
    }

    int numero_aleatorio = rand() % 100;
    //printf("El numero es %d ", numero_aleatorio);

    if(numero_aleatorio < 75){
        return disparoSimple;
    }
    if (numero_aleatorio < 90){
        return disparoGrande;
    }
    if (numero_aleatorio < 95){
        return disparoLineal;
    }
    if (numero_aleatorio < 97){
        return disparoRadar;
    }
    if (numero_aleatorio<=3){
        return disparo500KG;
    }    
    
    return NULL;


}

/**
 * Ejecuta el disparo de 500KG en la coordenada (x, y), afectando una gran área alrededor del punto.
 * 
 * parametro: x - Coordenada x donde se realiza el disparo.
 * parametro: y - Coordenada y donde se realiza el disparo.
 * 
 * devuelve: NULL.
 */

void * disparo500KG(int x, int y){
    x = x-1;
    y = y - 1;

    for (int i = -5; i < 6; i++) {
        int x_evaluar = x + i;  // Evaluamos el desplazamiento en x
        
        for (int j = -5; j < 6; j++) {
            int y_evaluar = y + j;  // Evaluamos el desplazamiento en y

            //printf("Evaluando (%d,%d)\n", x_evaluar, y_evaluar);
            
            //tablero[y_evaluar][x_evaluar] = "X";  // Marcamos el impacto
            valido = 1;
            //printf("%d el tamaño es: ",tamano);
            valido = verificar_disparo(tamano,y_evaluar,x_evaluar);

            //printf("%d valido \n", valido);
            if(valido == 1){
                //tablero[y_evaluar][x_evaluar] = "X";  // Marcamos el impacto
                disparo_barco(x_evaluar,y_evaluar);
                
            }
        }
    }

    return NULL;
}

/**
 * Inicializa la mano de cartas con cinco cartas de tipo disparoSimple.
 */
void inicializarMano(){
    Cartas.carta = malloc(5 * sizeof(void*));
    for (int i = 0; i < 5; i++) {
        Cartas.carta[i] = disparoSimple;  
    }
    Cartas.disponibles = 5;
}
/**
 * Muestra las cartas disponibles en la mano.
 */

void mostraMano(){
    
    for (int i = 0; i < 5; i++) {
        if (Cartas.carta[i] == disparoSimple) {
            printf(" Disparo Simple |");
        } else if (Cartas.carta[i] == disparoGrande) {
            printf(" Disparo Grande |");
        } else if (Cartas.carta[i] == disparoLineal) {
            printf(" Disparo Lineal |");
        } else if (Cartas.carta[i] == disparoRadar) {
            printf(" Disparo Radar |");
        } else if (Cartas.carta[i] == disparo500KG) {
            printf(" Disparo 500KG |");
        }else if(Cartas.carta[i] == NULL){
            printf(" |");
        }
    }
    printf("\n");
}
/**
 * Utiliza una carta seleccionada por el usuario para realizar un disparo.
 * 
 * Solicita al usuario que seleccione una carta y unas coordenadas (x, y). Luego,
 * ejecuta la función correspondiente al tipo de carta seleccionada y actualiza 
 * la carta en la mano con la función resultante.
 */


void usarCarta(){
    
    int eleccion_carta;
    printf("Seleccione una Carta:");
    scanf("%d",&eleccion_carta);
    
    
    int coor_x;
    int coor_y;
    printf("X: ");
    scanf("%d", &coor_x); // Elimina el salto de línea

    printf("Y: ");
    scanf("%d", &coor_y); // Elimina el salto de línea


    if(eleccion_carta == 1){
        if (Cartas.carta[0] == disparoSimple) {
            void * retorno_funcion = disparoSimple(coor_x,coor_y);
            Cartas.carta[0] = retorno_funcion;
        } else if (Cartas.carta[0] == disparoGrande) {
            void * retorno_funcion = disparoGrande(coor_x,coor_y);
            Cartas.carta[0] = retorno_funcion;
        } else if (Cartas.carta[0] == disparoLineal) {
            void * retorno_funcion = disparoLineal(coor_x,coor_y);
            Cartas.carta[0] = retorno_funcion;
        } else if (Cartas.carta[0] == disparoRadar) {
            void * retorno_funcion = disparoRadar(coor_x,coor_y);
            Cartas.carta[0] = retorno_funcion;
        } else if (Cartas.carta[0] == disparo500KG) {
            void * retorno_funcion = disparo500KG(coor_x,coor_y);
            Cartas.carta[0] = retorno_funcion;
        }
    
    }else if (eleccion_carta == 2){
        if (Cartas.carta[1] == disparoSimple) {
            void * retorno_funcion = disparoSimple(coor_x,coor_y);
            Cartas.carta[1] = retorno_funcion;
        } else if (Cartas.carta[1] == disparoGrande) {
            void * retorno_funcion = disparoGrande(coor_x,coor_y);
            Cartas.carta[1] = retorno_funcion;
        } else if (Cartas.carta[1] == disparoLineal) {
            void * retorno_funcion = disparoLineal(coor_x,coor_y);
            Cartas.carta[1] = retorno_funcion;
        } else if (Cartas.carta[1] == disparoRadar) {
            void * retorno_funcion = disparoRadar(coor_x,coor_y);
            Cartas.carta[1] = retorno_funcion;
        } else if (Cartas.carta[1] == disparo500KG) {
            void * retorno_funcion = disparo500KG(coor_x,coor_y);
            Cartas.carta[1] = retorno_funcion;
        }        
    }else if(eleccion_carta == 3){
        if (Cartas.carta[2] == disparoSimple) {
            void * retorno_funcion = disparoSimple(coor_x,coor_y);
            Cartas.carta[2] = retorno_funcion;
        } else if (Cartas.carta[2] == disparoGrande) {
            void * retorno_funcion = disparoGrande(coor_x,coor_y);
            Cartas.carta[2] = retorno_funcion;
        } else if (Cartas.carta[2] == disparoLineal) {
            void * retorno_funcion = disparoLineal(coor_x,coor_y);
            Cartas.carta[2] = retorno_funcion;
        } else if (Cartas.carta[2] == disparoRadar) {
            void * retorno_funcion = disparoRadar(coor_x,coor_y);
            Cartas.carta[2] = retorno_funcion;
        } else if (Cartas.carta[2] == disparo500KG) {
            void * retorno_funcion = disparo500KG(coor_x,coor_y);
            Cartas.carta[2] = retorno_funcion;
        } 
    }else if (eleccion_carta == 4){
        if (Cartas.carta[3] == disparoSimple) {
            void * retorno_funcion = disparoSimple(coor_x,coor_y);
            Cartas.carta[3] = retorno_funcion;
        } else if (Cartas.carta[3] == disparoGrande) {
            void * retorno_funcion = disparoGrande(coor_x,coor_y);
            Cartas.carta[3] = retorno_funcion;
        } else if (Cartas.carta[3] == disparoLineal) {
            void * retorno_funcion = disparoLineal(coor_x,coor_y);
            Cartas.carta[3] = retorno_funcion;
        } else if (Cartas.carta[3] == disparoRadar) {
            void * retorno_funcion = disparoRadar(coor_x,coor_y);
            Cartas.carta[3] = retorno_funcion;
        } else if (Cartas.carta[3] == disparo500KG) {
            void * retorno_funcion = disparo500KG(coor_x,coor_y);
            Cartas.carta[3] = retorno_funcion;
        }
    }else if(eleccion_carta == 5){
        if (Cartas.carta[4] == disparoSimple) {
            void * retorno_funcion = disparoSimple(coor_x,coor_y);
            Cartas.carta[4] = retorno_funcion;
        } else if (Cartas.carta[4] == disparoGrande) {
            void * retorno_funcion = disparoGrande(coor_x,coor_y);
            Cartas.carta[4] = retorno_funcion;
        } else if (Cartas.carta[4] == disparoLineal) {
            void * retorno_funcion = disparoLineal(coor_x,coor_y);
            Cartas.carta[4] = retorno_funcion;
        } else if (Cartas.carta[4] == disparoRadar) {
            void * retorno_funcion = disparoRadar(coor_x,coor_y);
            Cartas.carta[4] = retorno_funcion;
        } else if (Cartas.carta[4] == disparo500KG) {
            void * retorno_funcion = disparo500KG(coor_x,coor_y);
            Cartas.carta[4] = retorno_funcion;
        }
    }
}

// Libera la memoria asignada para Cartas.carta

void liberarMano() {
    
    free(Cartas.carta);
    
}



