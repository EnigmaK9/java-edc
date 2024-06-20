public class persona{
    private String nombre; //private tiene acceso restringido
    //getter
    public String getNombre(){
        return nombre;
        }
    //setter: el metodo set establece un valor
    public void setNombre(String nuevoNombre){
        this.nombre = nuevoNombre;
    }

}

class personas{
    public static void main(String[] args){
        persona miObj = new persona();
        miObj.setNombre("Carlos");
        System.out.println(miObj.getNombre());
    }
}
