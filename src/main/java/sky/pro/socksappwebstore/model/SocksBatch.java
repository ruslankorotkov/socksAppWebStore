package sky.pro.socksappwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SocksBatch {
    private Socks socks;
    private int quantity;

}
