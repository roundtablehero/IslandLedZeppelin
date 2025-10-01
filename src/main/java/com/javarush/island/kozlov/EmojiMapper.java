package com.javarush.island.kozlov;

import com.javarush.island.kozlov.entity.animals.Animal;
import com.javarush.island.kozlov.entity.animals.herbivores.*;
import com.javarush.island.kozlov.entity.animals.predators.*;

import java.util.HashMap;
import java.util.Map;

public class EmojiMapper {
    private static final Map<Class<? extends Animal>, String> EMOJI_MAP = new HashMap<>();

    static {
        // Инициализация маппинга эмодзи для животных
        EMOJI_MAP.put(Wolf.class, "🐺");           // Волк
        EMOJI_MAP.put(Snake.class, "🐍");        // Удав
        EMOJI_MAP.put(Fox.class, "🦊");          // Лиса
        EMOJI_MAP.put(Bear.class, "🐻");         // Медведь
        EMOJI_MAP.put(Eagle.class, "🦅");        // Орёл

        EMOJI_MAP.put(Horse.class, "🐎");          // Лошадь
        EMOJI_MAP.put(Deer.class, "🦌");         // Олень
        EMOJI_MAP.put(Rabbit.class, "🐇");       // Кролик
        EMOJI_MAP.put(Mouse.class, "🐁");        // Мышь
        EMOJI_MAP.put(Goat.class, "🐐");         // Коза
        EMOJI_MAP.put(Sheep.class, "🐑");          // Овца
        EMOJI_MAP.put(Boar.class, "🐗");         // Кабан
        EMOJI_MAP.put(Buffalo.class, "🐃");      // Буйвол
        EMOJI_MAP.put(Duck.class, "🦆");           // Утка
        EMOJI_MAP.put(Caterpillar.class, "🐛");    // Гусеница

    }

    // Метод для получения эмодзи по классу животного
    public static String getEmoji(Class<? extends Animal> animalClass) {
        return EMOJI_MAP.getOrDefault(animalClass, "❌"); // Возвращаем "❌" если эмодзи нет
    }
}
