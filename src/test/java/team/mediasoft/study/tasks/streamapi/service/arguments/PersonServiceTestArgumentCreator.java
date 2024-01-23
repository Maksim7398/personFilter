package team.mediasoft.study.tasks.streamapi.service.arguments;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;
import team.mediasoft.study.tasks.streamapi.model.Person;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterParameter;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;
import team.mediasoft.study.tasks.streamapi.model.place.Place;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class PersonServiceTestArgumentCreator {

    private static final Person ANDREEV = new Person("Андреев", "Сергей", "Петрович", LocalDate.now().minusYears(30).minusMonths(1), new Place("Самарская область", null, null));
    private static final Person ANDRIYANOV = new Person("Андриянов", "Павел", "Викторович", LocalDate.now().minusYears(45).minusMonths(1), new Place("Волгоградская область", null, null));
    private static final Person BARINOV = new Person("Баринов", "Сергей", "Васильевич", LocalDate.now().minusYears(25).minusMonths(1), new Place("Санкт-Петербург", null, null));
    private static final Person KARPIN = new Person("Карпин", "Валерий", "Георгиевич", LocalDate.now().minusYears(44).minusMonths(1), new Place("Республика Коми", null, null));
    private static final Person BONDARCHUK = new Person("Бондарчук", "Федор", "Сергеевич", LocalDate.now().minusYears(55).minusMonths(1), new Place("Москва", null, null));
    private static final Person PODARSKIY = new Person("Подарский", "Герман", null, LocalDate.now().minusYears(20).minusMonths(1), new Place("Самарская область", null, null));
    private static final Person MARFIN = new Person("Марфин", "Михаил", "Михайлович", LocalDate.now().minusYears(60).minusMonths(1), new Place("Приморский край", null, null));
    private static final Person CHERCHESOV = new Person("Черчесов", "Станислав", "Саламович", LocalDate.now().minusYears(50).minusMonths(1), new Place("Республика Северная Осетия", null, null));
    private static final Person KRASOVSKIY = new Person("Красовский", "Олег", "Еремеевич", LocalDate.now().minusYears(15).minusMonths(1), new Place("Ростовская область", null, null));
    private static final Person DAVIDS = new Person("Давидс", "Девид", null, LocalDate.now().minusYears(24).minusMonths(1), new Place("Москва", null, null));

    private static final List<Person> ALL_PERSONS = List.of(ANDREEV, ANDRIYANOV, BARINOV, KARPIN, BONDARCHUK, PODARSKIY, MARFIN, CHERCHESOV, KRASOVSKIY, DAVIDS);

    public static Stream<Arguments> createFilterValidationArguments() {
        return Stream.of(
                Arguments.of(new SearchFilter(new FilterParameter<>("Иванов", FilterOperator.GREATER_THAN), null, null, null, null)),
                Arguments.of(new SearchFilter(new FilterParameter<>("Иванов", FilterOperator.EQUALS), new FilterParameter<>("Иванов", FilterOperator.LESS_THAN), null, null, null)),
                Arguments.of(new SearchFilter(null, null, null, new FilterParameter<>(25, FilterOperator.CONTAINS), null)),
                Arguments.of(new SearchFilter(null, null, null, null, null)),
                Arguments.of(new SearchFilter(null, null, null, null, new FilterParameter<>("", FilterOperator.CONTAINS))),
                Arguments.of(new SearchFilter(new FilterParameter<>(null, FilterOperator.EQUALS), null, null, null, null))
        );
    }

    public static Stream<Arguments> createPersonsFilterArguments() {
        return Stream.of(
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(new FilterParameter<>("Андр", FilterOperator.CONTAINS), null, null, null, null),
                        List.of(ANDREEV, ANDRIYANOV)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(new FilterParameter<>("ар", FilterOperator.CONTAINS), null, null, null, null),
                        List.of(BARINOV, KARPIN, BONDARCHUK, PODARSKIY, MARFIN)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(new FilterParameter<>("ар", FilterOperator.CONTAINS), null, null, new FilterParameter<>(30, FilterOperator.GREATER_THAN), null),
                        List.of(KARPIN, BONDARCHUK, MARFIN)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, new FilterParameter<>("Сергей", FilterOperator.EQUALS), null, null, null),
                        List.of(ANDREEV, BARINOV)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, new FilterParameter<>("е", FilterOperator.CONTAINS), null, null, null),
                        List.of(ANDREEV, ANDRIYANOV, BARINOV, KARPIN, BONDARCHUK, PODARSKIY, KRASOVSKIY, DAVIDS)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, new FilterParameter<>("е", FilterOperator.CONTAINS), null, new FilterParameter<>(45, FilterOperator.LESS_THAN), null),
                        List.of(ANDREEV, BARINOV, KARPIN, PODARSKIY, KRASOVSKIY, DAVIDS)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, null, new FilterParameter<>("вич", FilterOperator.CONTAINS), null, null),
                        List.of(ANDREEV, ANDRIYANOV, BARINOV, KARPIN, BONDARCHUK, MARFIN, CHERCHESOV, KRASOVSKIY)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, null, new FilterParameter<>("вич", FilterOperator.CONTAINS), null, new FilterParameter<>("ск", FilterOperator.CONTAINS)),
                        List.of(ANDREEV, ANDRIYANOV, BONDARCHUK, MARFIN, KRASOVSKIY)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, null, null, new FilterParameter<>(25, FilterOperator.EQUALS), null),
                        List.of(BARINOV)
                ),
                Arguments.of(
                        ALL_PERSONS,
                        new SearchFilter(null, new FilterParameter<>("Константин", FilterOperator.EQUALS), null, new FilterParameter<>(25, FilterOperator.EQUALS), null),
                        List.of()
                )
        );
    }
}
