package team.mediasoft.study.tasks.streamapi.service;

import lombok.RequiredArgsConstructor;
import team.mediasoft.study.tasks.streamapi.model.Person;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
public class PersonService {

    private final List<Person> persons;

    public List<Person> getPersonsByFilter(SearchFilter filter) {
        PersonValidator.validator(filter);
        return persons.stream()

                .filter(p -> filter.getFirstName() == null || (p.getFirstName() != null
                        && p.getFirstName().contains(filter.getFirstName().getValue())
                        && (filter.getFirstName().getOperator() == FilterOperator.CONTAINS
                        || p.getFirstName().equals(filter.getFirstName().getValue()))))

                .filter(p -> filter.getSecondName() == null || (p.getSecondName() != null
                        && p.getSecondName().contains(filter.getSecondName().getValue())
                        && (filter.getSecondName().getOperator() == FilterOperator.CONTAINS
                        || p.getSecondName().equals(filter.getSecondName().getValue()))))

                .filter(p -> filter.getLastName() == null || (p.getLastName() != null
                        && p.getLastName().contains(filter.getLastName().getValue())
                        && (filter.getLastName().getOperator() == FilterOperator.CONTAINS
                        || p.getLastName().equals(filter.getLastName().getValue()))))

                .filter(p -> filter.getAge() == null || filterAge(p.getBirthDate(), filter))

                .filter(p -> filter.getBirthPlaceRegion() == null
                        || p.getBirthPlace().getRegion().contains(filter.getBirthPlaceRegion().getValue()))

                .toList();
    }

    private boolean filterAge(LocalDate birthDate, SearchFilter filter) {
        final int personAge = Period.between(birthDate, LocalDate.now()).getYears();
        final int filterAge = filter.getAge().getValue();
        return switch (filter.getAge().getOperator()) {
            case EQUALS -> personAge == filterAge;
            case CONTAINS -> false;
            case GREATER_THAN -> personAge > filterAge;
            case LESS_THAN -> personAge < filterAge;
        };
    }
}

