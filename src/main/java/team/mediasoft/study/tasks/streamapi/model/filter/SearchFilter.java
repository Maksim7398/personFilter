package team.mediasoft.study.tasks.streamapi.model.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchFilter {

    private final FilterParameter<String> lastName;
    private final FilterParameter<String> firstName;
    private final FilterParameter<String> secondName;
    private final FilterParameter<Integer> age;
    private final FilterParameter<String> birthPlaceRegion;
}
