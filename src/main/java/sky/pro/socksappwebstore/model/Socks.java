package sky.pro.socksappwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Socks {
    private LocalDate localDate;
    private Size size;
    private Color color;
    private int cottonPart;

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "Socks{" +
                "localDate=" + localDate +
                ", size=" + size +
                ", color=" + color +
                ", cottonPart=" + cottonPart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && localDate.equals(socks.localDate) && size == socks.size && color == socks.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate, size, color, cottonPart);
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Socks(LocalDate localDate, Size size, Color color, int cottonPart) {
        this.localDate = localDate.now();
        this.size = size;
        this.color = color;
        this.cottonPart = cottonPart;

    }
}
