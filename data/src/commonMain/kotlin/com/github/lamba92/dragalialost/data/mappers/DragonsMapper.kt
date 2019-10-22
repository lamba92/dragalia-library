package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.DragonJSON
import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.enums.Gender
import com.github.lamba92.dragalialost.domain.entities.support.SellValue
import com.github.lamba92.dragalialost.domain.entities.support.VoiceActor
import com.soywiz.klock.parseUtc

class DragonsMapper(
    private val dragonAbilityMapper: DragonAbilityMapper,
    private val dragonSkillMapper: DragonSkillMapper,
    private val rarityMapper: RarityMapper,
    private val availabilityMapper: AvailabilityMapper,
    private val elementMapper: ElementMapper
) : SingleFromRemoteMapper<DragonsMapper.Params, DragonEntity> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        val ability1 = dragonAbilityMapper(ability1lvl1 to ability1lvl2)
        val ability2 = if (ability2lvl1 != null && ability2lvl2 != null)
            dragonAbilityMapper(ability2lvl1 to ability2lvl2)
        else
            null
        val skill = dragonSkillMapper(skill1)
        with(dragon) {
            DragonEntity(
                Name,
                ProfileText,
                MaxHp.toInt(),
                MaxAtk.toInt(),
                DragaliaEntity.DRAGONS_MAX_LVL,
                MinHp.toInt() + MinAtk.toInt() + ability1.level1.might +
                        skill.level1.might + DragaliaEntity.DRAGON_BOND_MIGHT_PER_LEVEL,
                MaxHp.toInt() + MaxAtk.toInt() + skill.level2.might +
                        ability1.level2.might + (ability2?.level2?.might ?: 0) +
                        DragaliaEntity.DRAGON_BOND_MIGHT_PER_LEVEL * 30,
                rarityMapper(Rarity),
                SellValue(SellCoin.toInt(), SellDewPoint.toInt()),
                VoiceActor(EnglishCV),
                VoiceActor(JapaneseCV),
                Gender.MALE, //TODO gender!
                emptyList(), // TODO source!
                DragaliaEntity.DATE_TIME_FORMAT.parseUtc(ReleaseDate.substring(0, 10)),
                availabilityMapper(Availability),
                "", // TODO artwork!
                skill,
                ability1,
                ability2,
                emptyList(), // TODO attack modifiers
                elementMapper(ElementalType)
            )
        }
    }

    data class Params(
        val dragon: DragonJSON,
        val ability1lvl2: AbilityJSON,
        val ability1lvl1: AbilityJSON,
        val ability2lvl1: AbilityJSON?,
        val ability2lvl2: AbilityJSON?,
        val skill1: SkillJSON
    )

}
