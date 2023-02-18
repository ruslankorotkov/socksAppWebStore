package sky.pro.socksappwebstore.model;

public enum Colorsmeasure {
    COLORSMEASURE("цвет");

    Colorsmeasure(String colorsmeasure) {
        this.colorsmeasure = colorsmeasure;
    }

    @Override
    public String toString() {
        return "Colorsmeasure{" +
                "colorsmeasure='" + colorsmeasure + '\'' +
                '}';
    }

    public String getColorsmeasure() {
        return colorsmeasure;
    }

    private final String colorsmeasure;
}
