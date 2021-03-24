package com.example.specialties.model

import com.google.gson.annotations.SerializedName

data class SpecialityDataDto(
    @SerializedName("response")
    val response: List<Response>?
)

data class Response(
    @SerializedName("avatr_url")
    val avatrUrl: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("f_name")
    val fName: String?,
    @SerializedName("l_name")
    val lName: String?,
    @SerializedName("specialty")
    val specialty: List<SpecialtyDto>?
)

data class SpecialtyDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("specialty_id")
    val specialtyId: Int?
)

fun Response.toEmployee() = Employee(
    avatr_url  = this.avatrUrl,
    birthday = this.birthday,
    f_name = this.fName,
    l_name = this.lName,
    specialty = specialty?.map { it.toSpecalty() }
)

fun SpecialtyDto.toSpecalty() = Specialty(
    name = this.name,
    specialty_id = this.specialtyId,
)