import java.util.UUID;


public class TestUUID {

	public static void main(String[] args) {
		
		UUID uuid = java.util.UUID.randomUUID(); 
        System.out.println(uuid.toString());  
        uuid = java.util.UUID.randomUUID(); 
        System.out.println(uuid.toString()); 
	}
}
