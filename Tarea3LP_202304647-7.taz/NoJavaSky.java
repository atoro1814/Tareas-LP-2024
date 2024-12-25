import java.util.Random;
import java.util.Scanner;
public class NoJavaSky {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("========== REGLAS DEL JUEGO: NO JAVA'S SKY ==========");
        System.out.println("1. Eres un explorador espacial en busca del Centro Galáctico.");
        System.out.println("2. Puedes viajar por el Mapa Galáctico, visitando planetas y extrayendo recursos.");
        System.out.println("3. Hay diferentes tipos de planetas con condiciones climáticas únicas y recursos limitados.");
        System.out.println("4. Planetas Helados y Oceánicos tienen asentamientos, donde puedes intercambiar recursos como Uranio y Platino.");
        System.out.println("5. El objetivo es mejorar tu exotraje y nave a través de estos intercambios.");
        System.out.println("6. Cada planeta tiene recursos comunes como Cristales de Hidrógeno y Flores de Sodio.");
        System.out.println("7. La energía de tu exotraje y el combustible de tu nave son limitados, y deberás gestionarlos sabiamente.");
        System.out.println("8. Si te quedas sin energía en un planeta, podrás seleccionar el Sistema de emergencia y serás transportado al Planeta 0 con todo recargado.");
        System.out.println("9. Ganas el juego cuando llegues al Centro Galáctico con una nave que tenga más de 50% de eficiencia.");
        System.out.println("10. Todas las interacciones se realizan por la terminal, elige tus acciones cuidadosamente.");
        System.out.println("=======================================================");

        System.out.println("\n*** ¡Bienvenido a NO JAVA'S SKY! ***");
        System.out.println("Tu misión es llegar al misterioso Centro Galáctico.");
        System.out.println("Recoge recursos, mejora tu nave, y sobrevive en este vasto universo...");
        

        MapaGalactico mapagalactico = new MapaGalactico();
        Planeta planeta = mapagalactico.Generador_Planeta(); 
        mapagalactico.Agregar_planeta(planeta); 
        Jugador jugador = new Jugador();
 
        int num_planeta;


        boolean bandera_ganador = true;
        while(bandera_ganador){

            
            
            System.out.println("\n>> Actualmente estás en la órbita del planeta: " + mapagalactico.Obtener_posicion() + " <<");
            if (planeta instanceof CentroGalactico){
                System.out.println("¡Increíble! Has alcanzado la órbita del legendario Centro Galáctico.");
            }
            

            
            System.out.println("---------------------------------------------");
            System.out.println("¿Qué deseas hacer, valiente explorador?");
            System.out.println("1. Aterrizar y explorar el planeta");
            System.out.println("2. Activar los propulsores y viajar a otro planeta");
            System.out.print("Haz tu elección (1 o 2): ");
            int eleccion = scanner.nextInt();
            System.out.println("---------------------------------------------");


            if(eleccion == 1){
                boolean ciclo_visitar = true;
                while(ciclo_visitar){
                    if (planeta instanceof CentroGalactico){
                        System.out.println("\nEstás intentando descender en el Centro Galáctico...");
                        if(((CentroGalactico)planeta).verificar_entrada(jugador)){
                            System.out.println("\n¡Felicidades! Has superado todos los desafíos y alcanzado el Centro Galáctico.");
                            System.out.println(">> ¡Has ganado el juego! <<");
                            System.exit(1);
                        }else{
                            System.out.println("Aún no estás listo para entrar al Centro Galáctico...");
                            ciclo_visitar = false;
                        }
                    }else{
                        planeta.obtener_info_del_planeta();
                        jugador.mostrar_inventario(); 
                        jugador.imprimir_jugador(); 
                        planeta.imprimir_info_recursos();
                        System.out.println("---------------------------------------------");
                        ciclo_visitar = planeta.Visitar(jugador);
                        if(jugador.Obtener_Aviso() == 1){
                            System.out.println("\n¡Alerta! Te has quedado sin energía...");
                            System.out.println(">> Volviendo al Planeta 0 para recargar sistemas y recuperar energía <<");
                            planeta = mapagalactico.Acceder_planeta(0);
                            mapagalactico.cambiar_posicion(0);
                            jugador.RestablecerEnergias();
                            jugador.Vaciar_inventario();

                        }
                    }
                }
            }else{
                int direccion;
                int tamanoSalto;
                boolean ciclo_viaje = true;
                
                
                jugador.imprimir_datos_nave();
                System.out.println("\n🌌 Exploración del Mapa Galáctico");
                System.out.println("🪐 Planetas visitados en tu travesía:");
                System.out.println("---------------------------------------------");

                mapagalactico.Mostrar_MapaGalactico();
                System.out.println("\n>> Prepara los motores... <<");
                System.out.println("1. Recargar combustible");
                System.out.println("2. Iniciar secuencia de salto");
                System.out.print("Elige tu acción: ");
                int decision = scanner.nextInt();
                if(decision == 1){
                    System.out.print("¿Cuánto combustible deseas recargar?: ");
                    int cantidad = scanner.nextInt();
                    jugador.RecargarPropulsores(decision);
                

                }else {
                    System.out.print("Ingresa el número del planeta al que quieres viajar: ");
                    num_planeta = scanner.nextInt();
                    if (num_planeta > mapagalactico.Obtener_posicion()){
                        direccion = 1;
                    }else{
                        direccion = 2;
                    }
    
                    tamanoSalto = num_planeta - mapagalactico.Obtener_posicion();
                    tamanoSalto = Math.abs(tamanoSalto);
    
                    
                    ciclo_viaje = jugador.ViajarPlaneta(mapagalactico, direccion, tamanoSalto);
                    if(ciclo_viaje == false){
                        System.out.println("\n¡Peligro! Te has quedado sin combustible a mitad del viaje.");
                        System.out.println(">> Serás devuelto al Planeta 0 con todos los sistemas recargados <<");
                        planeta = mapagalactico.Acceder_planeta(0);
                        mapagalactico.cambiar_posicion(0);
                        jugador.RestablecerEnergias();
                        jugador.Vaciar_inventario();
    
                    }else{
                        System.out.println("\n>> Iniciando salto hacia el planeta " + num_planeta + "... <<");
                        planeta = mapagalactico.Acceder_planeta(num_planeta);
                        
                    }
                }

            }
        }
    }
}    