package com.robinfood.surveysapi.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {

    public static final String UNANSWERED = "One or more Answers were not answered";
    public static final String INVALID_ANSWER = "Answer is invalid for the question ";
    public static final String SQL_TRANSACTION = "Error when executing the SQL transaction";
    public static final String NOT_CONTROLLED_ERROR = "Error no controlled";


}
