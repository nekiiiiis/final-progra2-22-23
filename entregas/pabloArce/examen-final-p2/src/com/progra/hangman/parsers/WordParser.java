package com.progra.hangman.parsers;

import com.progra.hangman.base.LargeWord;
import com.progra.hangman.base.MediumWord;
import com.progra.hangman.base.ShortWord;
import com.progra.hangman.base.Word;
import com.progra.hangman.exceptions.InvalidIdException;
import com.progra.hangman.exceptions.InvalidWordException;

public class WordParser implements Parser {


    String regex; // Cadena de texto que representa el patrón de expresión regular
    final int ELEMENT_COUNT = 3; // Número de elementos que debe contener la cadena de texto al hacer split

    public WordParser() {
        // Regex por defecto, separa por comas "," la cadena de texto que se le pasa al método parse
        this.regex = ",";
    }

    public WordParser(String regex) {
        this.regex = regex;
    }

    public Word parse(String tokens) throws InvalidWordException {
        Word finalWord = null;

        /*
        * Tokens es una cadena que contiene la información de una palabra.
        * Se encarga de validar que la cadena contenga 3 elementos separados por el caracter regex caso contrario lanza una excepción InvalidWordException
        * La cadena debe tener la forma:
        * codigo,palabra,tipo
        * codigo: es un número que identifica la palabra
        * palabra: es la palabra en sí
        * tipo: es el tipo de palabra que es: LARGA, MEDIA o CORTA
        * Dependiendo del tipo crear la instancia de la clase Word correspondiente, que puede ser de la clase WordShort, WordMedium o WordLong
        * */
        try{
            String[] words = tokens.split(this.regex);
            try {
                this.sizeValidator(words);
            } catch (InvalidWordException e) {
                throw new InvalidWordException(e.getMessage());
            }
            int id = this.idValidator(words[0]);
            String word = words[1];
            String type = words[2];
            if(type.equals("L")){
                finalWord = new LargeWord(id, word);
            }
            else if(type.equals("M")){
                finalWord = new MediumWord(id, word);
            }
            else if(type.equals("S")){
                finalWord = new ShortWord(id, word);
            }
            else {throw new InvalidWordException("Invalid word");}
        } catch(InvalidWordException e){}
        return finalWord;
    }

    /*
     Parsea el Id de la palabra si ocurre un error lanza una excepción InvalidIdException
    */
    private int idValidator(String id) throws InvalidIdException {
        int idInt;

        try{
            idInt = Integer.parseInt(id);
        }catch(Exception e){
            throw new InvalidIdException(e.getMessage());
        }

        return idInt;
    }

    /*
    * Valida que la cadena contenga 3 elementos separados por el caracter regex caso contrario lanza una excepción InvalidWordException
     */
    private void sizeValidator(String[] words) throws InvalidWordException {
        if(words.length != this.ELEMENT_COUNT){
            throw new InvalidWordException("Missing words values");
        }
    }


}
