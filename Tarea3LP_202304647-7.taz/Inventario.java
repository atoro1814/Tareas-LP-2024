public class Inventario {

    private int CristalesHidrogeno;
    private int FloresDeSodio;
    private int Uranio;
    private int Platino;
    // Constructor de la clase Inventario.
    // Inicializa el inventario con 0 unidades de cristales de hidrógeno, flores de sodio, uranio y platino.
    public Inventario(){
        CristalesHidrogeno = 0;
        FloresDeSodio = 0;
        Uranio = 0;
        Platino = 0;
    }
    // Método para agregar una cantidad de recursos al inventario.
    // @param cantidad La cantidad de recursos a agregar.
    // @param recurso El tipo de recurso a agregar (1 para cristales de hidrógeno, 2 para flores de sodio).
    // @param planeta El planeta del cual provienen los recursos.
    public void Agregar_cantidad(int cantidad, int recurso, Planeta planeta){
        if(recurso == 1){
            CristalesHidrogeno = CristalesHidrogeno + cantidad;
        }else if (recurso == 2){
            FloresDeSodio = FloresDeSodio + cantidad;
        }else{
            if(planeta instanceof Radioactivo){
                Uranio = Uranio + cantidad;
            }else if (planeta instanceof Volcanico){
                Platino = Platino + cantidad;
            }
        }
        
    }
    // Método para mostrar el inventario del jugador.
    // Imprime la cantidad de cada recurso en el inventario
    public void mostrar_inventario(){

        System.out.println("\n=== Inventario del Explorador Espacial ===");
        System.out.println("Tus valiosos recursos recolectados en esta vasta galaxia son:");
        System.out.println("---------------------------------------------");
        System.out.println("✨ Cristales de Hidrógeno: " + CristalesHidrogeno + " unidades");
        System.out.println("🌼 Flores de Sodio: " + FloresDeSodio + " unidades");
        System.out.println("⚛️ Uranio: " + Uranio + " unidades");
        System.out.println("💎 Platino: " + Platino + " unidades");
        System.out.println("---------------------------------------------");
        System.out.println("¡Asegúrate de usar bien estos recursos en tu travesía!");
    }
    // Método para vaciar el inventario del jugador.
    // Resetea todas las cantidades de recursos a 0.
    public void Vaciar_inventario(){
        CristalesHidrogeno = 0;
        FloresDeSodio = 0;
        Uranio = 0;
        Platino = 0;
    }
    // Método para quitar una cantidad de recursos del inventario durante un asentamiento.
    // @param desicion El tipo de recurso a quitar (1 para uranio, otro para platino).
    // @param cantidad La cantidad de recursos a quitar.
    public void quitar_cantidad_Asentamiento(int desicion,int cantidad){
        if(desicion == 1){
            Uranio = Uranio - cantidad;
        }else{
            Platino = Platino - cantidad;
        }
    }
    // Método para quitar una cantidad de recursos del inventario durante una recarga.
    // @param desicion El tipo de recurso a quitar (1 para cristales de hidrógeno, otro para flores de sodio).
    // @param cantidad La cantidad de recursos a quitar.
    public void quitar_cantidad_Recarga(int desicion,int cantidad){
        if(desicion == 1){
            CristalesHidrogeno = CristalesHidrogeno - cantidad;
        }else{
            FloresDeSodio = FloresDeSodio - cantidad;
        }
    }
    // Método para verificar si hay suficiente cantidad de recursos en el inventario para un asentamiento.
    // @param decision El tipo de recurso a verificar (1 para uranio, otro para platino).
    // @param cantidad La cantidad de recursos a verificar.
    // @return true si hay suficientes recursos, false de lo contrario.
    public boolean Verificar_cantidad_Asentamiento(int decision,int cantidad){
        if(decision == 1){
            if(cantidad > Uranio){
                return false;
            }else{
                return true;
            }
        }else{
            if(cantidad > Platino){
                return false;
            }else{
                return true;
            }

        }

    }

}