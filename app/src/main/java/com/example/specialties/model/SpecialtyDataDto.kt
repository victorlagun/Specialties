package com.example.specialties.model

import com.google.gson.annotations.SerializedName

data class SpecialtyDataDto(
    @SerializedName("response")
    val response: List<Response>
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
    val specialty: List<SpecialtyDto>
)

data class SpecialtyDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("specialty_id")
    val specialtyId: Int
)

fun Response.toEmployee() = Employee(
    employee_id = this.hashCode(),
    avatr_url = this.avatrUrl,
    birthday = this.birthday,
    f_name = this.fName,
    l_name = this.lName,
)

fun SpecialtyDto.toSpecialty() = Specialty(
    name = this.name,
    specialty_id = this.specialtyId,
)

fun SpecialtyDataDto.toEmployeeSpecialtyCrossRef() =
    this.response.map { list -> Pair(list.toEmployee(), list.specialty.map { it.toSpecialty() }) }
        .map { pair ->
            pair.second.map { specialty ->
                EmployeeSpecialtyCrossRef(
                    pair.first.employee_id,
                    specialty.specialty_id
                )
            }
        }.flatten()