import java.util.Scanner;
import java.util.Random;

public class Volcanico extends Planeta {
    private int Platino;
    private int Temperatura;



    // Constructor de la clase Volcanico.
    // Inicializa el planeta con hidrógeno, sodio y energía, calculados en base al radio y temperatura.
    public Volcanico(){
        super();
        cambiar_Hidrogeno(Calcular_Hidrogeno(obtener_radio()));
        cambiar_Sodio(Calcular_Sodio(obtener_radio()));
        cambiar_Energia(Calcular_ConsumoDeEnergia(temperatura_aleatoria()));
        Cambiar_Platino(obtener_radio());
    }
    // Método para generar un radio aleatorio para el planeta.
    // @return Un radio aleatorio entre 1,000 y 100,000
    public int radio_aleatorio(){
        Random random = new Random();
        int min_radio = 1000;
        int max_radio = 100000;
        int radio_1 = random.nextInt(max_radio - min_radio + 1) + min_radio;
        return radio_1;
    }
    // Método para generar una temperatura aleatoria para el planeta.
    // La temperatura estará entre 120 °C y 256 °C.
    // @return La temperatura generada aleatoriamente.
    public int temperatura_aleatoria(){
        Random random = new Random();
        int min_temp = 120;
        int max_temp = 256;
        int temp = random.nextInt(max_temp - min_temp + 1) + min_temp;
        Temperatura = temp;
        
        return temp;
    }
    // Método para calcular la cantidad de hidrógeno en función del radio del planeta.
    // @param radio El radio del planeta.
    // @return La cantidad de hidrógeno disponible.
    public int Calcular_Hidrogeno(int radio){
        return (int) (0.3 * (4 * Math.PI * Math.pow(radio, 2)));
    }
     // Método para calcular la cantidad de sodio en función del radio del planeta.
    // En este caso, siempre retorna 0 ya que no hay sodio disponible en el planeta volcánico.
    // @param radio El radio del planeta.
    // @return La cantidad de sodio disponible, siempre 0.
    public int Calcular_Sodio(int radio){
        return 0;
    }
    // Método para calcular el consumo de energía necesario en función de la temperatura del planeta.
    // @param temperatura La temperatura del planeta.
    // @return El consumo de energía necesario.
    public int Calcular_ConsumoDeEnergia(int temperatura){
        return Math.abs((int)(0.08 * temperatura));
    }
    // Método para calcular la cantidad de platino en función del radio y temperatura del planeta.
    // @param radio El radio del planeta.
    public void Cambiar_Platino (int radio){
        Platino = (int) (0.25 * (4 * Math.PI * Math.pow(radio, 2)) - (20.5 * Math.pow(Temperatura, 2)));
    }
    // Método para mostrar una característica única del planeta.
    // En este caso, muestra la temperatura superficial del planeta.
    public void Mostrar_caracteristica_unica(){
        System.out.println("🔥 Temperatura superficial: " + Temperatura + " °C");
    }

    // Método para obtener la temperatura del planeta.
    // @return La temperatura actual del planeta.
    public int obtener_temperatura(){
        return Temperatura;
    }
    // Método para obtener la cantidad de platino disponible en el planeta.
    // @return La cantidad de platino actual
    public int obtener_platino(){
        return Platino;

    }
    // Método para mostrar la información del planeta.
    // Indica que el jugador se encuentra en un planeta volcánico.
    public void obtener_info_del_planeta(){
        System.out.println("\n>> Estás en la superficie del planeta Volcanico <<");
    }
    // Método que muestra las opciones de extracción de recursos adicionales.
    public void interfaz_extraccion(){
        super.interfaz_extraccion();
        System.out.println("3. Platino");
    }
    // Método para imprimir la información de los recursos del planeta.
    public void imprimir_info_recursos(){
        super.imprimir_info_recursos();
        System.out.println("Platino : " + Platino);
    }
    // Método para extraer recursos adicionales del planeta.
    // @param Cantidad La cantidad de platino a extraer.
    // @return La cantidad de platino extraído.

    public int ExtraerRecursos2(int Cantidad){
        Platino = Platino - Cantidad;
        return Cantidad;
    }
}