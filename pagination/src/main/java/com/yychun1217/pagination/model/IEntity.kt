package com.yychun1217.pagination.model

interface IEntity<TYPE : EntityType> {
    val type: TYPE

    fun <ENTITY : IEntity<EntityType>> toEntity(type: EntityType): ENTITY? = null

    interface Api : IEntity<EntityType.API> {
        override val type: EntityType.API
            get() = EntityType.API
    }

    interface Ui : IEntity<EntityType.UI> {
        override val type: EntityType.UI
            get() = EntityType.UI
    }

    interface Db : IEntity<EntityType.DB> {
        override val type: EntityType.DB
            get() = EntityType.DB
    }
}