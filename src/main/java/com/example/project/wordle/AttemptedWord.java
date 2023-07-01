package com.example.project.wordle;

public class AttemptedWord extends Word {
    public AttemptedWord(String word) {
        super(word);
    }

    public boolean isValid() {
        return DictionaryService.checkValidity(this.word);
    }

    public boolean compareWithTarget(TargetWord targetWord) {
        return this.word.equals(targetWord.getWord());
    }
}
