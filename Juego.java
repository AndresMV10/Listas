import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Juego {

    List<Personaje> listado;

    public Juego() {
        listado = new ArrayList<>();
        try {
            quemar(); // Cargar datos iniciales
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    public boolean buscarPersonajePorNombre(String nombre) {
        for (Personaje x : listado) {
            if (x.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public Personaje buscarPersonajePorID(int id) {
        if (listado.isEmpty()) {
            return null; // Lista vacía, no se puede buscar
        }

        // La lista debe estar ordenada antes de la búsqueda binaria
        ordenar();

        int inf = 0;
        int sup = listado.size() - 1;
        int centro;

        while (inf <= sup) {
            centro = (inf + sup) / 2;
            if (listado.get(centro).getId() == id) {
                return listado.get(centro); // Retorna el personaje si lo encuentra
            } else if (id > listado.get(centro).getId()) {
                inf = centro + 1;
            } else {
                sup = centro - 1;
            }
        }
        return null; // No se encontró el personaje
    }

    public boolean agregarPersonaje(Personaje nuevo) throws Exception {
        if (buscarPersonajePorID(nuevo.getId()) != null) {
            throw new Exception("El ID ya existe");
        } else {
            listado.add(nuevo);
            ordenar(); // Mantener ordenada la lista para la búsqueda binaria
            return true;
        }
    }

    public void quemar() throws Exception {
        agregarPersonaje(new Personaje(1, "Link", "Arquero", 45, 300, "Épico"));
        agregarPersonaje(new Personaje(2, "Kratos", "Guerrero", 70, 500, "Legendario"));
        agregarPersonaje(new Personaje(3, "Aloy", "Arquero", 50, 320, "Épico"));
        agregarPersonaje(new Personaje(4, "Geralt", "Mago", 60, 400, "Raro"));
    }

    public void ordenar() {
        int j;
        Personaje aux;
        for (int p = 1; p < listado.size(); p++) {
            j = p - 1;
            aux = listado.get(p);
            while (j >= 0 && aux.getId() < listado.get(j).getId()) {
                listado.set(j + 1, listado.get(j));
                j--;
            }
            listado.set(j + 1, aux);
        }
    }

    public void ordenarPorNivel(boolean ascendente) {
        for (int i = 0; i < listado.size() - 1; i++) {
            for (int j = 0; j < listado.size() - i - 1; j++) {
                boolean condicion = ascendente
                        ? listado.get(j).getNivel() > listado.get(j + 1).getNivel()
                        : listado.get(j).getNivel() < listado.get(j + 1).getNivel();

                if (condicion) {
                    Personaje temp = listado.get(j);
                    listado.set(j, listado.get(j + 1));
                    listado.set(j + 1, temp);
                }
            }
        }
    }

    public List<Personaje> filtrarPorClase(String clase) {
        List<Personaje> filtrados = new ArrayList<>();
        for (Personaje p : listado) {
            if (p.getClase().equalsIgnoreCase(clase)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    @Override
    public String toString() {
        String resultado = "";
        for(Personaje personaje: listado){
            resultado +=personaje.toString();
        }
        return resultado;
    }

}

