package com.manoilo.giphy.mappers

import com.manoilo.giphy.data.GifDto
import com.manoilo.giphy.entity.Gif
import com.manoilo.weatherapp.mappers.Mapper
import javax.inject.Inject

class GifMapper
@Inject
constructor() : Mapper<Gif, GifDto> {
    override fun map(input: GifDto): Gif {
        return Gif(input.images.image.url)
    }
}