package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandOne;
    private final NandGate nandTwo;
    private final NandGate nandThree;
    private final NandGate nandSum;
    private final NandGate nandCarry;

    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandOne = new NandGate();
        nandTwo = new NandGate();
        nandThree = new NandGate();
        nandSum = new NandGate();
        nandCarry = new NandGate();

        nandTwo.connect(1, nandOne);
        nandThree.connect(0, nandOne);
        nandSum.connect(0, nandTwo);
        nandSum.connect(1, nandThree);
        nandCarry.connect(0, nandOne);
        nandCarry.connect(1, nandOne);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandSum.read();
        } else
            return nandCarry.read();


    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        switch (inputPin) {
            case 0:
                nandOne.connect(0, emitter);
                nandTwo.connect(0, emitter);
                break;
            case 1:
                nandOne.connect(1, emitter);
                nandThree.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }

    }
}
