package br.com.alura.literalura.model;

import java.util.List;

public enum Category {
    LITERATURE(new String[]{"Category: American Literature","Category: British Literature","literature"}, "Literatura"),
    MYSTERY(new String[]{"Category: Crime, Thrillers and Mystery"}, "Mistério"),
    FANTASY(new String[]{"Category: Science-Fiction & Fantasy"}, "Fantasia"),
    HORROR(new String[]{"Category: Short Stories, Horror"}, "Horror"),
    YOUNG_ADULT(new String[]{"Category: Children & Young Adult Reading"}, "Infantojuvenil"),
    CLASSIC(new String[]{"Category: Classics of Literature"}, "Clássicos"),
    NOVEL(new String[]{"Category: Novels"}, "Novel"),
    CHILDREN(new String[]{"Children's Literature"}, "Infantil"),
    BEST_BOOKS(new String[]{"Best Books Ever Listings"}, "Melhores livros"),
    UNKNOWN(new String[]{"null"}, "Indefinido");


    private String[] description;
    private String categoryPTBR;

    Category(String[] description, String categoryPTBR) {
        this.description = description;
        this.categoryPTBR = categoryPTBR;
    }

    public static Category fromlist(List<String> list) {
        if (list == null || list.isEmpty()) {
            return UNKNOWN;
        }
        String firstItem = list.get(0).toLowerCase().trim();

        try {
            for (Category category : Category.values()) {
                for (String description : category.description) {
                    if (description.toLowerCase().contains(firstItem)) {
                        return category;
                    }
                }
            }
        }catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
        return  UNKNOWN;
    }

    public static Category fromPortuguese(String text) {
        for (Category category : Category.values()) {
            if (category.categoryPTBR.equalsIgnoreCase(text)) {
                return category;
            }
        }
        return UNKNOWN;
    }
}
