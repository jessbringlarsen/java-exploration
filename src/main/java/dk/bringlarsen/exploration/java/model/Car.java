package dk.bringlarsen.exploration.java.model;

public record Car(String make, String model) {

    public Car(String make) {
        this(make, "unknown");
    }

    public static class Build {
        private String make, model;

        private Build() {}

        public static Build car() {
            return new Build();
        }

        public Build withName(String make) {
            this.make = make;
            return this;
        }

        public Build withModel(String model) {
            this.model = model;
            return this;
        }

        public Car build() {
            return new Car(make, model);
        }
    }
}
