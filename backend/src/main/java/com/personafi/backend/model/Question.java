package com.personafi.backend.model;

import java.util.List;

/**
 * Class used for Questions that will be asked on this web appppp
 */
public class Question {
    private String questionText;
    private List<String> options;
    
    public Question(String questionText, List<String> options) 
    {
        //validating the arguments
        validateQuestionText(questionText);
        validateOptions(options);

        this.questionText = questionText;
        this.options = options;
    }

    public String getQuestionText() 
    {
        return questionText;
    }

    public void setQuestionText(String questionText) 
    {
        this.questionText = questionText;
    }

    public List<String> getOptions() 
    {
        return options;
    }

    public void setOptions(List<String> options) 
    {
        this.options = options;
    }

    private void validateQuestionText(final String questionText) 
    {
        if (questionText == null || questionText.trim().isEmpty()) {
        throw new IllegalArgumentException("Question text cannot be null or empty");
        }
    }

    private void validateOptions(final List<String> options) 
    {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("Options list cannot be null or empty");
        }
        for (String option : options) {
            if (option == null || option.trim().isEmpty()) {
                throw new IllegalArgumentException("Each option must be a non-empty string");
            }
        }
    }

}


