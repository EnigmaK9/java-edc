public class herencia{
    // vamos a meter metodos y atributos que requiere nuestra clase
    String name;
    public void eat(){
        System.out.println("puedo comer");
    }
}
class perro extends herencia{
    // nuevo metodo
    public void visualizar(){
        System.out.println("Mi nombre es: " + name);
    }
}
class gato extends herencia{
    public void visualizarDos(){
        System.out.println("Soy un gato y mi nombre es: " + name);
    }
}
class datosAnimales{
    public static void main(String[] args){
        // creamos el objeto
        perro labrador = new perro();
        //invocamos el atributo
        labrador.name = "Gus";
        labrador.visualizar();
        labrador.eat();

        //creamos el objeto de gato
        gato siames = new gato();
        siames.name = "michi";
        siames.visualizarDos();
        siames.eat();
    }
}


