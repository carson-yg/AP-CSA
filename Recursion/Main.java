class Main {
  public static void main(String[] args) {
    int b = 8;
    int e = 4;

    System.out.println(b + " ^ " + e + " = " + Iterative.power(b, e));

    int fac = 8;
    System.out.println(fac + "! = " + Iterative.factorial(fac));

    int fib = 5;
    System.out.println("Fibonacci number " + fib + " is " + Iterative.fibonacci(fib));

    System.out.println(b + " ^ " + e + " = " + Recursion.power(b, e));

    System.out.println(fac + "! = " + Recursion.factorial(fac));
    
    System.out.println("Fibonacci number " + fib + " is " + Recursion.fibonacci(fib));
  }
}