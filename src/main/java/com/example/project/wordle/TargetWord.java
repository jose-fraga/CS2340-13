package com.example.project.wordle;

public class TargetWord extends Word {
    public TargetWord(int length) {
        super("");
        updateWord(length);
    }

    private void updateWord(int length) {
        String curr = DictionaryService.generateWord(length).toUpperCase();
        if (!DictionaryService.checkValidity(curr)) {
            while (true) {
                curr = DictionaryService.generateWord(length).toUpperCase();
                if (DictionaryService.checkValidity(curr)) {
                    break;
                }
            }
        }
        this.word = curr;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
