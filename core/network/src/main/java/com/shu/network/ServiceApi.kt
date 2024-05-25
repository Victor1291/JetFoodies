package  com.shu.network

import com.shu.network.models.CategoriesDto
import com.shu.network.models.ProductDto
import com.shu.network.models.TagDto
import retrofit2.http.GET

interface ServiceApi {

    @GET("Products.json")
    suspend fun getProducts(): List<ProductDto>

    @GET("Categories.json")
    suspend fun getCategories(): List<CategoriesDto>

    @GET("Tags.json")
    suspend fun getTags(): List<TagDto>


}