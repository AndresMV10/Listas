public class Personaje {
    private int id;
    private String nombre;
    private String clase;
    private int nivel;
    private int poder;
    private String rareza;

    public Personaje(int id, String nombre, String clase, int nivel, int poder, String rareza) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
        this.poder = poder;
        this.rareza = rareza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    @Override
    public String toString() {
        return "Personaje: " +
                "Id: " + id +
                " Nombre: " + nombre  +
                " Clase: " + clase +
                " Nivel: " + nivel +
                " Poder: " + poder +
                " Rareza: " + rareza + "\n";
    }
}
