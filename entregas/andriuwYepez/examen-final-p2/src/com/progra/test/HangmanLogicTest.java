package src.com.progra.test;

import src.com.progra.hangman.HangmanLogic;
import src.com.progra.hangman.base.ShortWord;
import src.com.progra.hangman.base.Word;
import src.com.progra.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HangmanLogicTest {

    @Test
    @DisplayName("isGameOver() should return true when the number of faults greater than or equal to the number of tries")
    void isGameOver() {
        Word word = new ShortWord(1, "hola");
        HangmanLogic logic = new HangmanLogic(word);
        logic.guessLetter("q");
        logic.guessLetter("w");
        logic.guessLetter("e");
        logic.guessLetter("r");

        assertTrue(logic.isGameOver());
    }

    @Test
    @DisplayName("isWon() should return true when the word is guessed")
    void isWon() {

        Word word = new ShortWord(1, "hola");

        HangmanLogic logic = new HangmanLogic(word);
        logic.guessLetter("h".toUpperCase());
        logic.guessLetter("o".toUpperCase());
        logic.guessLetter("l".toUpperCase());
        logic.guessLetter("a".toUpperCase());
        assertTrue(logic.isWon());

    }
}