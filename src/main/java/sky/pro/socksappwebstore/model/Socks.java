package sky.pro.socksappwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private String socks;
    private List<Colors> colors;
    private CottonPart cottonPart;
}
