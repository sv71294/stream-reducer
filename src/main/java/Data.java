import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@ToString
final class Data {
    private final String id;
    private final Date timestamp;
}