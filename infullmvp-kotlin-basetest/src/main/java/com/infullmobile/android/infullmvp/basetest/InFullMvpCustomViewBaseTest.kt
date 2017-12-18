package com.infullmobile.android.infullmvp.basetest

import com.infullmobile.android.infullmvp.InFullMvpView
import com.infullmobile.android.infullmvp.PresentedCustomView
import com.infullmobile.android.infullmvp.Presenter
import org.junit.Before
import org.robolectric.RuntimeEnvironment

abstract class InFullMvpCustomViewBaseTest<
        T : InFullMvpView<PresenterType, PresentedViewType>,
        PresenterType : Presenter<PresentedViewType>,
        out PresentedViewType : PresentedCustomView<PresenterType>> {

    open lateinit var testedCustomView: T

    val testedPresenter: Presenter<*>
        get() = testedCustomView.presenter
    val testedView: PresentedViewType
        get() = testedCustomView.presentedView

    @Before
    open fun setUp() {
        testedCustomView = provideCustomView()
        substituteModules(testedCustomView)
        testedCustomView.initialize()
    }

    protected fun getString(stringResourceId: Int): String = RuntimeEnvironment.application.resources
            .getString(stringResourceId)

    protected abstract fun provideCustomView(): T

    open fun substituteModules(customView: T) {
        testedCustomView.injectIntoGraph()
    }
}
