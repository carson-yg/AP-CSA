public class Iterative
{
  //power method should take the base and raise it to the exponent
  public static int power(int base, int exponent)
  {
    int power = 1;
    for (int x = 0; x < exponent; x++){
      power *= base;
    }
    return power;
  }

  //factorial method should return the calculation of num!
  //num = 0 should return 1 (0! = 1)
  //num = 1 should return 1 (1! = 1)
  public static int factorial(int num)
  {
    int product = 1;
    for (int x = num; x > 0; x--){
      product *= x;
    }
    return product;
  }

  //fibonacci method should return the num fibonacci value
  //num = 0 should return 0
  //num = 1 should return 1
  //num = 2 should return 1
  //precondition: num >= 0
  public static int fibonacci(int num)
  {
    int prev = -1;
    int curr = 1;
    for (int x = 0; x <= num; x++){
      int temp = curr + prev;
      prev = curr;
      curr = temp;
    }
    return curr;
  }
}