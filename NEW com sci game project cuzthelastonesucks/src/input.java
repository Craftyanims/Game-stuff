
public class input {
	static boolean Waiting4RightResponse = false;
	public   String AvalOption(String option1,String option2,String option3,String option4){
		Waiting4RightResponse = false;
		String i;
		while(Waiting4RightResponse == false){
			i = Main.b.nextLine();	
			if((i.equalsIgnoreCase(option1))||(i.equalsIgnoreCase(option2))||(i.equalsIgnoreCase(option3))||(i.equalsIgnoreCase(option4))){
				Waiting4RightResponse = true;
				return i;
				}
			else{
				
			}
		
		}
		return "";
		
	}
	
	
	
}