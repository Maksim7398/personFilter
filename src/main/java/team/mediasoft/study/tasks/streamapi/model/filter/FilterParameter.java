package team.mediasoft.study.tasks.streamapi.model.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FilterParameter<T> {

    private final T value;
    private final FilterOperator operator;
}
