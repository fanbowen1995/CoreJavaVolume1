# CoreJavaVolume1
《Java核心技术 卷I》中较为复杂的示例程序。

不是很扎实的部分：
4.6 
    对象构造
4.8 
    jar文件打包，清单文件
4.9.5 
    通用注释
4.9.6 
    包注释

5.1.2 
    子类拥有父类的字段和方法，但不能直接访问私有字段和方法。当子类覆盖父类某个方法时，使用关键字super调用父类相应方法。
5.1.3
    使用super调用父类构造器。如果没有显式调用父类构造器，自动调用父类无参数构造器，如果父类没有无参数构造器，编译器报错。
5.1.5
    警告中内容。
5.1.6
    警告1中内容：子类覆盖父类一个方法时，需要保证返回类型的兼容性，允许子类将覆盖方法的返回类型改为圆返回类型的子类型。
    警告2中内容：覆盖一个方法的时候，子类方法不能低于超类方法的可见性。如果超类方法是public，子类方法必须也要声明为public。
5.1.7
    final修饰类，不允许派生子类；final修饰方法，子类不能覆盖该方法。final类中的所有方法自动成为final方法，字段不会自动变为final。
5.1.9
    包含一个或多个抽象方法的类本身必须被声明为抽象的。除了抽象方法之外，抽象类还可以包含字段和具体的方法。
    即使不含抽象方法，也可以将类声明为抽象类。
    抽象类不能实例化。
5.1.10
    protected字段只能由同一个包中的类访问，尽量不使用受保护的字段。
    受保护的方法更具有实际意义。
    private 仅对本类可见。
    public 对外部完全可见。
    protected 对本包和所有子类可见。
    默认，不加修饰符  对本包可见。
5.2.3
    编写一个完美的equals方法建议：
    1.显示参数命名为otherObject，稍后需要将它强制转成另一个名为other的变量。
    2.检测this与otherObject是否相等：
    if(this == otherObject) return true;
    3.检测otherObject是否为null，如果为null，返回false。这项检测是很必要的。
    if(otherObject == null) return false;
    4.比较this与otherObject的类。如果equals的语义可以在子类中改变，就使用getClass检测：
    if(getClass() != otherObject.getClass()) return false;
    如果所有的子类都有相同的相等性语义，可以使用instanceof检测：
    if(!(otherObject instanceof ClassName)) return false;
    5.将otherObject强制转换为相应类类型的变量：
    ClassName other = (ClassName) otherObject;
    6.现在根据相等性概念的要求来比较字段。使用==比较基本类型字段，使用Objects.equals比较对象字段。如果所有的字段都匹配，就返回true；否则返回false。

    常见错误：
    public class Employee {
        public boolean equals(Employee other) {//error：参数类型为Employee，并没有覆盖Object类的equals方法，而是定义了一个完全无关的方法。
            return .....;
        }
    }
5.2.4
    如果重新定义了equals方法，就必须为用户可能插入散列表的对象重新定义hashCode方法。
    static int Objects.hash(Object... objects);
5.4
    包装器类是不可改变的，即一旦构造了包装器，就不允许更改包装在其中的值。同时，包装器类还是final，因此不能派生它们的子类。
    ArrayList<Integer>的效率远远低于int[]。
    自动地装箱和拆箱也适用于算数表达式。例如，可以将自增运算符应用于一个包装器引用：
        Integer n = 3
        n++;
    比较两个包装器对象时调用equals方法，下面的比较通常会失败：
        Integer a = 1000;
        Integer b = 1000;
        if(a == b) ...
    自动装箱规范要求 boolean、byte、char <= 127，介于 -128 和 127 之间的 short 和 int 被包装到固定的对象中。
    装箱和拆箱是编译器要做的工作，而不是虚拟机。编译器在生成类的字节码时会插入必要的方法调用。虚拟机只是执行这些字节码。
5.5
    允许将数组作为最后一个参数传递给有可变参数的方法。
