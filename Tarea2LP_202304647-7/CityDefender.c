#include <stdio.h>
#include "tablero.h"
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>
#include "cartas.h"
#include <stdlib.h> 



int tamano;
int contador_barco;
mano Cartas;


int main(int argc, char const *argv[] ){
    srand(time(NULL));
    
    printf("Selecciona la Dificultad: \n");
    printf("1. Facil -> 11X11, 5 Barcos \n");
    printf("2. Medio -> 17 X 17, 7 Barcos \n");
    printf("3. Dificil -> 21 X 21, 9 Barcos \n");
    int dificultad ;
    printf("Ingrese un numero: ");
    scanf("%d",&dificultad);
    int turno = 1;

    

    

    if(dificultad == 1){
        
        tamano = 11;
        contador_barco = 16;
        inicializar_tablero(tamano);
        tablero_facil(tablero,tamano);
        inicializarMano();
        //mostrarTablero(tamano);
        
        while (turno < 31 && contador_barco != 0){
            //printf("El contador de barcos es %d\n ",contador_barco);
            printf("Turno %d \n",turno);
            
            mostrarTablero(tamano);
            printf("Cartas: \n");
            mostraMano();
            usarCarta();


            turno++;
        }
        

        if(contador_barco != 0){
            printf("Perdiste, no lograste destruir a todos los barcos \n");
        }else{
            printf("Todos los barcos fueron destruidos\n");
        }
        
        printf("Tablero Final \n");    
        tablero_final(tablero,tamano);

    }else if(dificultad == 2){
        tamano = 17;
        contador_barco = 21;
        inicializar_tablero(tamano);
        tablero_medio(tablero,tamano);
        inicializarMano();
        while(turno < 26 && contador_barco != 0){
            printf("Turno %d \n",turno);
            mostrarTablero(tamano);
            printf("Cartas: \n");
            mostraMano();
            usarCarta();
            turno++;
        }

        if(contador_barco != 0){
            printf("Perdiste, no lograste destruir a todos los barcos \n");
        }else{
            printf("Todos los barcos fueron destruidos \n");
        }
        printf("Tablero Final \n"); 
        tablero_final(tablero,tamano);
    }else if(dificultad == 3){

        tamano = 21;
        contador_barco = 30;
        inicializar_tablero(tamano);
        tablero_dificil(tablero,tamano);
        inicializarMano();
        
        while(turno < 16 && contador_barco != 0){
            printf("Turno %d \n",turno);
            mostrarTablero(tamano);
            printf("Cartas: \n");
            mostraMano();
            usarCarta();
            turno++;
        }

        if(contador_barco != 0){
            printf("Perdiste, no lograste destruir a todos los barcos \n");
        }else{
            printf("Todos los barcos fueron destruidos \n");
        }
        
        printf("Tablero Final \n");
        tablero_final(tablero,tamano);
        
    }

    liberarTablero(tamano);
    liberarMano();
    
    
}