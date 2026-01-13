package br.com.alura.literalura.service.translate;

import br.com.alura.literalura.service.ApiConection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.java.accessibility.util.Translator;

import java.net.URLEncoder;

public class MyMemoryTranslate {

    public static String gettranslation(String text) {
        if(text == null || text.isBlank()) {
            return "Resumo indisponível";
        }
        String text500 = text.length()>500? text.substring(0, 500) : text;
        ObjectMapper mapper = new ObjectMapper();

        ApiConection apiConection = new ApiConection();

        String textResult = URLEncoder.encode(text500);
        String langpair = URLEncoder.encode("en|pt-BR");

        String url = "https://api.mymemory.translated.net/get?q=" + textResult + "&langpair=" + langpair;
        String json = apiConection.getData(url);

        TranslateData translator;
        try {
            translator = mapper.readValue(json, TranslateData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(translator.responseData().translatedText().contains("MYMEMORY WARNING")){
            return "Tradução indisponível";
        }
        return translator.responseData().translatedText();
    }
}
