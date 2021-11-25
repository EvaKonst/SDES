class InspectInput{

static int i; 
    
static void InspectInput(int K[]) throws InvalidInputExc{
        if ( K[i] !=0 && K[i]!=1)
	   throw new InvalidInputExc("Sorry, invalid key. Try again.\n");
	else System.out.println("Your input key:");} 
}