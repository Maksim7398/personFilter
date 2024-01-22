package team.mediasoft.study.tasks.streamapi.service;

import team.mediasoft.study.tasks.streamapi.exception.FilterValidationException;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

import static team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator.CONTAINS;

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
            throw new FilterValidationException("not valid");
        }
    }

    private static void validatorName(SearchFilter filter) {
        if (filter.getFirstName() != null) {
            if (filter.getFirstName().getOperator() != null || filter.getFirstName().getValue() == null) {
//                if (filter.getFirstName().getOperator() != EQUALS && filter.getFirstName().getOperator() != CONTAINS) {
//                    throw new FilterValidationException("Not valid");
//                }
                throw new FilterValidationException("Not valid");
            }
        }
        if (filter.getSecondName() != null) {
            if (filter.getSecondName().getOperator() != null || filter.getSecondName().getValue() == null) {
//                if (filter.getSecondName().getOperator() != EQUALS && filter.getSecondName().getOperator() != CONTAINS) {
//                    throw new FilterValidationException("Not valid");
//                }
                throw new FilterValidationException("Not valid");
            }
        }
        if (filter.getLastName() != null) {
            if (filter.getLastName().getOperator() != null || filter.getLastName().getValue() == null) {
//                if (filter.getLastName().getOperator() != EQUALS && filter.getLastName().getOperator() != CONTAINS) {
//                    throw new FilterValidationException("Not valid");
//                }
                throw new FilterValidationException("Not valid");
            }
        }

    }


    private static void validatorAge(SearchFilter filter) {
        if (filter.getAge() != null) {
            if (filter.getAge().getOperator() == CONTAINS) {
                throw new FilterValidationException("not valid");
            }
        }
    }

    private static void validatorBirthPlaceRegion(SearchFilter searchFilter) {
        if (searchFilter.getBirthPlaceRegion() != null) {
            if (searchFilter.getBirthPlaceRegion().getValue().isEmpty()) {
                throw new FilterValidationException("not Valid");
            }
            if (searchFilter.getBirthPlaceRegion().getOperator() != CONTAINS) {
                throw new FilterValidationException("not Valid");
            }
        }

    }


}
