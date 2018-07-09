package org.muks.fsm.examples;

import java.util.*;

public class FiniteStateMachine {
    private enum State {
        Ready(true, "Deposit", "Quit"),
        Waiting(true, "Select", "Refund"),
        Dispensing(true, "Remove"),
        Refunding(false, "Refunding"),
        Exiting(false, "Quiting");

        State(boolean exp, String... in) {
            inputs = Arrays.asList(in);
            explicit = exp;
        }

        State nextState(String input, State current) {
            if (inputs.contains(input)) {
                return map.getOrDefault(input, current);
            }
            return current;
        }

        final List<String> inputs;
        final static Map<String, State> map = new HashMap<>();
        final boolean explicit;

        static {
            map.put("Deposit", State.Waiting);
            map.put("Quit", State.Exiting);
            map.put("Select", State.Dispensing);
            map.put("Refund", State.Refunding);
            map.put("Remove", State.Ready);
            map.put("Refunding", State.Ready);
        }
    }


    /**
     * Link: https://rosettacode.org/wiki/Finite_state_machine
     *
     Consider the model of a simple vending machine. The machine is initially in the "ready" state, which maps to exactly two states in the following way:

     ready -> deposit -> waiting

     ready -> quit -> exit

     The variables in bold-face represent transitions. Any input signal not corresponding to one of those transitions can either trigger an error or be ignored. Otherwise, the current state is updated and the process is repeated. If, for example, a deposit input signal is encountered, the FSM will move to the "waiting" state, which defines these transitions:

     waiting -> select -> dispense

     waiting -> refund -> refunding

     The "dispense" state defines only one transition:

     dispense -> remove -> ready

     Note, however, that in this example the "refunding" state doesn't actually require input in order to move to the "ready" state, so an implicit transition is defined as such:

     refunding -> ready

     [Deposit, Quit]
     > Deposit
     [Select, Refund]
     > Refund
     [Refunding]
     [Deposit, Quit]
     > Deposit
     [Select, Refund]
     > Quit
     [Select, Refund]
     > Select
     [Remove]
     > Remove
     [Deposit, Quit]
     > Quit

     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        State state = State.Ready;

        while (state != State.Exiting) {
            System.out.println(state.inputs);
            if (state.explicit){
                System.out.print("> ");
                state = state.nextState(sc.nextLine().trim(), state);
            } else {
                state = state.nextState(state.inputs.get(0), state);
            }
        }
    }
}
