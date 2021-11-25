import java.util.*;
import java.lang.*;

//Here, the 2 keys will be generated from the main key (K) given by the user
class CreateKeys
{
  private int[] K = new int[10];
  private int[] K1 = new int[8];
  private int[] K2 = new int[8];
  
  CreateKeys() //The constructor
  {
  }
  
  void MakeKeys(String key_in )
  {
    int[] K = new int[10];
    
    char c1;
    String bin;
    
    try
    {
    for(int i=0;i<10;i++)
    {
       c1 = key_in.charAt(i);
       bin = Character.toString(c1);
       K[i] = Integer.parseInt(bin);
    }
    }
    catch(Exception e)
    {
        System.out.println("\n The key you input is invalid! We want a binary number! \n");
    }
    
    
    this.K = K;
    
    System.out.println("\n First, we will perform operation P10. Our Key's 10 digits will be permutated likeso: \n");
    System.out.println("\n Before: 1 2 3 4 5 6 7 8 9 10 \n");
    System.out.println("\n After: 3 5 2 7 4 10 1 9 8 6 \n");
    permutationP10();
    
    System.out.println("\n Here's our key after permutation P10: ");
    PrintArr.array(this.K,10);
    System.out.println("\n");
    
    System.out.println("\n Next up we will divide our key in two halves and operate a one-position left shift on each one. \n");
    leftshiftLS1();
    
    System.out.println(" Our key after operation LeftShift1: ");
    PrintArr.array(this.K,10);
    System.out.println("\n");
    
    System.out.println("\n Now, we'll operate permutation P8 on our key. In P8 permutation, the number's digits are permutated likeso: \n");
    System.out.println("\n Before: 1 2 3 4 5 6 7 8 9 10 \n");
    System.out.println("\n After: 6 3 7 4 8 5 10 9 . Notice that we don't use bits 1 and 2.\n");
    this.K1 = permutationP8();

    System.out.println("\n The number after this permutation is Subkey 1!:");
    PrintArr.array(this.K1,8);
    System.out.println("\n");
    
    System.out.println("\n Remember the two halves of our main key? Now we'll operate a two-position left shift on each one. \n");
    leftshiftLS2();
  
    System.out.println("\n Your key after the 2 position left shift: ");
    PrintArr.array(this.K,10);
    System.out.println("\n");
    
    System.out.println("\n Now, we'll operate permutation P8 on our key. \n");
    this.K2 = permutationP8();
    
    System.out.println("\n The number after this permutation is Subkey 2!:");
    PrintArr.array(this.K2,8);
    System.out.println("\n"); 

}
  
  private void permutationP10()
  {
    int[] temp = new int[10];
    
    temp[0] = K[2];
    temp[1] = K[4];
    temp[2] = K[1];
    temp[3] = K[6];
    temp[4] = K[3];
    temp[5] = K[9];
    temp[6] = K[0];
    temp[7] = K[8];
    temp[8] = K[7];
    temp[9] = K[5];
    
    
    K = temp;
        
  }
  
  
  private void leftshiftLS1()
  {
    int[] temp = new int[10];
    
    temp[0] = K[1];
    temp[1] = K[2];
    temp[2] = K[3];
    temp[3] = K[4];
    temp[4] = K[0];   
    temp[5] = K[6];
    temp[6] = K[7];
    temp[7] = K[8];
    temp[8] = K[9];
    temp[9] = K[5];
    
    K = temp;
    
  }
  
  
  private int[] permutationP8()
  {
    int[] temp = new int[8];
    
    temp[0] = K[5];
    temp[1] = K[2];
    temp[2] = K[6];
    temp[3] = K[3];
    temp[4] = K[7];
    temp[5] = K[4];
    temp[6] = K[9];
    temp[7] = K[8];
    
    return temp;
        
  }
  
  
  private void leftshiftLS2()
  {
    int[] temp = new int[10];
    
    temp[0] = K[2];
    temp[1] = K[3];
    temp[2] = K[4];
    temp[3] = K[0];
    temp[4] = K[1];    
    temp[5] = K[7];
    temp[6] = K[8];
    temp[7] = K[9];
    temp[8] = K[5];
    temp[9] = K[6];
    
    K = temp;
    
  }


  public int[] get_K1()
  {
    return K1; 
  }

  public int[] get_K2()
  {
    return K2;
  }  

}