5.6 枚举类
    定义枚举类型 public enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE };
    这个声明定义的类型是一个类，它刚好有4个实例，不可能构造新的对象。
    因此，在比较两个枚举类型的值时，并不需要调用equals，直接使用“==”就可以了。
    如果需要的话，可以为枚举类型增加构造器、方法和字段。构造器只是在构造枚举常量的时候调用。示例如下：
    public enum Size {
        SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

        private String abbreviation;

        private Size(String abbreviation) { this.abbreviation = abbreviation; }
        public String getAbbreviation() { return abbreviation; }
    }
    枚举的构造器总是私有的，可以省略 private 修饰符。如果修饰一个 enmu 构造器为 public 或 protected，会出现语法错误。
    所有的枚举类型都是Enum类的子类。
5.7.1
    在程序运行期间，Java运行时系统始终为所有对象维护一个运行时类型标识。
    虚拟机为每个类型管理一个唯一的Class对象。因此，可以利用 == 运算符实现两个类对象的比较。例如，if(e.getClass() == Employee.class)，如果e是一个Employee的实例，测试通过（与instanceof不同，如果e是某个子类的实例，测试将失败）。
5.7.4
    Class类中的getFields、getMethods和getConstructors方法将分别返回这个类支持的公共字段、方法和构造器的数组，其中包括超类的公共成员。Class类的getDeclareFields、getDeclareMethods和getDeclareConstructors方法将分别返回类中声明的全部字段、方法和构造器的数组，其中包括私有成员、包成员和受保护成员，但不包括超类的成员。
5.7.6
    Java数组会记住每个元素的类型，即创建数组时new表达式中使用的元素类型（强制类型转换时要注意）。
5.7.7
    建议不要使用回调函数的 Method 对象，可以使用回调的接口。
5.8 继承的设计技巧
    1.将公共操作和字段放在超类中。
    2.不要使用受保护的字段。
    3.使用继承实现 “is-a” 关系。
    4.除非所有继承的方法都有意义，否则不要使用继承。
    5.在覆盖方法时，不要改变预期的行为。
    6.使用多态，而不要使用类型信息。
    7.不要滥用反射。
6.1 接口
6.1.1 接口的概念
    接口中的所有方法都自动是public方法。因此，在接口中声明方法时，不必提供关键字public。
    接口可以定义常量。接口绝不会有实例字段，在Java 8之前，接口中绝对不会实现方法（现在可以在接口中提供简单的方法，但这些方法不能引用实例字段--接口没有实例。）
    在接口声明中不必将方法声明为public，因为在接口中所有方法都自动是public。不过，在实现接口时，必须把方法声明为public；否则，编译器将认为这个方法的访问属性是protected，这是类的默认访问属性，之后编译器就会报错，指出试图提供更严格的访问权限。
    继承中可能会出现问题，详见227页注释。
6.1.2 接口的属性
    与建立类的继承层次一样，也可以扩展接口。例如：
    public interface Moveable {
        void move(double x, double y);
    }
    public interface Powered extends Moveable {
        double milesPerGallon();
    }
    接口中不能包含实例字段，但可以包含常量。例如：
    public interface Powered extends Moveable {
        double milesPerGallon();
        double SPEED_LIMIT = 95;//a public static final constant
    }
    与接口中的方法都自动被设置为public一样，接口中的字段总是public static final。
6.1.4 静态和私有方法
    在Java 8中，允许在接口中增加静态方法。
    在Java 9中，接口中的方法可以是private。private方法可以是静态方法或实例方法。由于私有方法只能在接口本身的方法中使用，所以用法很有限，只能作为接口中其他方法的辅助方法。
6.1.5 默认方法
    可以为接口方法提供一个默认实现。必须用default修饰符标记这样一个方法。默认方法可以调用其他方法。
    public interface Comparable<T> {
        default int compareTo(T other) { return 0; }
          // by default, all elements are the same
    }
    默认方法的一个重要用法是“接口演化”（详见231页）。为接口增加一个非默认方法不能保证“源代码兼容（编译不通过）”；为接口增加方法可以保证“二进制兼容（不重新编译，使用原先的JAR文件可正常加载类）”。
