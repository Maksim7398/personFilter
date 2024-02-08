package team.mediasoft.study.tasks.streamapi.service;

import team.mediasoft.study.tasks.streamapi.exception.FilterValidationException;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

import static team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator.CONTAINS;
import static team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator.EQUALS;

public class PersonValidator {

    public static void validator(SearchFilter searchFilter) {
        validatorName(searchFilter);
        validatorBirthPlaceRegion(searchFilter);
        validatorAge(searchFilter);
        allFieldIsNull(searchFilter);
    }

    private static void allFieldIsNull(SearchFilter searchFilter) {
        if (searchFilter.getFirstName() == null
                && searchFilter.getLastName() == null
                && searchFilter.getSecondName() == null
                && searchFilter.getAge() == null
                && searchFilter.getBirthPlaceRegion() == null) {
            throw new FilterValidationException("not all fields is null");
        }
    }

    private static void validatorName(SearchFilter filter) {
        if (filter.getSecondName() != null) {
            if (filter.getSecondName().getValue() == null) {
                throw new FilterValidationException("Not valid lastName");
            }
            if (filter.getSecondName().getOperator() != EQUALS && filter.getSecondName().getOperator() != CONTAINS) {
                throw new FilterValidationException("Not valid secondName");
            }
        }
        if (filter.getFirstName() != null) {
            if (filter.getFirstName().getValue() == null) {
                throw new FilterValidationException("Not valid lastName");
            }
            if (filter.getFirstName().getOperator() != EQUALS && filter.getFirstName().getOperator() != CONTAINS) {
                throw new FilterValidationException("Not valid firstName");
            }

        }
        if (filter.getLastName() != null) {
            if (filter.getLastName().getValue() == null) {
                throw new FilterValidationException("Not valid lastName getValue");
            }
            if (filter.getLastName().getOperator() != EQUALS && filter.getLastName().getOperator() != CONTAINS) {
                throw new FilterValidationException("Not valid lastName getOperator");
            }
        }


    }

    private static void validatorAge(SearchFilter filter) {
        if (filter.getAge() != null) {
            if (filter.getAge().getOperator() == CONTAINS) {
                throw new FilterValidationException("not valid age");
            }
        }
    }

    private static void validatorBirthPlaceRegion(SearchFilter searchFilter) {
        if (searchFilter.getBirthPlaceRegion() != null) {
            if (searchFilter.getBirthPlaceRegion().getValue().isEmpty()) {
                throw new FilterValidationException("not Valid birthPlaceRegion");
            }
            if (searchFilter.getBirthPlaceRegion().getOperator() != CONTAINS) {
                throw new FilterValidationException("not Valid birthPlaceRegion");
            }
        }

    }


}
