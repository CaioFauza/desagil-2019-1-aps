package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand;
    private final NandGate nand1;

    public AndGate() {
        super(2);
        nand = new NandGate();
        nand1 = new NandGate();
    }

    @Override
    public boolean read() {
        return nand1.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }

        nand.connect(inputPin, emitter);
        nand1.connect(inputPin, nand);
    }


}
