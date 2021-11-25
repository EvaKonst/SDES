class PracticalFunctions
{
  static int BinaryToDecimal(int n1,int n2)
    {  
    int decimal = 0;  
    int n = 0; 
    int binary = Integer.valueOf(String.valueOf(n1) + String.valueOf(n2));
    
    while(true){  
      if(binary == 0){  
        break;  
      } else {  
          int temp = binary%10;  
          decimal += temp*Math.pow(2, n);  
          binary = binary/10;  
          n++;  
       }  
    }  
    return decimal;  
    }  
  
  static int[] DecToBinary(int dec)
  {

      if(dec==0) //if dec= 00 then return array zero=[0,0]
    {
        int[] zero = new int[2];
        zero[0] = 0;
        zero[1] = 0;
        return zero;    
    }
    int[] tmp1 = new int[10] ; 
    int count = 0 ;
    
    for(int i= 0 ; dec!= 0 ; i++)
    {
      tmp1[i] = dec % 2;
      dec = dec/2;
      count++;
    }
    
    int[] tmp2 = new int[count];
    
    
    for(int i=count-1, j=0;i>=0 && j<count;i--,j++)
    {
        tmp2[j] = tmp1[i];
    }
    
    //this way we add a zero in the beginning of the number
    if(count<2)
    {
        tmp1 = new int[2];
        tmp1[0] = 0;
        tmp1[1] = tmp2[0];
        return tmp1;
    }
     
    return tmp2;
}
}