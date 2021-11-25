 class Encryption
 {
  private int[] Key_1 = new int[8];
  private int[] Key_2 = new int[8];
  private int[] plntxt = new int[8];
  
  void GetPlaintext(String plaintext , int[] Key_1, int[] Key_2)
  {
    int[] plntxt = new int[8];
    char c;
    String bin ;
    
    try
    {
       for(int i=0;i<8;i++)
      {
       c = plaintext.charAt(i);
       bin = Character.toString(c);
       plntxt[i] = Integer.parseInt(bin);
      }
    }
    
    catch(Exception e)
    {
                System.out.println("Exception occured:" +e);
    }
    
    this.plntxt = plntxt;
    
    System.out.println("The plaintext you input: ");
    PrintArr.array(this.plntxt,8);
    System.out.println("\n");
    
    this.Key_1 = Key_1;
    this.Key_2 = Key_2;
    
    System.out.println("\n Your two subkeys: \n");
    System.out.println("\n Subkey 1: \n");
    PrintArr.array(Key_1,8);
    System.out.println("\n Subkey 2: \n");
    PrintArr.array(Key_2,8);
  }
  
  void InitialPermutation()
  {
    int[] tmp = new int[8];
    
    tmp[0] = plntxt[1];
    tmp[1] = plntxt[5];
    tmp[2] = plntxt[2];
    tmp[3] = plntxt[0];
    tmp[4] = plntxt[3];
    tmp[5] = plntxt[7];
    tmp[6] = plntxt[4];
    tmp[7] = plntxt[6];
    
    plntxt = tmp;
    
     System.out.println("\n \n The first step is Initial Permutaion. In this operation, we permute the digits of the plaintext likeso : \n");
     System.out.println("Before: 1 2 3 4 5 6 7 8\n");
     System.out.println("After: 2 6 3 1 4 8 5 7\n");
     System.out.println("It should be mentioned that we will use the second half of this number as it is in our switch funtion. \n");
     PrintArr.array(this.plntxt,8);
     System.out.println("\n");
    
  } 
  void InitialPermutationInversed()
  {
    int[] tmp = new int[8];
    
    tmp[0] = plntxt[3];
    tmp[1] = plntxt[0];
    tmp[2] = plntxt[2];
    tmp[3] = plntxt[4];
    tmp[4] = plntxt[6];
    tmp[5] = plntxt[1];
    tmp[6] = plntxt[7];
    tmp[7] = plntxt[5];

    plntxt = tmp;
    System.out.println("In Inversed Initial Permutation, we permute the digits of the number likeso : \n");
    System.out.println("Before: 1 2 3 4 5 6 7 8\n");
    System.out.println("After: 4 1 3 5 7 2 8 6\n");
    
  }
  
   int[] mapping(int[] RH, int[] SubKey)
  {
    int[] tmp = new int[8];
    
    tmp[0]  = RH[3];
    tmp[1]  = RH[0];
    tmp[2]  = RH[1];
    tmp[3]  = RH[2];
    tmp[4]  = RH[1];
    tmp[5]  = RH[2];
    tmp[6]  = RH[3];
    tmp[7]  = RH[0];
    
     System.out.println("We'll operate Expansion Permutation on the right half of the key. Notice that in this permutation we're generating 8 bits out of 4 bits \n");
     System.out.println("\n Before: 5 6 7 8");
     System.out.println("\n After: 8 5 6 7 6 7 8 5 \n");
     PrintArr.array(tmp,8);
     System.out.println("\n");
     
    tmp[0] = tmp[0] ^ SubKey[0];
    tmp[1] = tmp[1] ^ SubKey[1];
    tmp[2] = tmp[2] ^ SubKey[2];
    tmp[3] = tmp[3] ^ SubKey[3];
    tmp[4] = tmp[4] ^ SubKey[4];
    tmp[5] = tmp[5] ^ SubKey[5];
    tmp[6] = tmp[6] ^ SubKey[6];
    tmp[7] = tmp[7] ^ SubKey[7];
    
     System.out.println("Bit-by-bit XOR operation between the result of EP and the subkey : ");
     PrintArr.array(tmp,8);
     System.out.println("\n Now we'll split the number that came up in two halves S0 and S1. Each halve has a 4x4 matrix that we call a S-box. Our input(the two halves) is used to select the row/column in the S-box.\n");
     System.out.println("\n The element in that position will be our output. Here are our two S-Boxes: \n");
     System.out.println("\n S0:\n");
     System.out.println(" ---           ___ \n");
     System.out.println(" |01  00  11  10| \n");
     System.out.println(" |11  10  01  00| \n");
     System.out.println(" |00  10  01  11| \n");
     System.out.println(" |11  01  11  10| \n");
     System.out.println("---            ___ \n");
     System.out.println("\n\n");
     System.out.println("\n S1:\n");
     System.out.println(" ---           ___ \n");
     System.out.println(" |00  01  10  11| \n");
     System.out.println(" |10  00  01  11| \n");
     System.out.println(" |11  00  01  00| \n");
     System.out.println(" |10  01  00  11| \n");
     System.out.println("---            ___ \n");
     System.out.println("\n bit1 and bit4 of each half specifies the row\n");
     System.out.println("\n bit2 and bit3 specifies the column\n");
     
    // S-Boxes
    final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} } ;
    final int[][] S1 = { {0,1,2,3},  {2,0,1,3}, {3,0,1,0}, {2,1,0,3}} ;
    
   
      int b1h1 = tmp[0]; // first bit first half 
      int b4h1 = tmp[3]; // fourth bit first half
      
      int S0row = PracticalFunctions.BinaryToDecimal(b1h1,b4h1);
      
      int b2h1 = tmp[1]; // second bit first half
      int b3h1 = tmp[2]; // third bit first half      

    int S0column = PracticalFunctions.BinaryToDecimal(b2h1,b3h1);
      
    int output0 = S0[S0row][S0column];      
    int[] op1 = PracticalFunctions.DecToBinary(output0);
    
     System.out.println("output of S-Box S0: ");
     PrintArr.array(op1,2);
     System.out.println("\n");

      int b1h2 = tmp[4]; // first bit second half
      int b4h2 = tmp[7]; // fourth bit second half

    int S1row = PracticalFunctions.BinaryToDecimal(b1h2,b4h2);
    
      int b2h2 = tmp[5]; // second bit second half
      int b3h2 = tmp[6]; // third bit second half

    int S1column = PracticalFunctions.BinaryToDecimal(b2h2,b3h2);
    
    int output1 = S1[S1row][S1column];
    int[] op2 = PracticalFunctions.DecToBinary(output1);
    
     System.out.println("output of S-Box S1: ");
     PrintArr.array(op2,2);
     System.out.println("\n");
        
      int[] res = new int[4];
      res[0] = op1[0];
      res[1] = op1[1];
      res[2] = op2[0];
      res[3] = op2[1];
      System.out.println("Collective output: ");
      PrintArr.array(res,4);
      System.out.println("\n"); 
      
      int [] P4 = new int[4];
      P4[0] = res[1];
      P4[1] = res[3];
      P4[2] = res[2];
      P4[3] = res[0];
      
      System.out.println("We will operate a Permutation 4 on this number.\n");
      System.out.println("Before: 1 2 3 4 \n ");
      System.out.println("After: 2 4 3 1 \n");
      System.out.println("Output of mapping : ");
      PrintArr.array(P4,4);
      System.out.println("\n");  
     
      return P4;
  }
  
  int[] FK(int[] LH, int[] RH,int[] SubKey)
  { 
    int[] tmp = new int[4];
    int[] res = new int[8];    
    
    tmp = mapping(RH,SubKey);
        
    //XOR left half of Initial Permutation with resput of mapping 
    res[0] = LH[0] ^ tmp[0];
    res[1] = LH[1] ^ tmp[1];
    res[2] = LH[2] ^ tmp[2];
    res[3] = LH[3] ^ tmp[3];
    
    res[4] = RH[0];
    res[5] = RH[1];
    res[6] = RH[2];
    res[7] = RH[3];
    
    System.out.println("Finishing with function FK, we will do a XOR operation between the output of mapping and the left half of the initial permutation. \n");
    return res;
    
    
  }
  
  int[] SW(int[] tmp2)
  {
    
    int[] tmp1 = new int[8];
    
    tmp1[0] = tmp2[4];
    tmp1[1] = tmp2[5];
    tmp1[2] = tmp2[6];
    tmp1[3] = tmp2[7];
    tmp1[4] = tmp2[0];
    tmp1[5] = tmp2[1];
    tmp1[6] = tmp2[2];
    tmp1[7] = tmp2[3];  
    
    System.out.println("With function switch, we interchange the second half of initial permutation we kept from earlier with the 4-bit result of function FK. Then we can start Round 2, repeating the FK function but using subkey 2 this time.\n");
    return tmp1;
  }

  int[] all(String plaintext, int[] LK, int[] RK)
    {
        GetPlaintext(plaintext,LK,RK);
        InitialPermutation();
        
        int[] LeftHalf = new int[4];
        int[] RightHalf = new int[4];
        
        LeftHalf[0] = plntxt[0];
        LeftHalf[1] = plntxt[1];
        LeftHalf[2] = plntxt[2];
        LeftHalf[3] = plntxt[3];
    
        RightHalf[0] = plntxt[4];
        RightHalf[1] = plntxt[5];
        RightHalf[2] = plntxt[6];
        RightHalf[3] = plntxt[7];
        
        System.out.println("Round 1:");
        int[] Round1 = new int[8];
        Round1 = FK(LeftHalf,RightHalf,Key_1);
       
        int[] tmp = new int[8];
        tmp = SW(Round1);
        System.out.println("Result of the first round (switched): ");
        PrintArr.array(tmp,8);
        
        LeftHalf[0] = tmp[0];
        LeftHalf[1] = tmp[1];
        LeftHalf[2] = tmp[2];
        LeftHalf[3] = tmp[3];
    
        RightHalf[0] = tmp[4];
        RightHalf[1] = tmp[5];
        RightHalf[2] = tmp[6];
        RightHalf[3] = tmp[7];
    
        int[] Round2 = new int[8];
        System.out.println("\n Round 2:\n");
        Round2 = FK(LeftHalf, RightHalf, Key_2);
        
        plntxt = Round2;
    
        System.out.println("Result of the second round: ");
        PrintArr.array(this.plntxt,8);
        System.out.println("\n Lastly, we will operate an Inversed Permutation \n");
        InitialPermutationInversed();
        System.out.println("Result: ");
        PrintArr.array(this.plntxt,8);
        System.out.println("\n");
    
        return plntxt;
    }
 
}
