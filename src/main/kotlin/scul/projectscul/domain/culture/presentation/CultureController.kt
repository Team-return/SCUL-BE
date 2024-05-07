package scul.projectscul.domain.culture.presentation

import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureListResponse
import scul.projectscul.domain.culture.presentation.dto.response.GetCultureResponse
import scul.projectscul.domain.culture.service.*
import scul.projectscul.infra.open.OpenApiService
import scul.projectscul.infra.s3.ImageUrlResponse
import java.util.*

@RequestMapping("/scul/cultures")
@RestController
class CultureController (
        private val openApiService: OpenApiService,
        private val getCultureListService: GetCultureListService,
        private val getCultureService: GetCultureService,
        private val getTagCultureService: GetTagCultureService,
        private val searchCultureService: SearchCultureService,
        private val createImageUrlService: CreateImageUrlService
) {

    @PostMapping("/image")
    fun createImageUrl(@RequestPart(value = "image", required = false) multipartFiles : List<MultipartFile>): ImageUrlResponse {
        return createImageUrlService.execute(multipartFiles)
    }

    @GetMapping
    fun getCultureList(): GetCultureListResponse {
        return getCultureListService.execute()
    }

    @GetMapping("/detail/{culture-id}")
    fun getCulture(@PathVariable("culture-id") @NotNull cultureId: UUID): GetCultureResponse {
        return getCultureService.execute(cultureId)
    }

    @GetMapping("/tag")
    fun getCulture(@RequestParam(name = "tag") tag: String): GetCultureListResponse {
        return getTagCultureService.execute(tag)
    }

    @GetMapping("/search")
    fun searchCulture(@RequestParam(name = "keyword") keyword: String): GetCultureListResponse {
        return searchCultureService.execute(keyword)
    }

    @GetMapping("/api")
    suspend fun saveCultureData(
            @RequestParam(name = "start_page", required = false) startPage: Int,
            @RequestParam(name = "end_page", required = false) endPage: Int)
    {
        openApiService.execute(startPage, endPage)
    }
}
