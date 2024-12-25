import java.util.Scanner;
import java.util.Random;

public class Oceanico extends Planeta implements Tieneasentamientos{
    private int Profundidad;
    
    // Constructor de la clase Oceanico.
    // Inicializa el planeta con hidrógeno, sodio y energía, calculados en base al radio y profundidad.
    public Oceanico(){

        super();
        cambiar_Hidrogeno(Calcular_Hidrogeno(obtener_radio()));
        cambiar_Sodio(Calcular_Sodio(obtener_radio()));
        cambiar_Energia(Calcular_ConsumoDeEnergia(profundidad_aleatoria()));
    }
    // Método para generar un radio aleatorio para el planeta.
    // @return Un radio aleatorio entre 10,000 y 1,000,000.
    public int radio_aleatorio(){
        Random random = new Random();
        int min_radio = 10000;
        int max_radio = 1000000;
        int radio_1 = random.nextInt(max_radio - min_radio + 1) + min_radio;
        return radio_1;
    }
    // Método para generar una profundidad aleatoria para el planeta.
    // La profundidad estará entre 30 y 1,000 metros.
    // @return La profundidad generada aleatoriamente.
    public int profundidad_aleatoria(){
        Random random = new Random();
        int min_prof = 30;
        int max_prof = 1000;
        int profundidad = random.nextInt(max_prof - min_prof + 1) + min_prof;
        Profundidad = profundidad;
        return profundidad;
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
        return (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
    }
    // Método para calcular el consumo de energía necesario en función de la profundidad del planeta.
    // @param Profundidad La profundidad del planeta.
    // @return El consumo de energía necesario
    public int Calcular_ConsumoDeEnergia(int Profundidad){
        return (int)(0.002 *Math.pow(Profundidad, 2));
    }
    // Método para mostrar una característica única del planeta.
    // En este caso, muestra la profundidad del planeta.
    public void Mostrar_caracteristica_unica(){
        System.out.println("🌊 Profundidad estimada: " + Profundidad + " metros");
    }



    // Método para extraer recursos adicionales del planeta.
    // En el caso del planeta Oceánico, no se extraen recursos adicionales.
    // @param cantidad La cantidad de recursos solicitada.
    // @return Siempre retorna 0.
    public int ExtraerRecursos2(int cantidad){
        return 0;
    }


    // Método para obtener la profundidad del planeta.
    // @return La profundidad actual del planeta.
    public int obtener_profundidad(){
        return Profundidad;
    }
    // Método para mostrar la información del planeta.
    // Indica que el jugador se encuentra en un planeta oceánico.
    public void obtener_info_del_planeta(){
        System.out.println("\n>> Estás en la superficie del planeta Oceanico <<");
    }

    // Método que permite al jugador visitar los asentamientos en el planeta oceánico.
    // El jugador puede intercambiar recursos como uranio y platino para mejorar su exotraje o propulsores.
    // @param jugador El jugador que visita los asentamientos

    public void VisitarAsentamientos(Jugador jugador){
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        int decision;
        System.out.println("---------------------------------------------");
        System.out.println("Bienvenido al Asentamiento del Planeta Oceanico");
        System.out.println("Las opciones son las siguientes: ");
        System.out.println("1. Cada 10 Unidades de Uranio se mejora un 0.1 de Eficiencia en la Exotraje");
        System.out.println("2. Cada 10 Unidades de Platino se mejora un 0.1 de Eficiencia de los propulsores de su nave");
        System.out.println("3. No intercambiar nada");
        jugador.mostrar_inventario();
        System.out.println("Que Intercambio quieres realizar ?");
        decision = scanner.nextInt();
        System.out.println("---------------------------------------------");
        if(decision == 1){
            System.out.println("Ingrese la cantidad a intercambiar : ");
            cantidad = scanner.nextInt();
            if(jugador.Verificar_cantidad_Asentamiento(decision,cantidad)){
                jugador.mejorar_eficiencia_Exotraje(cantidad);
                jugador.quitar_cantidad_Asentamiento(decision, cantidad);
            }else{
                System.out.println("\n🚨 ¡Alerta, explorador! 🚨");
                System.out.println("No tienes suficientes recursos disponibles para completar esta acción.");
                System.out.println("💡 Intenta recolectar más en tu próxima misión o intercambia en un asentamiento.");
            }
        }else if( decision == 2){
            System.out.println("Ingrese la cantidad a intercambiar : ");
            cantidad = scanner.nextInt();
            if(jugador.Verificar_cantidad_Asentamiento(decision,cantidad)){
                jugador.mejorar_eficiencia_propulsor(cantidad);
                jugador.quitar_cantidad_Asentamiento(decision, cantidad);
            }else{
                System.out.println("\n🚨 ¡Alerta, explorador! 🚨");
                System.out.println("No tienes suficientes recursos disponibles para completar esta acción.");
                System.out.println("💡 Intenta recolectar más en tu próxima misión o intercambia en un asentamiento.");
            }
        }
    }
}
