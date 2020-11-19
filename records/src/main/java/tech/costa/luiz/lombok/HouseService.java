package tech.costa.luiz.lombok;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class HouseService {

    public House newHouse() {
        log.info("Let's build a new house");
        return House.builder()
                .address("5th Avenue")
                .owner("Dr Manhattan")
                .squareMeters(42_000)
                .zipCode("99")
                .build();
    }
}
