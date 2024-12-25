import java.lang.Math;
import java.util.Scanner;
import java.util.Random;


public abstract class Planeta {

    private int radio;
    private int CristalesHidrogeno;
    private int FloresDeSodio;
    private int ConsumoDeEnergia;
    private int RecursoElegido;
    
    // Constructor de la clase Planeta que inicializa el radio con un valor aleatorio.
    public Planeta(){
        radio = radio_aleatorio(); 
        
    }
    // Método para obtener el radio del planeta.
    // @return El radio del planeta.
    public int obtener_radio(){
        return radio;
    }
    // Método para obtener la cantidad de cristales de hidrógeno disponibles.
    // @return La cantidad de cristales de hidrógeno.
    public int obtener_hidrogeno(){
        return CristalesHidrogeno;
    }
    // Método para obtener la cantidad de flores de sodio disponibles.
    // @return La cantidad de flores de sodio.
    public int obtener_sodio(){
        return FloresDeSodio;
    }
    
    // Método abstracto para generar un radio aleatorio para el planeta.
    // @return Un valor entero que representa el radio aleatorio.
    public abstract int radio_aleatorio();
    // Método abstracto para calcular la cantidad de hidrógeno en función del radio.
    // @param radio El radio del planeta.
    // @return La cantidad de hidrógeno calculada.
    public abstract int Calcular_Hidrogeno(int radio);
    // Método abstracto para calcular la cantidad de sodio en función del radio.
    // @param radio El radio del planeta.
    // @return La cantidad de sodio calculada.
    public abstract int Calcular_Sodio(int radio);
    // Método abstracto para calcular el consumo de energía.
    // @param variable Un valor entero que se utiliza en el cálculo.
    // @return El consumo de energía calculado.
    public abstract int Calcular_ConsumoDeEnergia(int variable);
    // Método abstracto para mostrar características únicas del planeta.
    public abstract void Mostrar_caracteristica_unica();
    
    // Método para cambiar la cantidad de cristales de hidrógeno.
    // @param cantidad_calculada La nueva cantidad de cristales de hidrógeno.
    public void cambiar_Hidrogeno(int cantidad_calculada){
        CristalesHidrogeno = cantidad_calculada;
    }
    // Método para cambiar la cantidad de flores de sodio.
    // @param cantidad_calculada La nueva cantidad de flores de sodio.
    public void cambiar_Sodio(int cantidad_calculada){
        FloresDeSodio = cantidad_calculada;
    }
    // Método para cambiar el consumo de energía.
    // @param cantidad_calculada El nuevo consumo de energía.
    public void cambiar_Energia(int cantidad_calculada){
        ConsumoDeEnergia = cantidad_calculada;
    }
    // Método para cambiar el recurso elegido por el jugador.
    // @param recurso El nuevo recurso elegido.
    public void cambiar_recurso_elegido(int recurso){
        RecursoElegido = recurso;
    }

    

    // Método abstracto para obtener información del planeta.
    public abstract void obtener_info_del_planeta();
    // Método para imprimir información sobre los recursos del planeta.
    public void imprimir_info_recursos(){
  
        System.out.println("Has realizado un análisis detallado del planeta con tu dispositivo.");
        System.out.println("🌍 El radio del planeta es: " + radio + " km");
        System.out.println("\n🌡️ Condiciones del planeta detectadas por tu dispositivo de exploración:");
        Mostrar_caracteristica_unica();
        System.out.println("🔋Consumo de Energia: " + ConsumoDeEnergia);
        System.out.println("🌍 Recursos disponibles en el planeta:");
        System.out.println("✨ Cristales de Hidrógeno: " + CristalesHidrogeno + " unidades");
        System.out.println("🌼 Flores de Sodio: " + FloresDeSodio + " unidades");

    }
    // Método para mostrar la interfaz de extracción de recursos.
    public void interfaz_extraccion(){
        System.out.println("¿Qué recurso deseas extraer?");
        System.out.println("1. Cristales de Sodio");
        System.out.println("2. Flores de Sodio");
        
    }
    // Método abstracto para extraer recursos del planeta.
    // Este método debe ser implementado por las subclases específicas.
    // @param cantidad La cantidad de recursos a extraer.
    // @return La cantidad de recursos efectivamente extraídos.

