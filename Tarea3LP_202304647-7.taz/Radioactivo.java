import java.util.Scanner;
import java.util.Random;

public class Radioactivo extends Planeta {
    private int Uranio;
    private int Radiacion;

    // Constructor de la clase Radioactivo.
    // Inicializa el planeta con hidrógeno, sodio, energía y nivel de uranio, calculados en base al radio.
    public Radioactivo(){
        super();
        cambiar_Hidrogeno(Calcular_Hidrogeno(obtener_radio()));
        cambiar_Sodio(Calcular_Sodio(obtener_radio()));
        cambiar_Energia(Calcular_ConsumoDeEnergia(radiacion_aleatoria()));
        Cambiar_Uranio(obtener_radio());
    }
    // Método para generar un radio aleatorio para el planeta.
    // @return Un radio aleatorio entre 10,000 y 100,000.
    public int radio_aleatorio(){
        Random random = new Random();
        int min_radio = 10000;
        int max_radio = 100000;
        int radio_1 = random.nextInt(max_radio - min_radio + 1) + min_radio;
        return radio_1;
    }
    // Método para generar un nivel de radiación aleatorio para el planeta.
    // La radiación estará entre 10 y 50 µSv/h.
    // @return El nivel de radiación generado aleatoriamente.
    public int radiacion_aleatoria(){
        Random random = new Random();
        int min_rad = 10;
        int max_rad = 50;
        int radiacion = random.nextInt(max_rad - min_rad + 1) + min_rad;
        Radiacion = radiacion;
        
        return radiacion;
    }
    // Método para calcular la cantidad de hidrógeno en función del radio del planeta.
    // @param radio El radio del planeta.
    // @return La cantidad de hidrógeno disponible.
    public int Calcular_Hidrogeno(int radio){
        return (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
    }
    // Método para calcular la cantidad de sodio en función del radio del planeta.
    // @param radio El radio del planeta.
    // @return La cantidad de sodio disponible.
    public int Calcular_Sodio(int radio){
        return (int) (0.2 * (4 * Math.PI * Math.pow(radio, 2)));
    }
    // Método para calcular el consumo de energía necesario en función del nivel de radiación del planeta.
    // @param Radiacion El nivel de radiación del planeta.
    // @return El consumo de energía necesario.
    public int Calcular_ConsumoDeEnergia(int Radiacion){
        return (int)(0.3 * Radiacion);
    }
    // Método para calcular la cantidad de uranio en función del radio del planeta.
    // @param radio El radio del planeta.
    public void Cambiar_Uranio(int radio){
        Uranio = (int) (0.25 * (4 * Math.PI * Math.pow(radio, 2)));
    }
    // Método para mostrar una característica única del planeta.
    // En este caso, muestra el nivel de radiación del planeta.
    public void Mostrar_caracteristica_unica(){
        System.out.println("☢️ Nivel de radiación: " + Radiacion + " µSv/h");
    }
    // Método para obtener el nivel de radiación del planeta.
    // @return El nivel de radiación actual del planeta.
    public int obtener_radiacion(){
        return Radiacion;
    }
    // Método para obtener la cantidad de uranio disponible en el planeta.
    // @return La cantidad de uranio actual.
    public int obtener_uranio(){
        return Uranio;
    }
    // Método para mostrar la información del planeta.
    // Indica que el jugador se encuentra en un planeta radioactivo.
    public void obtener_info_del_planeta(){
        System.out.println("\n>> Estás en la superficie del planeta Radioactivo <<");
    }
    // Método que muestra las opciones de extracción de recursos adicionales
    public void interfaz_extraccion(){
        super.interfaz_extraccion();
        System.out.println("3. Uranio"  );
    }
    // Método para imprimir la información de los recursos del planeta.
    public void imprimir_info_recursos(){
        super.imprimir_info_recursos();
        System.out.println("⚛️ Uranio : " + Uranio);
    }
    // Método para extraer recursos adicionales del planeta.
    // @param Cantidad La cantidad de uranio a extraer.
    // @return La cantidad de uranio extraído.

    public int ExtraerRecursos2(int Cantidad){
        Uranio = Uranio - Cantidad;
        return Cantidad;
    }
    
}