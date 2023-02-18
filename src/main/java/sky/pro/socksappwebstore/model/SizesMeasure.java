package sky.pro.socksappwebstore.model;

public enum SizesMeasure {
    SIZES_MEASURE("Размер");

    public String getSizesMeasure() {
        return sizesMeasure;
    }

    @Override
    public String toString() {
        return "SizesMeasure{" +
                "sizesMeasure='" + sizesMeasure + '\'' +
                '}';
    }

    SizesMeasure(String sizesMeasure) {
        this.sizesMeasure = sizesMeasure;
    }

    private final String sizesMeasure;
}
