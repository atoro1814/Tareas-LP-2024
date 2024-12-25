public class CentroGalactico extends Planeta {

    // Constructor vacío de la clase CentroGalactico.
    public CentroGalactico(){

    }
    // Método para verificar si el jugador puede ingresar al Centro Galáctico.
    // El jugador solo puede ingresar si la eficiencia de sus propulsores es mayor al 50%.
    // @param jugador El jugador que intenta ingresar al Centro Galáctico.
    // @return true si el jugador tiene más del 50% de eficiencia en sus propulsores, false en caso contrario.
    public boolean verificar_entrada(Jugador jugador){
        if(jugador.obtener_Eficiencia_Propulsor() >= 0.5){
            return true;
        }else{
            System.out.println("Lamentablemente no superas el 50% de Eficiencia de tus propulsores, no puedes ingresar al Centro Galactico");
            return false;
        }
    }
    // Método para extraer recursos del planeta.
    // En el caso del Centro Galáctico, no se pueden extraer recursos.
    // @param cantidad La cantidad de recursos que se intenta extraer.
    // @return Siempre retorna 0, ya que no hay recursos disponibles en el Centro Galáctico.
    public int ExtraerRecursos2(int cantidad) {
        return 0; 
    }
    // Método para obtener la información general del planeta.
    // Este método está vacío ya que el Centro Galáctico no tiene información especial
    public void obtener_info_del_planeta(){

    }
    // Método para calcular un radio aleatorio para el planeta.
    // En el caso del Centro Galáctico, siempre retorna 0.
    // @return Siempre retorna 0.
    public int radio_aleatorio(){
        return 0;
    };
    // Método para calcular la cantidad de hidrógeno disponible en función del radio del planeta.
    // En el Centro Galáctico, siempre retorna 0 ya que no hay recursos disponibles.
    // @param radio El radio del planeta.
    // @return Siempre retorna 0.
    public int Calcular_Hidrogeno(int radio){
        return 0;
    };
    // Método para calcular la cantidad de sodio disponible en función del radio del planeta.
    // En el Centro Galáctico, siempre retorna 0 ya que no hay recursos disponibles.
    // @param radio El radio del planeta.
    // @return Siempre retorna 0.
    public int Calcular_Sodio(int radio){
        return 0;
    }
    // Método para calcular el consumo de energía necesario para realizar una acción en función de una variable.
    // En el Centro Galáctico, siempre retorna 0 ya que no se requiere energía.
    // @param variable Variable que influye en el cálculo del consumo de energía.
    // @return Siempre retorna 0.
    public int Calcular_ConsumoDeEnergia(int variable){
        return 0;
    }
    // Método para mostrar una característica única del Centro Galáctico.
    // Este método está vacío ya que el Centro Galáctico no tiene características únicas definidas.
    public void Mostrar_caracteristica_unica(){

    }

}