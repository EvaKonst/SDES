
import java.util.*;

public class SimpleDES
{
  public static void main(String[] args)
  {
    
    CreateKeys KG = new CreateKeys();
    Encryption en  = new Encryption();
    Scanner scanner = new Scanner(System.in);
    
    String plntxt ;
    String Key;
    int[] cphrtxt = new int[8];
    
    try
    {   
    System.out.print("Let's encrypt! \n");
    System.out.print("Enter 8-bit Plaintext : \n");
    plntxt = scanner.next();
            
    System.out.print("Enter a 10-bit binary number. This will be your Key : \n");
    Key = scanner.next();

    System.out.println("\n Now we will generate 2 subkeys, K1 and K2. \n");

    KG.MakeKeys(Key);
    System.out.println("For encryption we will use K1 for round 1 and K2 for round 2 \n");
    cphrtxt = en.all( plntxt ,KG.get_K1(),KG.get_K2());
    
    System.out.println(" \n Let's Decrypt! \n ");
    
    System.out.print("Enter 8-bit binary number. This will be your Ciphertext : ");
    plntxt = scanner.next();
    System.out.println(" \n ");
                
    System.out.print("Enter the 10-bit binary number that will be your Key : ");
    Key = scanner.next();
    System.out.println(" \n ");
    
    System.out.println("\n For decryption we will follow the same steps, but will use the two subkeys in reverse order \n");
    KG.MakeKeys(Key);
    cphrtxt = en.all( plntxt ,KG.get_K2(),KG.get_K1());

    }
    catch(InputMismatchException e)
    {
      System.out.println("-- Error Occured : Invalid Input ");
    }
    catch(Exception e)
    {
      System.out.println("-- Error Occured : "+e);
    }
    
  }
  
}

