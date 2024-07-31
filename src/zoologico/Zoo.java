package zoologico;

import java.util.ArrayList;

public abstract class Zoo {
    ArrayList<Animales> jaulas = new ArrayList<>();

    public abstract double cantar(double num);

    public int alojar() {
        return jaulas.size();
    }

    public void agregarAnimal(Animales animal) {
        jaulas.add(animal);
    }

    public ArrayList<Animales> getAnimales() {
        return jaulas;
    }
}