public class Recursion
{
    //power method should take the base and raise it to the exponent
  public static int power(int base, int exponent)
  {
    if (exponent == 0){
      return 1;
    }
      return base * power (base, exponent-1);
  }

  //factorial method should return the calculation of num!
  //num = 0 should return 1 (0! = 1)
  //num = 1 should return 1 (1! = 1)
  public static int factorial(int num)
  {
    if (num == 1){
      return 1;
    }
    return num * factorial(num-1);
  }

  //fibonacci method should return the num fibonacci value
  //num = 0 should return 0
  //num = 1 should return 1
  //num = 2 should return 1
  //precondition: num >= 0
  public static int fibonacci(int num)
  {
    if (num == 0 || num == 1){
      return num;
    } else {
    return fibonacci(num - 1) + fibonacci(num -2);
  }
}
}