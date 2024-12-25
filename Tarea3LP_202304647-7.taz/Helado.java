import java.util.Scanner;
import java.util.Random;

public class Helado extends Planeta implements Tieneasentamientos{
    private int Temperatura;
    // Constructor de la clase Helado.
    // Inicializa el planeta con hidrógeno, sodio y energía, calculados en base al radio y temperatura.
    public Helado(){
        
        
        super();
        cambiar_Hidrogeno(Calcular_Hidrogeno(obtener_radio()));
        cambiar_Sodio(Calcular_Sodio(obtener_radio()));
        cambiar_Energia(Calcular_ConsumoDeEnergia(temperatura_aleatoria()));

    }
    // Método para generar un radio aleatorio para el planeta.
    // @return Un radio aleatorio entre 1000 y 1000000
    public int radio_aleatorio(){
        Random random = new Random();
        int min_radio = 1000;
        int max_radio = 1000000;
        int radio_1 = random.nextInt(max_radio - min_radio + 1) + min_radio;
        return radio_1;
    }
    // Método para generar una temperatura aleatoria para el planeta.
    // La temperatura estará entre -120°C y -30°C.
    // @return La temperatura generada aleatoriamente.
    public int temperatura_aleatoria(){
        Random random = new Random();
        int min_temp = -120;
        int max_temp = -30;
        int temp = random.nextInt(max_temp - min_temp + 1) + min_temp;
        Temperatura = temp;
       
        return temp;
    }
    // Método para calcular la cantidad de hidrógeno en función del radio del planeta.
    // @param radio El radio del planeta.
    // @return La cantidad de hidrógeno disponible.
    public int Calcular_Hidrogeno(int radio){
        return (int) (0.65 * (4 * Math.PI * Math.pow(radio, 2)));
    }
    // Método para calcular la cantidad de sodio en función del radio del planeta.
    // @param radio El radio del planeta.
    // @return La cantidad de sodio disponible.
    public int Calcular_Sodio(int radio){
        return (int) (0.35 * (4 * Math.PI * Math.pow(radio, 2)));
    }
    // Método para calcular el consumo de energía necesario en función de la temperatura del planeta.
    // @param temperatura La temperatura del planeta.
    // @return El consumo de energía necesario.
    public int Calcular_ConsumoDeEnergia(int temperatura){
        return Math.abs((int) (0.15 * temperatura));
    }
    // Método para mostrar una característica única del planeta.
    // En este caso, muestra la temperatura superficial del planeta.
    public void Mostrar_caracteristica_unica(){
        System.out.println("🔥 Temperatura superficial: " + Temperatura + " °C");
    }
    // Método que permite al jugador visitar los asentamientos en el planeta helado.
    // El jugador puede intercambiar recursos como uranio y platino para mejorar su exotraje o propulsores.
    // @param jugador El jugador que visita los asentamientos.
    public void VisitarAsentamientos(Jugador jugador){
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        int decision;
        System.out.println("---------------------------------------------");
        System.out.println("Bienvenido al Asentamiento del Planeta Helado");
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
            if(jugador.Verificar_cantidad_Asentamiento(decision, cantidad)){
                jugador.mejorar_eficiencia_propulsor(cantidad);
                jugador.quitar_cantidad_Asentamiento(decision, cantidad);
            }else{
                System.out.println("\n🚨 ¡Alerta, explorador! 🚨");
                System.out.println("No tienes suficientes recursos disponibles para completar esta acción.");
                System.out.println("💡 Intenta recolectar más en tu próxima misión o intercambia en un asentamiento.");  
            }
        }
        
    }
    // Método para obtener la temperatura del planeta.
    // @return La temperatura actual del planeta
    public int obtener_temperatura(){
        return Temperatura;
    }
    // Método para mostrar la información del planeta.
    // Indica que el jugador se encuentra en un planeta helado.
    public void obtener_info_del_planeta(){
        System.out.println("\n>> Estás en la superficie del planeta Helado <<");
    }
    // Método para extraer recursos adicionales del planeta.
    // En el caso del planeta Helado, no se extraen recursos adicionales.
    // @param cantidad La cantidad de recursos solicitada.
    // @return Siempre retorna 0.
    public int ExtraerRecursos2(int cantidad){
        return 0;
    }
      
}