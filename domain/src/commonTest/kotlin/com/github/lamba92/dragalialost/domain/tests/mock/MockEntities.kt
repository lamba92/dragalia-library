package com.github.lamba92.dragalialost.domain.tests.mock

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.entities.enums.*
import com.github.lamba92.dragalialost.domain.entities.support.*
import com.soywiz.klock.DateTime
import com.soywiz.klock.Month.December
import com.soywiz.klock.Month.September

object MockEntities {
    // https://dragalialost.gamepedia.com/Euden#Rarity_5
    val adventurer
        get() = AdventurerEntity(
            //region Mock adventurer
            "Euden",
            "The seventh heir of the Alberian royal family, he lives with the mutual support of his twin " +
                    "sister, Zethia, and the faerie Notte. Always smiling, he carries a unique blend of unwavering " +
                    "courage and intense consideration for others.",
            430,
            288,
            100,
            ManaCircleBonusStats(263, 24),
            ManaCircleBonusStats(176, 16),
            310,
            2396,
            10,
            HeroCLass.ATTACK,
            Gender.MALE,
            Race.HUMAN,
            Rarity.FIVE,
            VoiceActor("Vincent Tong"),
            VoiceActor("Koki Uchiyama"),
            Source.Story(0),
            DateTime(2018, September, 27),
            Availability.STORY,
            "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/f/f9/100001_01_r04_portrait.png/" +
                    "600px-100001_01_r04_portrait.png?version=de017ccbf0f9db0881f54c853cac6954",
            Element.FIRE,
            WeaponType.SWORD,
            Skill(
                "Blazing Circlet",
                2376,
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/c/c1/Icon_Skill_004.png/" +
                        "45px-Icon_Skill_004.png?version=3a9136691ba64773ed7f147aeaebc70a",
                SkillLevelData(
                    SkillLevel.ONE,
                    100,
                    "Deals two hits of 304% flame damage to surrounding enemies."
                ),
                SkillLevelData(
                    SkillLevel.TWO,
                    200,
                    "Deals two hits of 338% flame damage to surrounding enemies."
                ),
                SkillLevelData(
                    SkillLevel.THREE,
                    300,
                    "Deals two hits of 375% flame damage to surrounding enemies."
                )
            ),
            Skill(
                "Exalted Fire",
                4880,
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/c/c1/Icon_Skill_004.png/" +
                        "45px-Icon_Skill_004.png?version=3a9136691ba64773ed7f147aeaebc70a",
                SkillLevelData(
                    SkillLevel.ONE,
                    100,
                    "Deals 1 hit of 608% flame damage to enemies in a line"
                )
                ,
                SkillLevelData(
                    SkillLevel.TWO,
                    200,
                    "Deals 1 hit of 638% flame damage to enemies in a line, and reduces their defense by 5% for" +
                            " 10 seconds with 90% base chance."
                )
            ),
            Ability(
                "Dragon's Claws",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/4/4b/Icon_Ability_1020002.png/" +
                        "45px-Icon_Ability_1020002.png?version=486df5fa5658fe46ab1e57cb034b3dd1",
                AbilityLevelData(
                    "Each shapeshift increases strength for the remainder of the quest (up to three times per " +
                            "quest). On first shapeshift strength is increased by 4% in total. On second shapeshift " +
                            "strength is increased by 10% in total. On third shapeshift strength is increased by 20% " +
                            "in total. ",
                    AbilityLevel.ONE,
                    60
                ),
                AbilityLevelData(
                    "Each shapeshift increases strength for the remainder of the quest (up to three times per " +
                            "quest). On first shapeshift strength is increased by 5% in total. On second shapeshift " +
                            "strength is increased by 13% in total. On third shapeshift strength is increased by 25% " +
                            "in total.",
                    AbilityLevel.TWO,
                    80
                ),
                AbilityLevelData(lvl2.Details, AbilityLevel.ONE, lvl2.PartyPowerWeight.toInt())
            ),
            Ability(
                "Stun Res",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/f/f1/Icon_Ability_1030006.png/" +
                        "45px-Icon_Ability_1030006.png?version=41ef9016c9d335f7a4aaddf7fc1853d5",
                AbilityLevelData(
                    "Reduces susceptibility to stun by 50%",
                    AbilityLevel.ONE,
                    60
                ),
                AbilityLevelData(
                    "Reduces susceptibility to stun by 100%.",
                    AbilityLevel.TWO,
                    80
                ),
                AbilityLevelData(lvl2.Details, AbilityLevel.ONE, lvl2.PartyPowerWeight.toInt())
            ),
            Ability(
                "Player EXP",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/a/af/Icon_Ability_1010003.png/" +
                        "45px-Icon_Ability_1010003.png?version=29249bc46590c354879a487c65a88fc2",
                AbilityLevelData(
                    "Increases player EXP intake for clearing quests by 10%. This ability does not stack with " +
                            "other similar abilities; the highest value will be applied. ",
                    AbilityLevel.ONE,
                    60
                ),
                AbilityLevelData(
                    "Increases player EXP intake for clearing quests by 15%. This ability does not stack with " +
                            "other similar abilities; the highest value will be applied.",
                    AbilityLevel.TWO,
                    80
                ),
                AbilityLevelData(lvl2.Details, AbilityLevel.ONE, lvl2.PartyPowerWeight.toInt())
            ),
            CoAbility(
                "Dragon Hast",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/1/1e/Icon_Ability_1020005.png/" +
                        "45px-Icon_Ability_1020005.png?version=02b05ca68bc839861744301ca3e0b9f7",
                CoAbilityLevelData(
                    CoAbilityLevel.ONE,
                    "Increases dragon gauge fill rate by 5%. Benefits your whole team.",
                    110
                ),
                CoAbilityLevelData(
                    CoAbilityLevel.ONE,
                    "Increases dragon gauge fill rate by 6%. Benefits your whole team.",
                    140
                ),
                CoAbilityLevelData(
                    CoAbilityLevel.ONE,
                    "Increases dragon gauge fill rate by 8%. Benefits your whole team.",
                    170
                ),
                CoAbilityLevelData(
                    CoAbilityLevel.ONE,
                    "Increases dragon gauge fill rate by 11%. Benefits your whole team.",
                    230
                ),
                CoAbilityLevelData(
                    CoAbilityLevel.ONE,
                    "Increases dragon gauge fill rate by 15%. Benefits your whole team.",
                    320
                )
            )
            //endregion
        )

