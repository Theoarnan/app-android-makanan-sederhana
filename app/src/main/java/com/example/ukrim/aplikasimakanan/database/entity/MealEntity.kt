package com.example.ukrim.aplikasimakanan.database.entity

class MealEntity(
    val idMeal : String,
    val strMeal : String,
    val strMealThumb : String,
    val strMealCategory : String,
    val strMealInstruction : String) {

    companion object {
        const val  TABLE_FAVORITE : String = "table_meal_favorite"
        const val ID : String = "ID_"
        const val ID_MEAL : String = "id_meal"
        const val NAME_MEAL : String = "str_meal"
        const val IMAGE_MEAL : String = "str_meal_thumb"
        const val INSTRUCTION_MEAL : String = "str_instruction_meal"
        const val CATEGORY_MEAL : String = "str_meal_category"
    }
}



