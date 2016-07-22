package com.wbl.test.polymorphic;

/**
 * Created withSimple_love
 * Date: 2016/1/21.
 * Time: 10:09
 *
 * 构造器的调用顺序
 */
class Meal{
        public Meal() {
                System.out.println("Meal()");
        }
}

class Bread{
        public Bread() {
                System.out.println("Bread()");
        }
}

class Cheese{
        public Cheese() {
                System.out.println("Cheese()");
        }
}

class Lettuce{
        public Lettuce() {
                System.out.println("Lettuce()");
        }
}

class Lunch extends Meal{
        public Lunch() {
                System.out.println("Lunch()");
        }
}

class PortableLunch extends Lunch{
        public PortableLunch() {
                System.out.println("PortableLunch()");
        }
}

public class Sandwich extends PortableLunch{
        private Bread bread = new Bread();
        private Cheese cheese = new Cheese();
        private Lettuce lettuce = new Lettuce();

        public Sandwich() {
                System.out.println("Sandwich()");
        }

        public static void main(String[] args) {
                new Sandwich();
        }
}