    // https://dragalialost.gamepedia.com/Apollo
    val dragon
        get() = DragonEntity(
            //region Mock dragon
            "Apollo",
            "A dragon who favors honorable combat, and the guardian of Apollonia. Daily skirmishes are held in " +
                    "that city, as fighters seeking honor and glory struggle to cultivate their skill and discipline" +
                    " through friendly competition.",
            168,
            127,
            100,
            178,
            202,
            996,
            1245,
            Rarity.FIVE,
            SellValue(5000, 8500),
            VoiceActor("-"),
            VoiceActor("Daisuke Ono"),
            Gender.MALE,
            Source.Summoning(),
            DateTime(2019, September, 12),
            Availability.PERMANENT,
            "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/a/a1/210103_01_portrait.png/" +
                    "600px-210103_01_portrait.png?version=c9167abb4e56f3579efb3be04b2eddcb",
            Skill(
                "Arrow of GLory",
                50,
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/b/bd/Icon_Skill_022.png/" +
                        "45px-Icon_Skill_022.png?version=973dfe1c58e16d3f6a6f474d75395cda",
                SkillLevelData(
                    SkillLevel.ONE,
                    50,
                    "Deals 1 hit of 162% and 1 hit of 378% flame damage to the target and nearby enemies, " +
                            "inflicts burn for 30 seconds - dealing 31.1% damage every 3.9 seconds - with 100% base " +
                            "chance, and reduces defense by 5% for 10 seconds with 100% base chance."
                ),
                SkillLevelData(
                    SkillLevel.TWO,
                    100,
                    "Deals 1 hit of 180% and 1 hit of 420% flame damage to the target and nearby enemies, " +
                            "inflicts burn for 30 seconds - dealing 41.5% damage every 3.9 seconds - with 110% base " +
                            "chance, and reduces defense by 5% for 10 seconds with 100% base chance."
                )
            ),
            Ability(
                "(Flame) Strength",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/4/4b/Icon_Ability_1020002.png/" +
                        "45px-Icon_Ability_1020002.png?version=486df5fa5658fe46ab1e57cb034b3dd1",
                AbilityLevelData(
                    "If the user is attuned to Flame: increases strength by 35%.",
                    AbilityLevel.ONE,
                    65
                ),
                AbilityLevelData(
                    "If the user is attuned to Flame: increases strength by 50%.",
                    AbilityLevel.TWO,
                    85
                ),
                AbilityLevelData(lvl2.Details, AbilityLevel.ONE, lvl2.PartyPowerWeight.toInt())
            ),
            Ability(
                "(Flame) Burning Punisher",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/f/f7/Icon_Ability_1070002.png/" +
                        "45px-Icon_Ability_1070002.png?version=d4d51f2f569ae2cca71cf2cd23b7f25c",
                AbilityLevelData(
                    "If the user is attuned to Flame: increases damage to burning enemies by 15%.",
                    AbilityLevel.ONE,
                    5
                ),
                AbilityLevelData(
                    "If the user is attuned to Flame: increases damage to burning enemies by 20%.",
                    AbilityLevel.TWO,
                    15
                ),
                AbilityLevelData(lvl2.Details, AbilityLevel.ONE, lvl2.PartyPowerWeight.toInt())
            ),
            AttackModifier(
                AttackComboData(AttackComboNumber.ONE, 190, 1),
                AttackComboData(AttackComboNumber.TWO, 209, 1),
                AttackComboData(AttackComboNumber.THREE, 257, 1)
            ),
            Element.FIRE
            //endregion
        )

