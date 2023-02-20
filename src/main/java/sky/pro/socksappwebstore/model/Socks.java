package sky.pro.socksappwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Size size;
    private Color color;
    private int cottonPart;
    private int quantity;
}
