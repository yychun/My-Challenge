package com.yychun1217.pagination.model

interface IEntityContract<TYPE : EntityType> {
    val type: TYPE

    fun <ENTITY : IEntityContract<EntityType>> toEntity(type: EntityType): ENTITY? = null

    interface Api : IEntityContract<EntityType.API> {
        override val type: EntityType.API
            get() = EntityType.API
    }

    interface Ui : IEntityContract<EntityType.UI> {
        override val type: EntityType.UI
            get() = EntityType.UI
    }

    interface Db : IEntityContract<EntityType.DB> {
        override val type: EntityType.DB
            get() = EntityType.DB
    }
}