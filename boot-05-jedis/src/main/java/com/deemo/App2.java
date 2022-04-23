package com.deemo;

public class App2 extends App {

    public static void main(String[] args) {

    }

    // 结论：父类的static方法是不会被重写的。
    //
    // 解释：在进行方法调用时，系统唯一的任务是确定被调用方法的版本。
    // 对于private、static、final方法或者构造器，这部分方法在程序真正运行之前就有一个可以确定的调用版本，
    // 并且该版本在运行期间是不可变的，编译器一开始就能确定要调用的版本，这叫做静态绑定，
    // 这些方法在类加载的时候就会把符号引用转化为该方法的直接引用。与之对应，在程序运行期间确定方法调用版本的调用方式叫做动态绑定，
    // 此时，虚拟机会为每个类创建一个方法表，列出所有方法签名和实际调用的方法，这样一来虚拟机在调用方法时，只用查找该表就行了，
    // 只有在调用时采用动态绑定的方法才能体现出多态特性。
    // @Override
    public static void aaa() {

    }
}