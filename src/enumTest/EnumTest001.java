package enumTest;

import java.util.EnumMap;
import java.util.EnumSet;

public class EnumTest001 {

	public enum State{
		ON,OFF
	}
	
	public static void main(String... args){
		for(State s : State.values()){
			System.out.println(s.name());
		}
		
		System.out.println("-----------------------------------------------");
		State switchState = State.OFF;
		switch(switchState){
		case OFF:
				System.out.println("OFF");
				break;
		case ON:
				System.out.println("NO");
		}
		
		System.out.println("-----------------------------------------------");
		EnumSet<State> stateSet = EnumSet.allOf(State.class);
		for(State s : stateSet){
			System.out.println(s);
		}
		
		System.out.println("-----------------------------------------------");
		EnumMap<State,String> stateMap = new EnumMap<State,String>(State.class);
		stateMap.put(State.ON, "is ON");
		stateMap.put(State.OFF, "is OFF");
		for (State s : State.values()) {
			System.out.println(s.name()+":"+stateMap.get(s));
		}
	}
}
