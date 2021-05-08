package simple;

public class ClassExtendTest {

    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.getName());
        a.setName("C");
        System.out.println(a.name);
        System.out.println(((B)a).name);
    }

    public static class A{
        private String name = "A";

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    static class B extends A{
        private String name = "B";

        public String getName(){
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
