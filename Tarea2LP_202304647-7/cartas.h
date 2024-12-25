typedef struct mano{
    void** carta;
    int disponibles;
}mano;


extern mano Cartas;
void inicializarMano();
void mostraMano();
void usarCarta();
void liberarMano();
extern int tamano;
extern int contador_barco;

void *disparoSimple(int x, int y);
void *disparoGrande(int x, int y);
void *disparoLineal(int x, int y);
void *disparoRadar(int x, int y);
void *disparo500KG(int x, int y);

