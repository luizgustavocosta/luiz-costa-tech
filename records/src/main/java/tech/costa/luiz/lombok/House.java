package tech.costa.luiz.lombok;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
// Or
//@Data
public class House {

    private final String owner;
    private final String address;
    private final String zipCode;
    private final int squareMeters;
}
