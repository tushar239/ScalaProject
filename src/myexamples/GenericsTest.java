package myexamples;

/**
 * @author Tushar Chokshi @ 12/19/16.
 */
public class GenericsTest {

    static class Fruit {

    }
    static class Apple extends Fruit {

    }

    static class Box<F extends Fruit> {
        private Fruit fruit;

        public Box(Fruit aFruit) {
            this.fruit = aFruit;
        }
    }

    public static void main(String[] args) {
        // Java doesn't allow this to assign Box<Apple> to Box<Fruit>
        // Box<Fruit> fruitBox = new Box<Apple>(new Apple());

    }
}
