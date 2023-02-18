package sky.pro.socksappwebstore.model;

public enum Color {
    RED("красный"),
    ORANGE("оранжевый"),
    YELLOW("желтый"),
    GREEN("зеленый"),
    BLUE("синий"),
    PURPLE("фиолетовый"),
    PINK("розовый"),
    BLACK("черный"),
    WHITE("белый"),
    GRAY("серый"),
    TERRACOTTA("терракота"),
    COQUELICOT("дикий мак"),
    FOLLY("безумие");

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }

    public String getColor() {
        return color;
    }

    Color(String color) {
        this.color = color;
    }

    private final String color;
}
