package com.javarush.island.kozlov.settings;

import java.util.HashMap;
import java.util.Map;

public class AnimalEmojis {

    private static final Map<String, String> emojiMap = new HashMap<>();

    static {
        emojiMap.put("Wolf", "\uD83D\uDC3A");
        emojiMap.put("Boa", "\uD83D\uDC0D");
        emojiMap.put("Fox", "\uD83E\uDD8A");
        emojiMap.put("Bear", "\uD83D\uDC3B");
        emojiMap.put("Eagle", "\uD83E\uDD85");
        emojiMap.put("Horse", "\uD83D\uDC0E");
        emojiMap.put("Deer", "\uD83E\uDD8C");
        emojiMap.put("Rabbit", "\uD83D\uDC07");
        emojiMap.put("Mouse", "\uD83D\uDC01");
        emojiMap.put("Goat", "\uD83D\uDC10");
        emojiMap.put("Sheep", "\uD83D\uDC11");
        emojiMap.put("Boar", "\uD83D\uDC17");
        emojiMap.put("Buffalo", "\uD83D\uDC03");
        emojiMap.put("Duck", "\uD83E\uDD86");
        emojiMap.put("Caterpillar", "\uD83D\uDC1B");
        emojiMap.put("Plant", "\uD83C\uDF3F");
    }

    public static String getEmoji(String animalName) {
        return emojiMap.getOrDefault(animalName, "\uD83D\uDC3E"); // если нет эмодзи
    }
}