    val wyrmprint
        get() = WyrmprintEntity(
            //region Mock wyrmprint
            "Heralds of Hinomoto",
            WyrmprintDescription(
                "Two attendants awaited their master at the foot of a vermilion bridge. Just then, the man they " +
                        "were awaiting came forth. It was Ieyasu - pactbound warrior and head of one of the twelve " +
                        "wyrmclans of the East.",
                "Ieyasu's gaze was directed at a land far west of Hinomoto—one known as Alberia. \"Let us go " +
                        "and deliver blessings to them,\" he said before setting out with his loyal attendants at " +
                        "his side.",
                "The journey to Alberia would not be easy. But though countless dangers lay in wait - from " +
                        "ferocious fiends to brutal bandits - they would not stop their travels until they had hewn " +
                        "open a path to their destination.",
                "Sazanka swung her axe easily - causing a hint of petals to dance upon the wind - while Addis " +
                        "slew foes with a single slash of his lightning-fast blade. Their loyalty was unconditional; " +
                        "nothing too much to ask.",
                "Within Ieyasu dwelled a legendary dragon of war and a symbol of victory - Marishiten. There was " +
                        "nothing his dragon's blade could not cleave in twain, and naught could hope to stand in their " +
                        "way."
            ),
            177,
            64,
            145,
            421,
            Source.Summoning("Eastern Emissaries"),
            DateTime(2018, December, 31),
            Availability.EVENT_WELLFARE,
            "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/9/95/400125_01_portrait.png/" +
                    "600px-400125_01_portrait.png?version=f26365ebee4be2488ad330cc1932ccf3",
            "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/0/0b/400125_02_portrait.png/" +
                    "600px-400125_02_portrait.png?version=34a8f794fbe920846ced84b7a2967185",
            Rarity.FIVE,
            100,
            SellValue(5000, 3000),
            listOf("Ieyasu, Sazanka, Addis, Marishiten"),
            null,
            null,
            WyrmprintAbility(
                "Skill Damage",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/b/b9/Icon_Ability_1010002.png/" +
                        "45px-Icon_Ability_1010002.png?version=b810a6825fbc77652c1a26d0d926ec73",
                WyrmprintAbilityLevelData(
                    "Increases attack skill damage by 20%.",
                    WyrmprintAbilityLevel.ONE,
                    60
                ),
                WyrmprintAbilityLevelData(
                    "Increases attack skill damage by 25%.",
                    WyrmprintAbilityLevel.TWO,
                    80
                ),
                WyrmprintAbilityLevelData(
                    "Increases attack skill damage by 30%.",
                    WyrmprintAbilityLevel.THREE,
                    100
                )
            ),
            WyrmprintAbility(
                "Skill Haste",
                "https://gamepedia.cursecdn.com/dragalialost_gamepedia_en/thumb/4/43/Icon_Ability_1020004.png/" +
                        "45px-Icon_Ability_1020004.png?version=a78c5793328bb1f1e3d5a70cbb65aa09",
                WyrmprintAbilityLevelData(
                    "Increases skill gauge fill rate by 4%.",
                    WyrmprintAbilityLevel.ONE,
                    50
                ),
                WyrmprintAbilityLevelData(
                    "Increases skill gauge fill rate by 5%.",
                    WyrmprintAbilityLevel.TWO,
                    60
                ),
                WyrmprintAbilityLevelData(
                    "Increases skill gauge fill rate by 6%.",
                    WyrmprintAbilityLevel.THREE,
                    80
                )
            )
            //endregion
        )

}