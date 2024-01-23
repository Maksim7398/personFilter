package team.mediasoft.study.tasks.streamapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import team.mediasoft.study.tasks.streamapi.model.place.Place;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {

    private String lastName;
    private String firstName;
    private String secondName;
    private LocalDate birthDate;
    private Place birthPlace;
}