6.1.6 解决默认方法冲突
    如果先在一个接口中将一个方法定义为默认方法，然后又在超类或另一个接口中定义同样的方法，规则如下：
    1.超类优先。如果超类提供了一个具体方法，同名而且有相同参数类型的默认方法会被忽略（类优先规则）。
    2.接口冲突。如果一个接口提供了一个默认方法，另一个接口提供了一个同名而且参数类型（不论是否是默认参数）相同的方法，必须覆盖这个方法来解决冲突。
    两个包含getName方法的接口：
    interface Person {
        default String getName() { return ""; }
    }

    interface Named {
        default String getName() { return getClass().getName() + "_" + hashCode(); }
    }
    如果一个Student类同时实现了这两个接口，Java编译器会报告一个错误。只需要在Student类中提供一个getName方法即可，如下所示：
    class Student implements Person, Named {
        public String getName() { return Person.super.getName(); }
    }
    现在假设Named接口没有为getName提供默认实现：
    interface Named {
        String getName();
    }
    如果至少有一个接口提供了一个实现，编译器就会报告错误，程序员必须解决这个二义性。如果两个接口都没有为共享方法提供默认实现，那么就不存在冲突。实现类可以有两个选择：实现这个方法，或者干脆不实现（这个类本身就是抽象的）。
    警告：千万不要让一个默认方法重新定义Object类中的某个方法。例如，不能为toString或equals定义默认方法。由于“类优先”规则，这样的方法绝对无法超越Object.toString或Object.equals。
6.1.9 对象克隆
    对于每一个类，需要确定：
    1.默认的clone方法是否满足要求；
    2.是否可以在可变的子对象上调用clone来修补默认的clone方法；
    3.是否不该使用clone。

    如果选择第1项或第2项，类必须：
    1.实现Cloneable接口；
    2.重新定义clone方法，并指定public访问修饰符。
    注释：Object类中的clone方法声明为protected，所以你的代码不能直接调用anObject.clone()。子类只能调用受保护的clone方法来克隆它自己的对象。必须重新定义clone为public才能允许所有方法克隆对象。

    在这里，Cloneable接口的出现与接口正常使用并没有关系。具体来说，它没有指定clone方法，这个方法是从Object类继承的。这个接口只是作为一个标记，指示类设计者了解克隆过程。如果一个对象请求克隆，但是没有实现这个接口，就会生成一个检查型异常。
    注释：Cloneable接口是Java提供的少数标记接口之一。（有些程序员称之为记号接口）标记接口不包含任何方法，它唯一的作用就是允许在类型查询中使用instanceof：
    if(obj instanceof Cloneable) ...
    建议自己的程序中不要使用标记接口。

    即使clone的默认（浅拷贝）实现能够满足要求，还是需要实现Cloneable接口，将clone重新定义为public，再调用super.clone()。下面给出一个例子：
    class Employee implements Cloneable {
        //public access, change return type
        public Employee clone() throws CloneNotSupportedException {
            return (Employee)super.clone();
        }
    }
    创建深拷贝的clone方法的一个例子：
    class Employee implements Cloneable {
        ...
        public Employee clone throws CloneNotSupportedException {
            //call Object.clone()
            Employee cloned = (Employee) super.clone();

            //clone mutable fields
            cloned.hireDay = (Date) hireDay.clone();

            return cloned;
        }
    }

    必须当心子类的克隆。例如，一旦为Employee类定义了clone方法，任何人都可以用它来克隆Manager对象。但是Manager可能会有需要深拷贝或不可克隆的字段。不能保证子类的实现者一定会修正clone方法让它正常工作。出于这个原因，在Object类中clone方法声明为protected。不过，如果你希望类用户调用clone，就不能这样做。
    注释：所有数组类型都有一个公共的clone方法，而不是受保护的。可以用这个方法建立一个新数组，包含原数组所有元素的副本。例如：
    int[] luckyNumbers = { 2, 3, 5, 7, 11, 13 };
    int[] cloned = luckyNumbers.clone();
    cloned[5] = 12; // doesn't change luckyNumbers[5]