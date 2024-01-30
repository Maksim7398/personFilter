package team.mediasoft.study.tasks.streamapi.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import team.mediasoft.study.tasks.streamapi.exception.FilterValidationException;
import team.mediasoft.study.tasks.streamapi.model.Person;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonServiceTest {

    @ParameterizedTest
    @MethodSource("team.mediasoft.study.tasks.streamapi.service.arguments.PersonServiceTestArgumentCreator#createFilterValidationArguments")
    void shouldThrowFilterValidationException(SearchFilter filter) {
        final PersonService service = new PersonService(List.of());
        assertThrows(FilterValidationException.class, () -> service.getPersonsByFilter(filter));
    }

    @ParameterizedTest
    @MethodSource("team.mediasoft.study.tasks.streamapi.service.arguments.PersonServiceTestArgumentCreator#createPersonsFilterArguments")
    void shouldSearchPersonsCorrectly(List<Person> sourcePersonList, SearchFilter filter, List<Person> expectedResult) {
        final PersonService service = new PersonService(sourcePersonList);
        final List<Person> actualResult = service.getPersonsByFilter(filter);
        Assertions.assertThat(expectedResult).containsExactlyInAnyOrderElementsOf(actualResult);
    }
}