    public abstract int ExtraerRecursos2(int cantidad);
    // Método para extraer recursos del planeta.
    // Si el recurso elegido es Cristales de Hidrógeno o Flores de Sodio, se extrae esa cantidad.
    // Si no, llama al método abstracto `ExtraerRecursos2` para otros tipos de recursos.
    // @param Cantidad La cantidad de recursos a extraer.
    // @return La cantidad de recursos efectivamente extraídos.
    public int ExtraerRecursos(int Cantidad){

        if(RecursoElegido == 1){
            CristalesHidrogeno = CristalesHidrogeno - Cantidad;
            return Cantidad;
        }else if(RecursoElegido == 2){
            FloresDeSodio = FloresDeSodio - Cantidad;
            return Cantidad;
        }else{
            return ExtraerRecursos2(Cantidad);
        } 

    }
    // Verifica si es posible extraer la cantidad de recursos solicitada.
    // Calcula el consumo de energía basado en la cantidad y la eficiencia del jugador.
    // @param cantidad La cantidad de recursos que se desea extraer.
    // @param jugador El jugador que intenta extraer los recursos.
    // @return true si es posible extraer los recursos, false si no hay suficiente energía.

    public boolean verificar_ExtraerRecursos(int cantidad ,Jugador jugador){
        float UnidadesConsumidas = (float) (0.5f * cantidad * (ConsumoDeEnergia/100f) * (1f - jugador.Obtener_Eficiencia()));
        if(UnidadesConsumidas > jugador.Obtener_UnidadesEnergiaProteccion()){
            return false;
        }else{
            return true;
        }
    }
    // Método para salir del planeta actual.
    // @return false, indicando que el jugador abandona el planeta.
    public boolean Salir(){
        return false;
    }
    // Método que gestiona las opciones disponibles cuando el jugador visita un planeta.
    // Presenta opciones como extraer recursos, visitar asentamientos, salir del planeta, recargar energía, o activar el sistema de emergencias.
    // @param jugador El jugador que interactúa con el planeta.
    // @return true si el jugador permanece en el planeta, false si decide salir.
    public boolean Visitar(Jugador jugador){
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        
        System.out.println("\n=== Acciones disponibles ===");
        System.out.println("1. 🛠️ Extraer Recursos");
        System.out.println("2. 🏠 Visitar Asentamientos");
        System.out.println("3. 🚀 Salir del Planeta");
        System.out.println("4. ⚡ Recargar Exotraje");
        System.out.println("5. 🚨 Activar Sistema de Emergencias");
        System.out.print("Elige una opción: ");
        int decision = scanner.nextInt();
        if(decision == 1){
 
            interfaz_extraccion(); 
            decision = scanner.nextInt(); 
            RecursoElegido = decision;
            System.out.println("Cuanta cantidad deseas extraer ?");
            cantidad = scanner.nextInt();
            if(verificar_ExtraerRecursos(cantidad, jugador)){
                System.out.println("Realizando la extraccion....");
                ExtraerRecursos(cantidad);
                
                jugador.Agregar_recurso_Inventario(cantidad, decision, this);
                jugador.cambiar_UnidadesEnergiaProteccion(ConsumoDeEnergia, cantidad);
                return true;
            }else{
                
                System.out.println("\n🚨 ¡Error! No es posible realizar la extracción.");
                System.out.println("⚡ Tu exotraje no tiene suficiente energía.");
                System.out.println("Considera recargar antes de intentar nuevamente.");
                return true;
               
            }
            
        }else if(decision == 2){
           
            if (this instanceof Tieneasentamientos) {
                ((Tieneasentamientos) this).VisitarAsentamientos(jugador);
            } else if(this instanceof Radioactivo) {
                System.out.println("Este planeta no tiene Asentamientos");
                return true;
            }else{
                System.out.println("Este planeta no tiene Asentamientos");
                return true;
            }
            return true;
        }else if(decision == 3){
            
            System.out.println("Saliendo del planeta...");
            return Salir();
        } else if(decision == 4){
            jugador.mostrar_inventario();
            System.out.println("Ingrese la cantidad de Sodio que desea recargar ? ");
            cantidad = scanner.nextInt();
            jugador.RecargarEnergiaProteccion(cantidad);
            decision = 2;
            jugador.quitar_cantidad_Recarga(decision, cantidad);
            
        } else if(decision == 5){
            System.out.println("\n⚠️ ¡Atención, explorador intrépido! ⚠️");
            System.out.println("Serás transportado de emergencia al Planeta 0...");
            System.out.println("⚡ Tu exotraje y nave serán completamente recargados.");
            System.out.println("❌ Lamentablemente, perderás todos los recursos que llevabas en tu inventario.");
            System.out.println("¡Recuerda, el viaje espacial es impredecible, pero siempre hay otra oportunidad!");

            jugador.Modificar_Aviso();
        }
        return false;
    };
}