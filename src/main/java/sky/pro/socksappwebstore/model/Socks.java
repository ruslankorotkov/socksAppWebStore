package sky.pro.socksappwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Socks {
    private LocalDate localDate;
    private Size size;
    private Color color;
    private int cottonPart;
}
