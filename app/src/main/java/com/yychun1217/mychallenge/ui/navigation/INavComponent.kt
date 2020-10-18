package com.yychun1217.mychallenge.ui.navigation

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.ui.navigation.INavComponent.Companion.findNavComponent
import java.lang.ref.WeakReference

interface INavComponent {
    companion object {
        fun View.findNavComponent(): INavComponent? = findViewNavComponent(this)

        private fun findViewNavComponent(aView: View): INavComponent? {
            var view = aView
            while (true) {
                getViewNavComponent(view)?.let {
                    return it
                }
                val parent = view.parent
                view = if (parent is View) parent else break
            }
            return null
        }

        private fun getViewNavComponent(view: View): INavComponent? {
            return if (view is INavComponent) view else {
                when (val tag =
                    view.getTag(R.id.nav_component_view_tag)) {
                    is WeakReference<*> -> tag.get() as? INavComponent
                    is INavComponent -> tag
                    else -> null
                }
            }
        }
    }

    fun navigate(@IdRes destinationId: Int, args: Bundle?): Boolean
}

fun Fragment.navigate(@IdRes destinationId: Int, args: Bundle?) {
    view?.let {
        val isNavigationHandled = it.findNavComponent()?.navigate(destinationId, args) ?: false
        if (!isNavigationHandled) {
            it.findNavController().navigate(destinationId, args)
        }
    }
}