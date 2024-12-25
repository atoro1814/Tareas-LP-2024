import java.util.ArrayList;
import java.util.Random;



public class MapaGalactico {


    private ArrayList<Planeta> planetas ;
    private int posicion;
    private int Largo;
    // Constructor de la clase MapaGalactico.
    // Inicializa el mapa galáctico con una lista vacía de planetas, posición en 0 y largo en 0.
    public MapaGalactico(){
        planetas = new ArrayList<>();
        posicion = 0;
        Largo = 0;
    }
    // Método para viajar a un planeta específico.
    // Si el planeta no existe, se genera uno nuevo en la posición de destino.
    // @param destino La posición del planeta al que se desea viajar.
    public void viajarAPlaneta(int destino) {
        if (destino < planetas.size()) {
            if(planetas.get(destino) == null){
                Planeta planeta = Generador_Planeta();
                planetas.set(destino , planeta);
            }
            return;
        }
        for (int i = posicion + 1; i <= destino; i++) {
            
            planetas.add(null);
        }
        Planeta planeta = Generador_Planeta();
        planetas.set(destino , planeta);
    }

    // Método para mostrar el mapa galáctico y los planetas existentes.
    public void Mostrar_MapaGalactico(){
        for(int i = 0 ; i < planetas.size() ; i++){
            if(planetas.get(i) != null){
                if(planetas.get(i) instanceof Helado){
                    System.out.println("Planeta " + i + " : " + "Helado");
                }else if(planetas.get( i) instanceof Oceanico){
                    System.out.println("Planeta " + i + " : " + "Oceanico");
                }else if(planetas.get(i) instanceof Radioactivo){
                    System.out.println("Planeta " + i + " : " + "Radioactivo");
                }else if(planetas.get(i) instanceof Volcanico){
                    System.out.println("Planeta " + i + " : " + "Volcanico");
                }else if (planetas.get(i) instanceof CentroGalactico){
                    System.out.println("Planeta " + i + " : " + "CentroGalactico");
                }
            }        
        }
    }
    // Método para agregar un planeta al mapa galáctico.
    // @param planeta El planeta a agregar al mapa.
    public void Agregar_planeta(Planeta planeta){
        planetas.add(planeta);
    }
    // Método para agregar un planeta en una posición específica del mapa galáctico.
    // @param planeta El planeta a agregar.
    // @param destino La posición en la que se debe agregar el planeta
    public void Agregar_planeta_2(Planeta planeta, int destino){
        planetas.set(destino, planeta);
    }
    // Método para acceder a un planeta en una posición específica del mapa.
    // @param destino La posición del planeta a acceder.
    // @return El planeta en la posición especificada.
    public Planeta Acceder_planeta(int destino){
        return planetas.get(destino);
    }
    // Método para obtener el largo actual del mapa galáctico.
    // @return El largo del mapa galáctico.
    public int obtener_largo(){
        return Largo;
    }
    // Método para cambiar el largo del mapa galáctico.
    // @param destino El nuevo largo del mapa galáctico.
    public void cambiar_largo(int destino){
        Largo = destino;
    }
    // Método para obtener la posición actual del jugador en el mapa galáctico.
    // @return La posición actual del jugador.
    public int Obtener_posicion(){
        return posicion;
    }
    // Método para cambiar la posición del jugador en el mapa galáctico.
    // @param pos La nueva posición del jugador.
    public void cambiar_posicion(int pos){
        posicion = pos;
    }
    // Método para generar un nuevo planeta aleatorio.
    // @return Un objeto Planeta generado aleatoriamente
    public Planeta Generador_Planeta(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(100);

        
        if (numeroAleatorio < 30){
            Helado helado = new Helado();
            return helado;

        } else if(numeroAleatorio < 60) {
            Oceanico oceanico = new Oceanico();
            return oceanico;
        }else if (numeroAleatorio < 80){
            Radioactivo radioactivo = new Radioactivo();
            return radioactivo;

        }else if (numeroAleatorio < 99){
            Volcanico volcanico = new Volcanico();
            return volcanico;
        }else{
            CentroGalactico centrogalactico = new CentroGalactico();
            return centrogalactico;
        }
    }
}