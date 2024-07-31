package zoologico;

public class Animales extends Zoo {
    String nombre;
    String habitat;
    String alimento;

    public Animales(String nombre) {
        this.nombre = nombre;
        this.habitat = "Desconocido";
        this.alimento = "Desconocido";
    }

    @Override
    public double cantar(double num) {
        double canto = Math.sqrt(num);
        System.out.println("Animales cantando..." + canto);
        return canto;
    }

    // Getters y setters para habitat y alimento
    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Habitat: " + habitat + ", Alimento: " + alimento;
    }
}
