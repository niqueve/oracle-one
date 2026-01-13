package br.com.alura.literalura.service.translate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TranslateData(@JsonAlias("responseData") ResponseData responseData) {
}
