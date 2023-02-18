package sky.pro.socksappwebstore.model;

public enum CottonPartMeasure {
    PROCENT("процентов");

    CottonPartMeasure(String cottonPartMeasure) {
        this.cottonPartMeasure = cottonPartMeasure;
    }

    @Override
    public String toString() {
        return "CottonPartMeasure{" +
                "cottonPartMeasure='" + cottonPartMeasure + '\'' +
                '}';
    }

    public String getCottonPartMeasure() {
        return cottonPartMeasure;
    }

    private final String cottonPartMeasure;
}
