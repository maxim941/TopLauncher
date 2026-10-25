package com.toplauncher.app.data

data class CatalogItem(
    val id: Int,
    val title: String,
    val category: String,
    val emoji: String,
    val version: String,
    val sizeText: String,
    val rating: Float,
    val description: String
)

object CatalogData {
    val items = listOf(
        CatalogItem(1, "Too Many Items", "mod", "\uD83E\uDDE9", "1.21+", "1.2 МБ", 4.8f,
            "Улучшает инвентарь: сортировка, поиск предметов, быстрый крафт."),
        CatalogItem(2, "Lucky Block", "mod", "\uD83C\uDF40", "1.20+", "0.8 МБ", 4.6f,
            "Добавляет блок с случайными наградами и событиями."),
        CatalogItem(3, "WorldEdit PE", "mod", "\uD83D\uDEE0", "1.19+", "2.4 МБ", 4.9f,
            "Инструменты редактирования мира: заливка, копирование, отмена."),
        CatalogItem(4, "BSL Shaders", "resource", "\uD83C\uDF07", "1.20+", "8.4 МБ", 4.9f,
            "Реалистичное освещение, тени и отражения воды."),
        CatalogItem(5, "Complementary Shaders", "resource", "\uD83C\uDF08", "1.19+", "7.2 МБ", 4.8f,
            "Мягкие тени и улучшенная цветопередача.")
    )
}