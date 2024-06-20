class Animal {

  // field and method of the parent class
  String name;
  public void eat() {
    System.out.println("puedo comer croquetas");
  }
}

// inherit from Animal
class Dog extends Animal {

  // new method in subclass
  public void display() {
    System.out.println("Mi nombre es " + name);
  }
}

class animales {
  public static void main(String[] args) {

    // create an object of the subclass
    Dog labrador = new Dog();

    // access field of superclass
    labrador.name = "Marduk";
    labrador.display();

    // call method of superclass
    // using object of subclass
    labrador.eat();

  }
}
