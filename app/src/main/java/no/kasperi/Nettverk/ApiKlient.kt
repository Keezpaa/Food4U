package no.kasperi.Nettverk

import no.kasperi.Models.ResponsModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiKlient {

        private val BASE_URL = "https://api.edamam.com/"
        private val api: Food4UApi = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Food4UApi::class.java)

    fun getRecipes(recipe: String, app_id: String, app_key: String): Call<ResponsModel> {
            return api.getRecipes(recipe, app_id, app_key)
        }

        fun getRecipesByPage(
            from: Int,
            to: Int,
            recipe: String,
            app_id: String,
            app_key: String
        ): Call<ResponsModel> {
            return api.getRecipesByPage(from, to, recipe, app_id, app_key)
        }

        fun getCustomRecipes(
            from: Int,
            to: Int,
            query: String,
            calories: String,
            mealType: String,
            dietType: String,
            app_id: String,
            app_key: String
        ): Call<ResponsModel> {
            return api.getCustomRecipes(from,to,query, calories, mealType, dietType, app_id, app_key)
        }

        fun getCuisineTypeResults(
            from: Int,
            to: Int,
            query: String,
            cuisineType: String,
            app_id: String,
            app_key: String
        ): Call<ResponsModel> {
            return api.getCuisineTypeResults(from,to,query, cuisineType, app_id, app_key)
        }

        fun getMealTypeResults(
            from: Int,
            to: Int,
            query: String,
            mealType: String,
            app_id: String,
            app_key: String
        ): Call<ResponsModel> {
            return api.getMealTypeResults(from,to,query, mealType, app_id, app_key)
        }

        fun getDishTypeResults(
            from: Int,
            to: Int,
            query: String,
            dishType: String,
            app_id: String,
            app_key: String
        ): Call<ResponsModel> {
            return api.getDishTypeResults(from,to,query, dishType, app_id, app_key)
        }
}