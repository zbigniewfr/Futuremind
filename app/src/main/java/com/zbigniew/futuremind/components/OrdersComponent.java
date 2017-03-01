package com.zbigniew.futuremind.components;

import com.zbigniew.futuremind.activities.MainActivity;
import com.zbigniew.futuremind.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zbigniew on 28/02/2017.
 */

@Singleton
@Component(modules={NetModule.class})
public interface OrdersComponent {
    void inject(MainActivity mainActivity);
}
