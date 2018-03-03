package market.modifiers;

import simulator.State;
import simulator.modifiers.Event;
import simulator.queue.EventQueue;

@Deprecated
public class ShutdownEvent extends Event {
	
	ShutdownEvent(float time){
		super(time);
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		state.endState();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
