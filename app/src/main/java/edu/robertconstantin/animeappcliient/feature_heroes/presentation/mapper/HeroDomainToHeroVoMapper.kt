package edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper

import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO


fun HeroDM.toHeroVo() = HeroVO(id, name, image, about, rating, power, month, day)

fun HeroVO.toHeroDM() = HeroDM(id, name, image, about, rating, power, month, day)
