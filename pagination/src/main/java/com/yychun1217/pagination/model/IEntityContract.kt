package com.yychun1217.pagination.model

interface IEntityContract {
    interface Api<out DB : Db<Api<DB, UI>, UI>, out UI : Ui<Api<DB, UI>, DB>> : IEntityContract {
        fun toDb(): DB? = null
        fun toUi(): UI? = null
    }

    interface Db<out API : Api<Db<API, UI>, UI>, out UI : Ui<API, Db<API, UI>>> : IEntityContract {
        fun toApi(): API? = null
        fun toUi(): UI? = null
    }

    interface Ui<out API : Api<DB, Ui<API, DB>>, out DB : Db<API, Ui<API, DB>>> : IEntityContract {
        fun toApi(): API? = null
        fun toDb(): DB? = null
    }
}