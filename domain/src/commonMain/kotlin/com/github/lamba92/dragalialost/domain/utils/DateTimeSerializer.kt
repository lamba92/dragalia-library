package com.github.lamba92.dragalialost.domain.utils

import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.soywiz.klock.DateTime
import com.soywiz.klock.format
import com.soywiz.klock.parseUtc
import kotlinx.serialization.*

@Serializer(forClass = DateTime::class)
object DateTimeSerializer : KSerializer<DateTime> {

    override val descriptor = PrimitiveDescriptor("WithCustomDefault", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeString(DragaliaEntity.DATE_TIME_FORMAT.format(value))
    }

    override fun deserialize(decoder: Decoder) =
        DragaliaEntity.DATE_TIME_FORMAT.parseUtc(decoder.decodeString())

}
