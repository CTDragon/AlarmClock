package com.better.alarm;

import android.database.Cursor;

import com.better.alarm.model.AlarmCore;
import com.better.alarm.model.Alarms;
import com.google.common.base.Preconditions;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yuriy on 24.10.2015.
 */
@Singleton
@Component(modules = AlarmClockModule.class)
public interface AlarmClockComponent {


    class Singleton {
        private static AlarmClockComponent sInstance;

        public static void initialze(AlarmClockComponent component) {
            Singleton.sInstance = component;
        }

        public static AlarmClockComponent getsInstance() {
            return Preconditions.checkNotNull(sInstance);
        }
    }

    //void inject(Dagger2Activity mainActivity);
    //add here whatever else you want to inject
    IAlarmCoreFactory alarmCoreFactory();

    Alarms alarms();
}

