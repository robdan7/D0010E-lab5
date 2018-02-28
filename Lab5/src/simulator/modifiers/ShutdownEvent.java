package simulator.modifiers;

import simulator.State;
import simulator.queue.EventQueue;

public class ShutdownEvent extends Event {
	
	ShutdownEvent(float time){
		this.time=time;
	}

	@Override
	public void action(EventQueue eventQueue, State state) {
		state.setFlag(true);
	}

}
