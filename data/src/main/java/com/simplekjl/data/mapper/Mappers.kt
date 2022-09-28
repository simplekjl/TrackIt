package com.simplekjl.data.mapper

import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.ProfileRaw
import com.simplekjl.data.model.WeightRaw
import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Profile
import com.simplekjl.domain.model.Weight

fun Measures.toRaw(): MeasuresRaw {
    return MeasuresRaw(
        this.entryUid,
        this.date,
        this.chest,
        this.leftArm,
        this.rightArm,
        this.waist,
        this.hips,
        this.leftThigh,
        this.rightThigh,
        this.leftCalf,
        this.rightCalf
    )
}

fun Array<out Measures>.toRaw(): Array<MeasuresRaw> {
    return this.map {
        MeasuresRaw(
            it.entryUid,
            it.date,
            it.chest,
            it.leftArm,
            it.rightArm,
            it.waist,
            it.hips,
            it.leftThigh,
            it.rightThigh,
            it.leftCalf,
            it.rightCalf
        )
    }.toTypedArray()
}

fun MeasuresRaw.toModel(): Measures {
    return Measures(
        this.entryUid,
        this.date,
        this.chest,
        this.leftArm,
        this.rightArm,
        this.waist,
        this.hips,
        this.leftThigh,
        this.rightThigh,
        this.leftCalf,
        this.rightCalf
    )
}

fun List<MeasuresRaw>.toMeasureModel(): List<Measures> {
    return this.map {
        Measures(
            it.entryUid,
            it.date,
            it.chest,
            it.leftArm,
            it.rightArm,
            it.waist,
            it.hips,
            it.leftThigh,
            it.rightThigh,
            it.leftCalf,
            it.rightCalf
        )
    }.toList()
}

fun Array<out Weight>.toRaw(): Array<WeightRaw> {
    return this.map { WeightRaw(it.uid ?: 0, it.date, it.weight, it.note) }.toTypedArray()
}

fun List<WeightRaw>.toModel(): List<Weight> {
    return this.map { Weight(it.uid, it.date, it.weight, it.note) }
}

fun WeightRaw.toModel(): Weight {
    return Weight(this.uid, this.date, this.weight, this.note)
}

fun Weight.toRaw(): WeightRaw {
    return WeightRaw(this.uid ?: 0, this.date, this.weight, this.note)
}

fun ProfileRaw.toModel(): Profile {
    return Profile(this.uid, this.name, this.goalWeight, this.createdDate)
}
