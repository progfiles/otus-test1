package ru.otus.test1.di

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.Module
import dagger.Provides
import ru.otus.test1.delegates.imageDelegate
import ru.otus.test1.delegates.openDetailActivity
import javax.inject.Singleton

@Module
class AdapterModule {
    @Provides
    @Singleton
    fun provideImagesAdapter(ctx: Context) = ListDelegationAdapter(
        imageDelegate {
            openDetailActivity(ctx, it)
        }
    )
}