public class Jugador {

    private float UnidadesEnergiaProteccion;
    private float EficienciaEnergiaProteccion;
    private Inventario inventario;
    private Nave nave;
    private int Aviso_Emergencia;
    // Constructor de la clase Jugador.
    // Inicializa el jugador con 100 unidades de energía de protección, 0% de eficiencia,
    // un inventario vacío y una nave.
    public Jugador(){
        UnidadesEnergiaProteccion = 100.0f;
        EficienciaEnergiaProteccion = 0.0f;
        inventario = new Inventario();
        nave = new Nave();
        Aviso_Emergencia = 0;
    }
    // Método para imprimir el estado actual del exotraje del jugador.
    // Muestra la energía disponible y la eficiencia del traje.
    public void imprimir_jugador(){
        System.out.println("\n=== Estado Actual de tu Exotraje ===");
        System.out.println("⚡ Energía disponible: " + Obtener_UnidadesEnergiaProteccion() + " unidades");
        System.out.println("🔧 Eficiencia del traje: " + Obtener_Eficiencia() + " %");
        System.out.println("---------------------------------------------");
    }
    // Método para cambiar la cantidad de energía de protección en función del consumo.
    // @param Consumo_Energia El consumo de energía asociado a la extracción de recursos.
    // @param cantidad La cantidad de recursos extraídos.
    public void cambiar_UnidadesEnergiaProteccion(int Consumo_Energia, int cantidad){
        UnidadesEnergiaProteccion = (float) (UnidadesEnergiaProteccion - (0.5f * cantidad *(Consumo_Energia / 100f)*(1f - EficienciaEnergiaProteccion)));
        System.out.println("Extraccion consumio : " + (0.5f * cantidad *(Consumo_Energia / 100f)*(1f - EficienciaEnergiaProteccion)));
    }
    // Método para obtener la eficiencia actual del exotraje.
    // @return La eficiencia actual del exotraje.
    public float Obtener_Eficiencia(){
        return EficienciaEnergiaProteccion;
    }
    // Método para obtener la cantidad de energía de protección actual del jugador.
    // @return Las unidades de energía de protección disponibles.
    public float Obtener_UnidadesEnergiaProteccion(){
        return UnidadesEnergiaProteccion;
    }
    // Método para recargar la energía de protección del jugador con sodio.
    // @param sodio La cantidad de sodio utilizada para la recarga.
    public void RecargarEnergiaProteccion(int sodio){
        float nuevaEnergia = (float)(0.65 * sodio * (1 - Obtener_Eficiencia()));
        if (UnidadesEnergiaProteccion + nuevaEnergia > 100.0f) {
            UnidadesEnergiaProteccion = 100.0f; // Limita a 100
        } else {
            UnidadesEnergiaProteccion += nuevaEnergia; // Suma normalmente
        }
    }
    // Método para agregar recursos al inventario del jugador.
    // @param cantidad La cantidad de recursos a agregar.
    // @param recurso El tipo de recurso a agregar.
    // @param planeta El planeta del cual provienen los recursos.
    public void Agregar_recurso_Inventario(int cantidad, int recurso, Planeta planeta){
        inventario.Agregar_cantidad(cantidad, recurso, planeta);
    }
    // Método para mostrar el inventario del jugador.
    public void mostrar_inventario(){
        inventario.mostrar_inventario();
    }
    public void mejorar_eficiencia_Exotraje(int cantidad){
        EficienciaEnergiaProteccion = EficienciaEnergiaProteccion + ((cantidad/10f) * 0.1f);
        if(EficienciaEnergiaProteccion > 1.0f){
            EficienciaEnergiaProteccion = 1.0f;
        } 
    }
    // Método para mejorar la eficiencia del exotraje del jugador.
    // @param cantidad La cantidad de recursos que se utilizarán para la mejora.
    public void mejorar_eficiencia_propulsor(int cantidad){
        nave.mejorar_eficiencia(cantidad);
    }
    // Método para viajar a otro planeta utilizando la nave.
    // @param MG El mapa galáctico que contiene la información de los planetas.
    // @param direccion La dirección del viaje .
    // @param tamanoSalto El tamaño del salto a realizar.
    // @return true si el viaje fue exitoso, false si no hay suficiente combustible.
    public boolean ViajarPlaneta(MapaGalactico MG,int direccion, int tamanoSalto){
        return nave.ViajarPlaneta(MG, direccion, tamanoSalto); 
    }
    // Método para imprimir los datos de la nave del jugador.
    public void imprimir_datos_nave(){
        nave.imprimir_datos();
    }
    // Método para modificar el aviso de emergencia.
    public void Modificar_Aviso(){
        Aviso_Emergencia = 1;
    }
    // Método para obtener el estado del aviso de emergencia.
    // @return El estado del aviso de emergencia (1 si está activado).
    public int Obtener_Aviso(){
        return Aviso_Emergencia;
    }
    // Método para vaciar el inventario del jugador.
    public void Vaciar_inventario(){
        inventario.Vaciar_inventario();
    }
    // Método para restablecer la energía del jugador y de la nave.
    public void RestablecerEnergias(){
        UnidadesEnergiaProteccion = 100.0f;
        nave.RestablecerEnergias();
    }
    // Método para recargar los propulsores de la nave del jugador.
    // @param hidrogeno La cantidad de hidrógeno utilizada para recargar los propulsores.
    public void RecargarPropulsores(int hidrogeno){
        nave.RecargarPropulsores(hidrogeno);
    }
    // Método para obtener la eficiencia actual del propulsor de la nave.
    // @return La eficiencia actual del propulsor.
    public float obtener_Eficiencia_Propulsor(){
        return nave.obtener_Eficiencia_Propulsor();
    }
    // Método para quitar una cantidad de recursos del inventario del jugador en un asentamiento.
    // @param decision El tipo de recurso a quitar.
    // @param cantidad La cantidad de recursos a quitar
    public void quitar_cantidad_Asentamiento(int decision , int cantidad ){
        inventario.quitar_cantidad_Asentamiento(decision, cantidad);
    }
    // Método para quitar una cantidad de recursos del inventario del jugador durante la recarga.
    // @param decision El tipo de recurso a quitar.
    // @param cantidad La cantidad de recursos a quitar.
    public void quitar_cantidad_Recarga(int decision ,int cantidad){
        inventario.quitar_cantidad_Recarga(decision, cantidad);
    }
    // Método para verificar si hay suficiente cantidad de recursos en el inventario para un asentamiento.
    // @param decision El tipo de recurso a verificar.
    // @param cantidad La cantidad de recursos a verificar.
    // @return true si hay suficientes recursos, false de lo contrario
    public boolean Verificar_cantidad_Asentamiento(int decision, int cantidad){
        return inventario.Verificar_cantidad_Asentamiento(decision,cantidad);
    }

}







