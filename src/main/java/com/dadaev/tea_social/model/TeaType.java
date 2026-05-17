package com.dadaev.tea_social.model;

public enum TeaType {
    GREEN_TEA("Зеленый чай"),
    RED_TEA("Красный (черный) чай"),
    OOLONG_TEA("Улун"),
    WHITE_TEA("Белый чай"),
    PU_ERH("Пуэр"),
    YELLOW_TEA("Желтый чай"),
    HEI_CHA("Хей-ча");

    private final String displayName;

    TeaType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
