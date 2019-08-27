import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
final class DataReduced {
    private final Data image;
    private final Data gpsData;
}