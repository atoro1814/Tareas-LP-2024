public class Nave {

    private float UnidadesCombustible;
    private float EficienciaPropulsor;
    // Constructor de la clase Nave.
    // Inicializa la nave con 100 unidades de combustible y eficiencia del propulsor en 0.
    public Nave(){
        UnidadesCombustible = 100.0f;
        EficienciaPropulsor = 0.0f;
    }
    // Método para imprimir el estado actual del propulsor de la nave.
    // Muestra la cantidad de combustible disponible y la eficiencia del propulsor.
    public void imprimir_datos(){
        System.out.println("\n=== Estado Actual del Propulsor de la Nave ===");
        System.out.println("🚀 Combustible disponible: " + UnidadesCombustible + " unidades");
        System.out.println("🔧 Eficiencia del propulsor: " + EficienciaPropulsor + " %");
        System.out.println("¡Asegúrate de no quedarte sin combustible durante tus saltos espaciales!");
    }
    // Método para mejorar la eficiencia del propulsor.
    // Aumenta la eficiencia en función de la cantidad de uranio intercambiado.
    // @param cantidad La cantidad de uranio intercambiado para mejorar la eficiencia.
    public void mejorar_eficiencia(int cantidad){
      
        float aumentoEficiencia = (cantidad/10f) * 0.1f;
        EficienciaPropulsor = EficienciaPropulsor + aumentoEficiencia;
        if (EficienciaPropulsor > 1.0f) {
            EficienciaPropulsor = 1.0f; 
        }
    }
    // Método para viajar a otro planeta.
    // Calcula el consumo de combustible y verifica si hay suficiente para el viaje.
    // @param MG El mapa galáctico que contiene la información de los planetas.
    // @param direccion La dirección del viaje (1 para adelante, 2 para atrás).
    // @param tamanoSalto El tamaño del salto a realizar.
    // @return true si el viaje fue exitoso, false si no hay suficiente combustible.
    public boolean ViajarPlaneta(MapaGalactico MG, int direccion , int tamanoSalto){

        float UnidadesConsumidades = (float) (0.75f * Math.pow(tamanoSalto, 2) * (1f - EficienciaPropulsor));
        System.out.println("El salto consumió : " + UnidadesConsumidades);
        UnidadesCombustible = UnidadesCombustible - UnidadesConsumidades;
        int destino;
        if(UnidadesCombustible < 0){
            return false;
        }
        
        int posicion_actual = MG.Obtener_posicion();

        if (direccion == 1){
            
            destino  = posicion_actual + tamanoSalto;
        }else{
            
            destino = posicion_actual - tamanoSalto;
        }
        MG.viajarAPlaneta(destino);
        MG.cambiar_posicion(destino);
        return true;
    }
    // Método para restablecer la energía de la nave a su máximo y la eficiemcia en 0.
    public void RestablecerEnergias(){
        UnidadesCombustible = 100.f;
        EficienciaPropulsor = 0.0f;
    }
    // Método para recargar los propulsores de la nave con hidrógeno.
    // @param hidrogeno La cantidad de hidrógeno utilizada para recargar los propulsores.
    public void RecargarPropulsores(int hidrogeno){
        float nuevaEnergia =  (float) (0.6f * hidrogeno * (1f - EficienciaPropulsor));
        
        if (UnidadesCombustible + nuevaEnergia > 100.0f) {
            UnidadesCombustible = 100.0f; 
        } else {
            UnidadesCombustible += nuevaEnergia; 
        }
    }
    // Método para obtener la eficiencia actual del propulsor.
    // @return La eficiencia actual del propulsor
    public float obtener_Eficiencia_Propulsor(){
        return EficienciaPropulsor;
    }
    
}