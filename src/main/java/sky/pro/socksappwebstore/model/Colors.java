package sky.pro.socksappwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colors {
    private Color color;
    private Colorsmeasure colorsmeasure;
    private List<Sizes> sizes;
}
