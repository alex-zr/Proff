package ua.kiev.prog.patterns.builder;

//@Builder
public class Laptop {
    private int hddCapasity;
    private int screenSize;
    private ProcessorType processorType;

    private Laptop(Builder builder) {
        this.hddCapasity = builder.hddCapasity;
        this.screenSize = builder.screenSize;
        this.processorType = builder.processorType;
    }

    public int getHddCapasity() {
        return hddCapasity;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public ProcessorType getProcessorType() {
        return processorType;
    }

    public static class Builder {
        private int hddCapasity;
        private int screenSize;
        private ProcessorType processorType;

        public Builder withHddCapasity(int hddCapasity) {
            this.hddCapasity = hddCapasity;
            return this;
        }

        public Builder withScreenSize(int screenSize) {
            this.hddCapasity = screenSize;
            return this;
        }

        public Builder withProcessorType(ProcessorType processorType) {
            this.processorType = processorType;
            return this;
        }

        public Laptop build() {
            return new Laptop(this);
        }
    }
}
