package finance.modelling.fmcommons.data.schema.eod.enums;

public enum Interval {
    DAY("d"),
    WEEK("w"),
    MONTH("m");

    private final String label;

    Interval(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
