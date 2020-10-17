package com.yychun1217.pagination.model

sealed class EntityType {
    object API : EntityType()
    object UI : EntityType()
    object DB : EntityType()
}