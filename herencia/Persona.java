public class Persona {
    private String nombre; // private tiene acceso restringido

    // getter
    public String getNombre() {
        return nombre;
    }

    // setter: el metodo set establece un valor
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }
}

class Personas {
    public static void main(String[] args) {
        Persona miObj = new Persona();
        miObj.setNombre("Carlos");
        System.out.println(miObj.getNombre());
    }
}

